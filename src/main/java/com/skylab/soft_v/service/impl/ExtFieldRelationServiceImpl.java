package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ExtFieldRelation;
import com.skylab.soft_v.mapper.ExtFieldRelationMapper;
import com.skylab.soft_v.service.ExtFieldRelationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 扩展字段关联表(ExtFieldRelation)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:07:46
 */
@Service("extFieldRelationService")
public class ExtFieldRelationServiceImpl implements ExtFieldRelationService {
    @Resource
    private ExtFieldRelationMapper extFieldRelationMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation queryById(Integer id) {
        return this.extFieldRelationMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<ExtFieldRelation> queryAllByPage(int page, int limit) {
        Pager<ExtFieldRelation> pager = new Pager<ExtFieldRelation>();
        pager.setRows(this.extFieldRelationMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.extFieldRelationMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> queryList() {
        return this.extFieldRelationMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param extFieldRelation 实例对象
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> queryByExample(ExtFieldRelation extFieldRelation) {
        return this.extFieldRelationMapper.queryByExample(extFieldRelation);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param extFieldRelation 实例对象
     * @param page             查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    public Pager<ExtFieldRelation> queryByExampleAndPage(ExtFieldRelation extFieldRelation, int page, int limit) {
        List<ExtFieldRelation> list = extFieldRelationMapper.queryByExample(extFieldRelation);
        Pager<ExtFieldRelation> pager = new Pager<ExtFieldRelation>();
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
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation insert(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.insert(extFieldRelation);
        return extFieldRelation;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation insertSelective(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.insertSelective(extFieldRelation);
        return extFieldRelation;
    }

    /**
     * 修改数据
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation update(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.update(extFieldRelation);
        return this.queryById(extFieldRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.extFieldRelationMapper.deleteById(id) > 0;
    }
}