package com.skylab.soft_v.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skylab.soft_v.bean.ExtFieldRelationVO;
import com.skylab.soft_v.bean.JsonToObj;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.entity.Category;
import com.skylab.soft_v.entity.ExtFieldRelation;
import com.skylab.soft_v.mapper.CategoryMapper;
import com.skylab.soft_v.mapper.ExtFieldRelationMapper;
import com.skylab.soft_v.mapper.SoftMapper;
import com.skylab.soft_v.service.ExtFieldRelationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 扩展字段关联表(ExtFieldRelation)表服务实现类
 *
 * @author xw
 * @since 2020-08-12 18:07:46
 */
@Service("extFieldRelationService")
public class ExtFieldRelationServiceImpl implements ExtFieldRelationService {
    @Resource
    private ExtFieldRelationMapper extFieldRelationMapper;
    @Resource
    private CategoryMapper categoryMapper;
    @Resource
    private SoftMapper softMapper;

    private ObjectMapper objectMapper = new ObjectMapper();
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation queryById(Integer id) {
        return this.extFieldRelationMapper.queryById(id);
    }

    /**
     * 查询多条数据并分页
     *
     * @param page  查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public Pager<ExtFieldRelation> queryAllByPage(int page, int limit) {
        Pager<ExtFieldRelation> pager = new Pager<ExtFieldRelation>();
        pager.setRows(this.extFieldRelationMapper.queryAllByPage((page - 1) * limit, limit));
        pager.setTotal(this.extFieldRelationMapper.count());
        return pager;
    }

    /**
     * 查询所有数据
     *
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> queryList() {
        return this.extFieldRelationMapper.queryList();
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param extFieldRelation 实例对象
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> queryByExample(ExtFieldRelation extFieldRelation) {
        return this.extFieldRelationMapper.queryByExample(extFieldRelation);
    }

    /**
     * 通过实体作为筛选条件查询并分页
     *
     * @param extFieldRelation 实例对象
     * @param page             查询起始位置
     * @param limit            查询条数
     * @return 对象列表
     */
    public Pager<ExtFieldRelation> queryByExampleAndPage(ExtFieldRelation extFieldRelation, int page, int limit) {
        List<ExtFieldRelation> list = extFieldRelationMapper.queryByExample(extFieldRelation);
        Pager<ExtFieldRelation> pager = new Pager<ExtFieldRelation>();
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
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation insert(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.insert(extFieldRelation);
        return extFieldRelation;
    }

    /**
     * 新增数据 可以有空字段
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation insertSelective(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.insertSelective(extFieldRelation);
        return extFieldRelation;
    }

    /**
     * 修改数据
     *
     * @param extFieldRelation 实例对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation update(ExtFieldRelation extFieldRelation) {
        this.extFieldRelationMapper.update(extFieldRelation);
        return this.queryById(extFieldRelation.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.extFieldRelationMapper.deleteById(id) > 0;
    }

    @Override
    public List<ExtFieldRelation> queryValidFieldsByCategory(int category) {
        return extFieldRelationMapper.queryValidFieldsByCategory(category);
    }
    /**
     * 查询有效字段并分页
     *
     *
     * @param page
     * @param limit
     * @param condition 查询条件
     * @return 对象列表
     */
    @Override
    public Pager<ExtFieldRelationVO> queryValidFieldsAndPage(int page, int limit, String condition) {
        Pager<ExtFieldRelationVO> pager = new Pager<>();
        List<ExtFieldRelation> extFieldRelations = extFieldRelationMapper.queryValidFields(condition);
        List<ExtFieldRelationVO> extFieldRelationVOS = new ArrayList<>();
        for (ExtFieldRelation extFieldRelation : extFieldRelations){
            ExtFieldRelationVO extFieldRelationVO = new ExtFieldRelationVO();
            extFieldRelationVO.setId(extFieldRelation.getId());
            extFieldRelationVO.setFieldName(extFieldRelation.getFieldName());
            extFieldRelationVO.setFieldDes(extFieldRelation.getFieldDes());
            Category category =  categoryMapper.queryById(extFieldRelation.getCategory());
            extFieldRelationVO.setCategory(category == null?"公有字段":category.getCategoryName());
            extFieldRelationVO.setIsTerm(extFieldRelation.getIsTerm());
            if (extFieldRelation.getValue() != null && !"".equals(extFieldRelation.getValue())){
                List<JsonToObj> jsonToObjs = null;
                try {
                    jsonToObjs = objectMapper.readValue(extFieldRelation.getValue(), new TypeReference<List<JsonToObj>>() {
                    });
                } catch (JsonProcessingException e) {
                    throw new BusinessException(400,"Json转换错误");
                }
                extFieldRelationVO.setValue(jsonToObjs);
                extFieldRelationVO.setType("0");
            }else {
                extFieldRelationVO.setType("1");
            }
            extFieldRelationVOS.add(extFieldRelationVO);
        }
        int count = extFieldRelationVOS.size();
        pager.setTotal(count);
        page = Math.min(page,(count/limit)+1);
        int fromIndex = (page-1) * limit;
        int toIndex = fromIndex + limit;
        pager.setRows(extFieldRelationVOS.subList(fromIndex, Math.min(toIndex, count)));
        return pager;
    }

    /**
     * 修改字段查询状态
     *
     * @param id     id
     * @param isTerm 状态
     * @return 是否成功
     */
    @Override
    public boolean updateIsTerm(Integer id, boolean isTerm) {
        return extFieldRelationMapper.updateIsTerm(id,isTerm) > 0;
    }

    /**
     * 查询无效字段
     *
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> queryInvalidFields() {
        return extFieldRelationMapper.queryInvalidFields();
    }

    /**
     * 停止使用一个字段
     *
     * @param extFieldRelation 字段
     * @return 是否成功
     */
    @Override
    @Transactional
    public boolean stopUse(ExtFieldRelation extFieldRelation) {
        int update = extFieldRelationMapper.stopUse(extFieldRelation.getId());
        softMapper.cleanByColumn(extFieldRelation.getFieldName());
        return update > 0;
    }

    /**
     * 扩展一个字段
     *
     * @param exits 字段对象
     * @return 实例对象
     */
    @Override
    public ExtFieldRelation extendField(ExtFieldRelation exits) {
        extFieldRelationMapper.extendField(exits);
        return this.queryById(exits.getId());
    }

    /**
     * 查询展示字段
     *
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> getShowFields() {
        return extFieldRelationMapper.getShowFields();
    }

    /**
     * 根据业务类型查询
     *
     * @param categoryId 业务id
     * @return 对象列表
     */
    @Override
    public List<ExtFieldRelation> getShowFieldsAndDataByCategory(int categoryId) {
        return extFieldRelationMapper.getShowFieldsAndDataByCategory(categoryId);
    }
}