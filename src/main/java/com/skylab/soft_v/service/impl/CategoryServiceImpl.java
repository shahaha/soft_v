package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Category;
import com.skylab.soft_v.mapper.CategoryMapper;
import com.skylab.soft_v.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 种类表（蓝牙、GNSS...）(Category)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:34:52
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Category queryById(Integer id) {
        return this.categoryMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Category> queryAllByPage(int page, int limit) {
        Pager<Category> pager = new Pager<Category>();
        pager.setRows(this.categoryMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.categoryMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Category> queryList() {
        return this.categoryMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param category 实例对象
     * @return 对象列表
     */
    @Override
    public List<Category> queryByExample(Category category) {
        return this.categoryMapper.queryByExample(category);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param category 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    public Pager<Category> queryByExampleAndPage(Category category, int page, int limit) {
        List<Category> list = categoryMapper.queryByExample(category);
        Pager<Category> pager = new Pager<Category>();
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
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insert(Category category) {
        this.categoryMapper.insert(category);
        return category;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category insertSelective(Category category) {
        this.categoryMapper.insertSelective(category);
        return category;
    }

    /**
     * 修改数据
     *
     * @param category 实例对象
     * @return 实例对象
     */
    @Override
    public Category update(Category category) {
        this.categoryMapper.update(category);
        return this.queryById(category.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.categoryMapper.deleteById(id) > 0;
    }
}