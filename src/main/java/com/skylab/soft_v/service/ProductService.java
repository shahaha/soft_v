package com.skylab.soft_v.service;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Product;

import java.util.List;

/**
 * 产品表(Product)表服务接口
 *
 * @author xw
 * @since 2020-08-12 18:07:47
 */
public interface ProductService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    Pager<Product> queryAllByPage(int page, int limit);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Product> queryList();

    /**
     * 通过实体作为筛选条件查询
     *
     * @param product 实例对象
     * @return 对象列表
     */
    List<Product> queryByExample(Product product);

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param product 实例对象
     * @param page    查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    Pager<Product> queryByExampleAndPage(Product product, int page, int limit);

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insert(Product product);

    /**
     * 新增数据 可以有空字段
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product insertSelective(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    Product update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

}