package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Product;
import com.skylab.soft_v.mapper.ProductMapper;
import com.skylab.soft_v.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品表(Product)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:07:47
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Product queryById(Integer id) {
        return this.productMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Product> queryAllByPage(int page, int limit) {
        Pager<Product> pager = new Pager<Product>();
        pager.setRows(this.productMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.productMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Product> queryList() {
        return this.productMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param product 实例对象
     * @return 对象列表
     */
    @Override
    public List<Product> queryByExample(Product product) {
        return this.productMapper.queryByExample(product);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param product 实例对象
     * @param page    查询起始位置
     * @param limit   查询条数
     * @return 对象列表
     */
    public Pager<Product> queryByExampleAndPage(Product product, int page, int limit) {
        List<Product> list = productMapper.queryByExample(product);
        Pager<Product> pager = new Pager<Product>();
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
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insert(Product product) {
        this.productMapper.insert(product);
        return product;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product insertSelective(Product product) {
        this.productMapper.insertSelective(product);
        return product;
    }

    /**
     * 修改数据
     *
     * @param product 实例对象
     * @return 实例对象
     */
    @Override
    public Product update(Product product) {
        this.productMapper.update(product);
        return this.queryById(product.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.productMapper.deleteById(id) > 0;
    }
}