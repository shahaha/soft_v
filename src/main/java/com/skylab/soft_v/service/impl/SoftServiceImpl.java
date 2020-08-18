package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.mapper.SoftMapper;
import com.skylab.soft_v.service.SoftService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 软件表(Soft)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:11:05
 */
@Service("softService")
public class SoftServiceImpl implements SoftService {
    @Resource
    private SoftMapper softMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Soft queryById(Integer id) {
        return this.softMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Soft> queryAllByPage(int page, int limit) {
        Pager<Soft> pager = new Pager<Soft>();
        pager.setRows(this.softMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.softMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Soft> queryList() {
        return this.softMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param soft 实例对象
     * @return 对象列表
     */
    @Override
    public List<Soft> queryByExample(Soft soft) {
        return this.softMapper.queryByExample(soft);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param soft  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<Soft> queryByExampleAndPage(Soft soft, int page, int limit) {
        List<Soft> list = softMapper.queryByExample(soft);
        Pager<Soft> pager = new Pager<Soft>();
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
     * @param soft 实例对象
     * @return 实例对象
     */
    @Override
    public Soft insert(Soft soft) {
        this.softMapper.insert(soft);
        return soft;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param soft 实例对象
     * @return 实例对象
     */
    @Override
    public Soft insertSelective(Soft soft) {
        this.softMapper.insertSelective(soft);
        return soft;
    }

    /**
     * 修改数据
     *
     * @param soft 实例对象
     * @return 实例对象
     */
    @Override
    public Soft update(Soft soft) {
        this.softMapper.update(soft);
        return this.queryById(soft.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.softMapper.deleteById(id) > 0;
    }

    /**
     * 根据编码查询
     *
     * @param code 软件编码
     * @return 实例对象
     */
    @Override
    public Soft queryByCode(String code) {
        return softMapper.queryByCode(code);
    }
}