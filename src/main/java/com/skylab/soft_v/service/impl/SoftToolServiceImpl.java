package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.bean.SoftToolVO;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.SoftTool;
import com.skylab.soft_v.mapper.SoftToolMapper;
import com.skylab.soft_v.service.SoftToolService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件工具表（测试、烧录工具）(SoftTool)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:07:50
 */
@Service("softToolService")
public class SoftToolServiceImpl implements SoftToolService {
    @Resource
    private SoftToolMapper softToolMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SoftTool queryById(Integer id) {
        return this.softToolMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<SoftTool> queryAllByPage(int page, int limit) {
        Pager<SoftTool> pager = new Pager<SoftTool>();
        pager.setRows(this.softToolMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.softToolMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<SoftTool> queryList() {
        return this.softToolMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param softTool 实例对象
     * @return 对象列表
     */
    @Override
    public List<SoftTool> queryByExample(SoftTool softTool) {
        return this.softToolMapper.queryByExample(softTool);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param toolName 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    @Override
    public Pager<SoftTool> queryByNameAndPage(String toolName, int page, int limit) {
        List<SoftTool> list = softToolMapper.queryByName(toolName);
        Pager<SoftTool> pager = new Pager<SoftTool>();
        int count = list.size();
        pager.setTotal(count);
        int fromIndex = (page - 1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(list.subList(fromIndex, Math.min(toIndex, count)));
        return pager;
    }

    /**
     * 新增数据
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    @Override
    public SoftTool insert(SoftTool softTool) {
        this.softToolMapper.insert(softTool);
        return softTool;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    @Override
    public SoftTool insertSelective(SoftTool softTool) {
        this.softToolMapper.insertSelective(softTool);
        return softTool;
    }

    /**
     * 修改数据
     *
     * @param softTool 实例对象
     * @return 实例对象
     */
    @Override
    public SoftTool update(SoftTool softTool) {
        this.softToolMapper.update(softTool);
        return this.queryById(softTool.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.softToolMapper.deleteById(id) > 0;
    }

    /**
     * 根据类型查工具
     *
     * @param i 类型id
     * @return 工具列表
     */
    @Override
    public List<SoftTool> queryByType(int i) {
        return softToolMapper.queryByType(i);
    }

    @Override
    public List<SoftToolVO> queryList2SoftToolVO(String tools) {
        return softToolMapper.queryList2SoftToolVO(tools);
    }
    /**
     * 判断是否在使用
     *
     * @param id id
     * @return 是否使用
     */
    @Override
    public boolean inUser(Integer id) {
        return softToolMapper.inUser(id) > 0;
    }
}