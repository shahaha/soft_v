package com.skylab.soft_v.controller;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.entity.Product;
import com.skylab.soft_v.service.ProductService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 产品表(Product)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:25
 */
@RestController
@RequestMapping("product")
@Api(tags = "产品表")
@Slf4j
public class ProductController {
    /**
     * 服务对象
     */
    @Resource
    private ProductService productService;

    /**
     * 通过Id查询对象
     *
     * @param id id
     * @return 响应数据
     */
    @GetMapping("queryById")
    public ResultBean<Product> queryById(int id) {
        Product product = productService.queryById(id);
        return ResultBean.success(product);
    }

    /**
     * 分页查询
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("pageList")
    public ResultBean<Pager<Product>> pageList(int page, int limit) {
        Pager<Product> pager = productService.queryAllByPage(page, limit);
        return ResultBean.success(pager);
    }

    /**
     * 查询所有记录
     *
     * @return 响应数据
     */
    @GetMapping("list")
    public ResultBean<List<Product>> list() {
        List<Product> categories = productService.queryList();
        return ResultBean.success(categories);
    }

    /**
     * 添加记录
     *
     * @param product 添加对象
     * @return 响应数据
     */
    @PostMapping("add")
    public ResultBean<Product> add(Product product) {
        try {
            Product insertSelective = productService.insertSelective(product);
            return ResultBean.success(insertSelective);
        } catch (Exception e) {
            return ResultBean.error("保存失败");
        }
    }

    /**
     * 删除一条记录
     *
     * @param id 主键
     * @return 响应数据
     */
    @PostMapping("delete")
    public ResultBean<Product> delete(Integer id) {
        final boolean b = productService.deleteById(id);
        if (b) {
            return ResultBean.success("删除成功！");
        } else {
            return ResultBean.error("删除失败！");
        }
    }

    /**
     * 更新一条记录
     *
     * @param product 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    public ResultBean<Product> update(Product product) {
        try {
            Product update = productService.update(product);
            return ResultBean.success(update);
        } catch (Exception e) {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 根据条件查询
     *
     * @param product 查询条件
     * @return 响应数据
     */
    @GetMapping("queryByExample")
    public ResultBean<List<Product>> queryByExample(Product product) {
        List<Product> list = productService.queryByExample(product);
        return ResultBean.success(list);
    }

    /**
     * 根据条件查询并分页
     *
     * @param product 查询条件
     * @param page    当前页
     * @param limit   每页行数
     * @return 响应数据
     */
    @GetMapping("queryByExampleAndPage")
    public ResultBean<Pager<Product>> queryByExampleAndPage(Product product, int page, int limit) {
        Pager<Product> pager = productService.queryByExampleAndPage(product, page, limit);
        return ResultBean.success(pager);
    }

}