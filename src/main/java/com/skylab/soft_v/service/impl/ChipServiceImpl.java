package com.skylab.soft_v.service.impl;

import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Chip;
import com.skylab.soft_v.mapper.ChipMapper;
import com.skylab.soft_v.service.ChipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 芯片表(Chip)表服务实现类
 *
 * @author xw
 * @since 2020-08-11 15:35:53
 */
@Service("chipService")
public class ChipServiceImpl implements ChipService {
    @Resource
    private ChipMapper chipMapper;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Chip queryById(Integer id) {
        return this.chipMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<Chip> queryAllByPage(int page, int limit) {
        Pager<Chip> pager = new Pager<Chip>();
        pager.setRows(this.chipMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.chipMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<Chip> queryList() {
        return this.chipMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param chip 实例对象
     * @return 对象列表
     */
    @Override
    public List<Chip> queryByExample(Chip chip) {
        return this.chipMapper.queryByExample(chip);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param chip  实例对象
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    public Pager<Chip> queryByExampleAndPage(Chip chip, int page, int limit) {
        List<Chip> list = chipMapper.queryByExample(chip);
        Pager<Chip> pager = new Pager<Chip>();
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
     * @param chip 实例对象
     * @return 实例对象
     */
    @Override
    public Chip insert(Chip chip) {
        this.chipMapper.insert(chip);
        return chip;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param chip 实例对象
     * @return 实例对象
     */
    @Override
    public Chip insertSelective(Chip chip) {
        this.chipMapper.insertSelective(chip);
        return chip;
    }

    /**
     * 修改数据
     *
     * @param chip 实例对象
     * @return 实例对象
     */
    @Override
    public Chip update(Chip chip) {
        this.chipMapper.update(chip);
        return this.queryById(chip.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.chipMapper.deleteById(id) > 0;
    }
}