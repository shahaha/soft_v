package com.skylab.soft_v.service;

import com.skylab.soft_v.bean.SoftVO;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.util.SoulPage;

import java.util.List;

/**
 * 软件表(Soft)表服务接口
 *
 * @author xw
 * @since 2020-08-12 18:11:02
 */
public interface SoftService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Soft queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Soft> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Soft> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param soft 实例对象
     * @return 对象列表
     */
    List<Soft> queryByExample(Soft soft);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param soft  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Soft> queryByExampleAndPage(Soft soft, int page, int limit);

    /**
     * 新增数据
     *
     * @param soft 实例对象
     * @return 实例对象
     */
    Soft insert(Soft soft);

    /**
     * 新增数据 可以有空字段
     *
     * @param soft 实例对象
     * @return 实例对象
     */
    Soft insertSelective(Soft soft);

    /**
     * 修改数据
     *
     * @param soft 实例对象
     * @return 实例对象
     */
    Soft update(Soft soft);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    Object queryForSoulpage(SoulPage<SoftVO> soulPage);
    /**
     * 根据编码查询
     * @param code 软件编码
     * @return 实例对象
     */
    Soft queryByCode(String code);

    Object queryByUserForSoulpage(SoulPage<SoftVO> soulPage, User user);
}