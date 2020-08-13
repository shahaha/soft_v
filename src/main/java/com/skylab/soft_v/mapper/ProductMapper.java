package com.skylab.soft_v.mapper;

import com.skylab.soft_v.entity.Product;

import java.util.List;

/**
 * 产品表(Product)表数据库访问层
 *
 * @author xw
 * @since 2020-08-12 18:07:48
 */
public interface ProductMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Product queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Product> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param product 实例对象
     * @return 对象列表
     */
    List<Product> queryByExample(Product product);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Product> queryList();

    /**
     * 新增数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int insert(Product product);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int insertSelective(Product product);

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 影响行数
     */
    int update(Product product);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    /**
     * 查询数据总行数
     *
     * @return 总行数
     */
    int count();
}