package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ToolType;
import com.skylab.soft_v.mapper.ToolTypeMapper;
import com.skylab.soft_v.service.ToolTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件工具类型表(ToolType)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:07:51
 */
@Service("toolTypeService")
public class ToolTypeServiceImpl implements ToolTypeService {
    @Resource
    private ToolTypeMapper toolTypeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ToolType queryById(Integer id) {
        return this.toolTypeMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<ToolType> queryAllByPage(int page, int limit) {
        Pager<ToolType> pager = new Pager<ToolType>();
        pager.setRows(this.toolTypeMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.toolTypeMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<ToolType> queryList() {
        return this.toolTypeMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param toolType 实例对象
     * @return 对象列表
     */
    @Override
    public List<ToolType> queryByExample(ToolType toolType) {
        return this.toolTypeMapper.queryByExample(toolType);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param toolType 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    public Pager<ToolType> queryByExampleAndPage(ToolType toolType, int page, int limit) {
        List<ToolType> list = toolTypeMapper.queryByExample(toolType);
        Pager<ToolType> pager = new Pager<ToolType>();
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
     * @param toolType 实例对象
     * @return 实例对象
     */
    @Override
    public ToolType insert(ToolType toolType) {
        this.toolTypeMapper.insert(toolType);
        return toolType;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param toolType 实例对象
     * @return 实例对象
     */
    @Override
    public ToolType insertSelective(ToolType toolType) {
        this.toolTypeMapper.insertSelective(toolType);
        return toolType;
    }

    /**
     * 修改数据
     *
     * @param toolType 实例对象
     * @return 实例对象
     */
    @Override
    public ToolType update(ToolType toolType) {
        this.toolTypeMapper.update(toolType);
        return this.queryById(toolType.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.toolTypeMapper.deleteById(id) > 0;
    }
}