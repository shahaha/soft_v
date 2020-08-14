package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Log;
import com.skylab.soft_v.mapper.LogMapper;
import com.skylab.soft_v.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志表(Log)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:52
 */
@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Log queryById(Integer id) {
        return this.logMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Log> queryAllByPage(int page, int limit) {
        Pager<Log> pager = new Pager<Log>();
        pager.setRows(this.logMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.logMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Log> queryList() {
        return this.logMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param conditions   实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<Log> queryByExampleAndPage(String conditions, int page, int limit) {
        List<Log> list = logMapper.queryByExample(conditions);
        Pager<Log> pager = new Pager<Log>();
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
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log insert(Log log) {
        this.logMapper.insert(log);
        return log;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log insertSelective(Log log) {
        this.logMapper.insertSelective(log);
        return log;
    }

    /**
     * 修改数据
     *
     * @param log 实例对象
     * @return 实例对象
     */
    @Override
    public Log update(Log log) {
        this.logMapper.update(log);
        return this.queryById(log.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.logMapper.deleteById(id) > 0;
    }
}