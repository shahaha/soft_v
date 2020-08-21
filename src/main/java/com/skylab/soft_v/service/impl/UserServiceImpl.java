package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.bean.AccountVO;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Const;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.entity.UserRole;
import com.skylab.soft_v.mapper.UserMapper;
import com.skylab.soft_v.mapper.UserRoleMapper;
import com.skylab.soft_v.service.RedisService;
import com.skylab.soft_v.service.UserService;
import com.skylab.soft_v.util.SoulPage;
import com.skylab.soft_v.util.JwtTokenUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 用户表(User)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;
    @Resource
    private RedisService redisService;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer id) {
        return this.userMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<User> queryAllByPage(int page, int limit) {
        Pager<User> pager = new Pager<User>();
        pager.setRows(this.userMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.userMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<User> queryList() {
        return this.userMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    @Override
    public List<User> queryByExample(User user) {
        return this.userMapper.queryByExample(user);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param user  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<User> queryByExampleAndPage(User user, int page, int limit) {
        List<User> list = userMapper.queryByExample(user);
        Pager<User> pager = new Pager<User>();
        int count = list.size();
        pager.setTotal(count);
        page = Math.min(page,(count/limit)+1);
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(list.subList(fromIndex, Math.min(toIndex, count)));
        return pager;
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userMapper.insert(user);
        return user;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param user 实例对象
     * @param roleIds
     * @return 实例对象
     */
    @Override
    @Transactional
    public User insertSelective(User user, List<Integer> roleIds) {
        this.userMapper.insertSelective(user);
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleMapper.insertSelective(userRole);
        }
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @param roles
     * @return 实例对象
     */
    @Override
    public User update(User user, List<Integer> roles) {
        this.userMapper.update(user);
        userRoleMapper.deleteByUserId(user.getId());
        for (Integer roleId : roles) {
            UserRole userRole = new UserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleId);
            userRoleMapper.insertSelective(userRole);
        }
        return this.queryById(user.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean deleteById(Integer id) {
        userRoleMapper.deleteByUserId(id);
        return this.userMapper.deleteById(id) > 0;
    }

    /**
     * 通过用户名查询
     *
     * @param username 用户名
     * @return 实例对象
     */
    @Override
    public User queryByUsername(String username) {
        return userMapper.queryByUsername(username);
    }

    @Override
    public Object dataGrid(SoulPage<User> soulPage) {
        return soulPage.setData(userMapper.dataGrid(soulPage, (User) soulPage.getObj()));
    }
    /**
     * 根据条件查询用户及角色列表
     *
     * @param msg 条件
     * @return 对象列表
     */
    @Override
    public List<AccountVO> queryAccountByMsg(String msg) {
        return userMapper.queryAccountByMsg(msg);
    }

    /**
     * 判断用户是否在使用
     *
     * @param id 用户id
     * @return 是否使用
     */
    @Override
    public boolean inUser(Integer id) {
        return userMapper.inUser(id) > 0;
    }

    /**
     * 修改密码
     *
     * @param passwordOld  老密码
     * @param passwordNew  新密码
     * @param accessToken  accessToken
     * @param refreshToken refresgToken
     */
    @Override
    public void userUpdatePwd(String passwordOld, String passwordNew, String accessToken, String refreshToken) {
        String username= JwtTokenUtil.getUserId(accessToken);
        User user = userMapper.queryByUsername(username);
        if (user == null){
            throw new BusinessException(400,"用户未登录，请重新登录");
        }
        if (!passwordOld.equals(user.getPassword())){
            throw new BusinessException(400,"旧密码不匹配");
        }
        //保存新密码
        user.setPassword(passwordNew);
        int i = userMapper.update(user);
        if(i!=1){
            throw new BusinessException(400,"操作失败");
        }

        /**
         * 把token 加入黑名单 禁止再访问我们的系统资源
         */
        redisService.set(Const.JWT_ACCESS_TOKEN_BLACKLIST+accessToken,username,JwtTokenUtil.getRemainingTime(accessToken), TimeUnit.MILLISECONDS);
        /**
         * 把 refreshToken 加入黑名单 禁止再拿来刷新token
         */
        redisService.set(Const.JWT_REFRESH_TOKEN_BLACKLIST+refreshToken,username,JwtTokenUtil.getRemainingTime(refreshToken),TimeUnit.MILLISECONDS);
        /**
         * 清楚用户授权数据缓存
         */
        redisService.delete(Const.IDENTIFY_CACHE_KEY+username);
    }

    /**
     * 根据角色名查询用户列表
     *
     * @param role 角色
     * @return 用户列表
     */
    @Override
    public List<User> queryByRole(String role) {
        return userMapper.queryByRole(role);
    }
}