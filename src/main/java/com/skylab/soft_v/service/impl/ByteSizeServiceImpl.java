package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.ByteSize;
import com.skylab.soft_v.mapper.ByteSizeMapper;
import com.skylab.soft_v.service.ByteSizeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 字节大小(ByteSize)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:34:49
 */
@Service("byteSizeService")
public class ByteSizeServiceImpl implements ByteSizeService {
    @Resource
    private ByteSizeMapper byteSizeMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ByteSize queryById(Integer id) {
        return this.byteSizeMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<ByteSize> queryAllByPage(int page, int limit) {
        Pager<ByteSize> pager = new Pager<ByteSize>();
        pager.setRows(this.byteSizeMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.byteSizeMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<ByteSize> queryList() {
        return this.byteSizeMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param byteSize 实例对象
     * @return 对象列表
     */
    @Override
    public List<ByteSize> queryByExample(ByteSize byteSize) {
        return this.byteSizeMapper.queryByExample(byteSize);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param byteSize 实例对象
     * @param page     查询起始位置
     * @param limit    查询条数
     * @return 对象列表
     */
    public Pager<ByteSize> queryByExampleAndPage(ByteSize byteSize, int page, int limit) {
        List<ByteSize> list = byteSizeMapper.queryByExample(byteSize);
        Pager<ByteSize> pager = new Pager<ByteSize>();
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
     * @param byteSize 实例对象
     * @return 实例对象
     */
    @Override
    public ByteSize insert(ByteSize byteSize) {
        this.byteSizeMapper.insert(byteSize);
        return byteSize;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param byteSize 实例对象
     * @return 实例对象
     */
    @Override
    public ByteSize insertSelective(ByteSize byteSize) {
        this.byteSizeMapper.insertSelective(byteSize);
        return byteSize;
    }

    /**
     * 修改数据
     *
     * @param byteSize 实例对象
     * @return 实例对象
     */
    @Override
    public ByteSize update(ByteSize byteSize) {
        this.byteSizeMapper.update(byteSize);
        return this.queryById(byteSize.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.byteSizeMapper.deleteById(id) > 0;
    }
}