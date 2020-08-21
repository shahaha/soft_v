package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Model;
import com.skylab.soft_v.mapper.ModelMapper;
import com.skylab.soft_v.service.ModelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 型号表(Model)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:50
 */
@Service("modelService")
public class ModelServiceImpl implements ModelService {
    @Resource
    private ModelMapper modelMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Model queryById(Integer id) {
        return this.modelMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Model> queryAllByPage(int page, int limit) {
        Pager<Model> pager = new Pager<Model>();
        pager.setRows(this.modelMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.modelMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Model> queryList() {
        return this.modelMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param model 实例对象
     * @return 对象列表
     */
    @Override
    public List<Model> queryByExample(Model model) {
        return this.modelMapper.queryByExample(model);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param model 实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<Model> queryByExampleAndPage(Model model, int page, int limit) {
        List<Model> list = modelMapper.queryByExample(model);
        Pager<Model> pager = new Pager<Model>();
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
     * @param model 实例对象
     * @return 实例对象
     */
    @Override
    public Model insert(Model model) {
        this.modelMapper.insert(model);
        return model;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param model 实例对象
     * @return 实例对象
     */
    @Override
    public Model insertSelective(Model model) {
        this.modelMapper.insertSelective(model);
        return model;
    }

    /**
     * 修改数据
     *
     * @param model 实例对象
     * @return 实例对象
     */
    @Override
    public Model update(Model model) {
        this.modelMapper.update(model);
        return this.queryById(model.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.modelMapper.deleteById(id) > 0;
    }
}