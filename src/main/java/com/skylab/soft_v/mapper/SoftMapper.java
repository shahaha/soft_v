package com.skylab.soft_v.mapper;

import com.skylab.soft_v.bean.SoftVO;
import com.skylab.soft_v.entity.Soft;
import com.skylab.soft_v.entity.User;
import com.skylab.soft_v.util.SoulPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 软件表(Soft)表数据库访问层
 *
 * @author xw
 * @since 2020-08-12 18:11:09
 */
public interface SoftMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Soft queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Soft> queryAllByPage(int page, int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param soft 实例对象
     * @return 对象列表
     */
    List<Soft> queryByExample(Soft soft);

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    List<Soft> queryList();

    /**
     * 新增数据
     *
     * @param soft 实例对象
     * @return 影响行数
     */
    int insert(Soft soft);

    /**
     * 新增数据 对象字段可以为空
     *
     * @param soft 实例对象
     * @return 影响行数
     */
    int insertSelective(Soft soft);

    /**
     * 修改数据
     *
     * @param soft 实例对象
     * @return 影响行数
     */
    int update(Soft soft);

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

    /**
     * 清空一列
     * @param fieldName 列名
     * @return 影响行数
     */
    int cleanByColumn(String fieldName);

    List<SoftVO> queryForSoulpage(SoulPage<SoftVO> soulPage,@Param("softVO") SoftVO softVO);
    /**
     * 根据编码查询
     *
     * @param code 软件编码
     * @return 实例对象
     */
    Soft queryByCode(String code);

    List<SoftVO> queryByUserForSoulpage(SoulPage<SoftVO> soulPage,@Param("user") User user);
}