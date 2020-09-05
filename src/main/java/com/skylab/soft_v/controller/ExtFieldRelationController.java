package com.skylab.soft_v.controller;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skylab.soft_v.bean.ExtFieldRelationVO;
import com.skylab.soft_v.bean.JsonToObj;
import com.skylab.soft_v.common.BusinessException;
import com.skylab.soft_v.common.Pager;
import com.skylab.soft_v.common.ResultBean;
import com.skylab.soft_v.component.ActionLog;
import com.skylab.soft_v.entity.*;
import com.skylab.soft_v.service.*;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 扩展字段关联表(ExtFieldRelation)表控制层
 *
 * @author xw
 * @since 2020-08-13 10:37:25
 */
@RestController
@RequestMapping("extFieldRelation")
@Api(tags = "扩展字段关联表")
@Slf4j
public class ExtFieldRelationController {
    /**
     * 服务对象
     */
    @Resource
    private ExtFieldRelationService extFieldRelationService;
    @Resource
    private ModelService modelService;
    @Resource
    private ChipService chipService;
    @Resource
    private ByteSizeService byteSizeService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private SoftToolService softToolService;

    private ObjectMapper objectMapper = new ObjectMapper();


    /**
     * 查询展示字段
     *
     * @return 响应数据
     */
    @GetMapping("getShowFields")
    @RequiresPermissions("field_select")
    public ResultBean<List<ExtFieldRelation>> getShowFields() {
        List<ExtFieldRelation> extFieldRelations = extFieldRelationService.getShowFields();
        for (ExtFieldRelation extFieldRelation : extFieldRelations) {
            extFieldRelation.setFieldName(StrUtil.toCamelCase(extFieldRelation.getFieldName()));
            if ("gpioDetection".equals(extFieldRelation.getFieldName())) {
                extFieldRelation.setFieldName("GPIODetection");
            }
        }
        return ResultBean.success(extFieldRelations);
    }


    /**
     * 查询展示字段
     *
     * @return 响应数据
     */
    @GetMapping("getShowFieldsAndDataByCategory")
    @RequiresPermissions("field_select")
    public ResultBean<List<ExtFieldRelationVO>> getShowFieldsAndDataByCategory(int categoryId) throws JsonProcessingException {
        List<ExtFieldRelation> extFieldRelations = extFieldRelationService.getShowFieldsAndDataByCategory(categoryId);
        List<ExtFieldRelationVO> extFieldRelationVOS = fieldsToFieldsVO(extFieldRelations);
        return ResultBean.success(extFieldRelationVOS);
    }

    /**
     * 分页查询有效字段
     *
     * @param page  当前页
     * @param limit 每页行数
     * @return 响应数据
     */
    @GetMapping("getValidFieldsAndPage")
    @RequiresPermissions("field_select")
    public ResultBean<Pager<ExtFieldRelationVO>> getValidFieldsAndPage(int page, int limit, String condition) {
        Pager<ExtFieldRelationVO> pager = extFieldRelationService.queryValidFieldsAndPage(page, limit, condition);
        return ResultBean.success(pager);
    }

    /**
     * 查询无效字段记录
     *
     * @return 响应数据
     */
    @GetMapping("getInvalidFields")
    @RequiresPermissions("field_select")
    public ResultBean<List<ExtFieldRelation>> getInvalidFields() {
        List<ExtFieldRelation> extFieldRelations = extFieldRelationService.queryInvalidFields();
        return ResultBean.success(extFieldRelations);
    }

    /**
     * 更新一条记录
     *
     * @param extFieldRelation 更新实体
     * @return 响应数据
     */
    @PostMapping("update")
    @ActionLog("扩展或修改一个字段")
    @RequiresPermissions("field_update")
    public ResultBean<ExtFieldRelation> update(ExtFieldRelation extFieldRelation) {
        if (extFieldRelation.getId() == null) {
            return ResultBean.error("id不能为空！");
        }
        ExtFieldRelation exits = extFieldRelationService.queryById(extFieldRelation.getId());
        if (exits == null) {
            return ResultBean.error("字段不存在！");
        }
        if (!extFieldRelation.getFieldName().startsWith("column")) {
            return ResultBean.error("该字段不可修改！");
        }
        if (extFieldRelation.getFieldDes() == null || "".equals(extFieldRelation.getFieldDes())) {
            return ResultBean.error("字段描述不能为空！");
        }
        if (extFieldRelation.getCategory() == null) {
            return ResultBean.error("业务类型不能为空！");
        }
        try {
            exits.setCategory(extFieldRelation.getCategory());
            exits.setFieldDes(extFieldRelation.getFieldDes());
            exits.setIsTerm(extFieldRelation.getIsTerm());
            exits.setValue(extFieldRelation.getValue());
            ExtFieldRelation update = extFieldRelationService.extendField(exits);
            return ResultBean.success(update);
        } catch (Exception e) {
            throw new BusinessException(400,e.getMessage());
        }
    }

    /**
     * 修改字段可查询状态
     *
     * @param id     字段id
     * @param isTerm 是否可查
     * @return 响应数据
     */
    @PostMapping("updateIsTerm")
    @ActionLog("修改字段可查询状态")
    @RequiresPermissions("field_update")
    public ResultBean<ExtFieldRelation> updateIsTerm(Integer id, boolean isTerm) {
        boolean b = extFieldRelationService.updateIsTerm(id, isTerm);
        if (b) {
            return ResultBean.success();
        } else {
            return ResultBean.error("修改失败");
        }
    }

    /**
     * 停止使用字段
     *
     * @param id id
     * @return 响应数据
     */
    @PostMapping("stopUse")
    @ActionLog("停止使用字段并清空有关该字段的内容")
    @RequiresPermissions("field_update")
    public ResultBean<ExtFieldRelation> stopUse(Integer id) {
        ExtFieldRelation extFieldRelation = extFieldRelationService.queryById(id);
        if (id == null) {
            return ResultBean.error("id不能为空！");
        }
        if (extFieldRelation == null) {
            return ResultBean.error("字段不存在！");
        }
        if (extFieldRelation.getFieldName() == null || !extFieldRelation.getFieldName().startsWith("column")) {
            return ResultBean.error("该字段不可修改，只能修改扩展字段！");
        }
        boolean b = extFieldRelationService.stopUse(extFieldRelation);
        if (b) {
            return ResultBean.success();
        } else {
            return ResultBean.error("修改失败");
        }
    }


    /**
     * extFieldRelations to ExtFieldRelationVOS
     *
     * @param extFieldRelations extFieldRelations list
     * @return ExtFieldRelationVOS list
     * @throws JsonProcessingException 扩展字段的value转对象时可能出现的异常
     */
    public List<ExtFieldRelationVO> fieldsToFieldsVO(List<ExtFieldRelation> extFieldRelations) throws JsonProcessingException {
        List<ByteSize> byteSizeList = byteSizeService.queryList();
        //字节列表
        List<JsonToObj> byteSizes = new ArrayList<>();
        for (ByteSize byteSize : byteSizeList) {
            byteSizes.add(new JsonToObj(byteSize.getId().toString(), byteSize.getSize()));
        }
        List<ExtFieldRelationVO> extFieldRelationVOS = new ArrayList<>();
        for (ExtFieldRelation extFieldRelation : extFieldRelations) {
            extFieldRelation.setFieldName(StrUtil.toCamelCase(extFieldRelation.getFieldName()));
            ExtFieldRelationVO extFieldRelationVO = new ExtFieldRelationVO();
            extFieldRelationVO.setId(extFieldRelation.getId());
            extFieldRelationVO.setFieldDes(extFieldRelation.getFieldDes());
            extFieldRelationVO.setFieldName(extFieldRelation.getFieldName());
            extFieldRelationVO.setIsTerm(extFieldRelation.getIsTerm());
            if (extFieldRelation.getValue() == null || "".equals(extFieldRelation.getValue())) {
                if ("modelId".equals(extFieldRelation.getFieldName())) {
                    List<Model> modelList = modelService.queryList();
                    List<JsonToObj> models = new ArrayList<>();
                    for (Model model : modelList) {
                        models.add(new JsonToObj(model.getId().toString(), model.getModelName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(models);
                    continue;
                } else if ("chipId".equals(extFieldRelation.getFieldName())) {
                    List<Chip> chipList = chipService.queryList();
                    List<JsonToObj> chips = new ArrayList<>();
                    List<JsonToObj> collect = chipList.stream().map(chip -> new JsonToObj(chip.getId().toString(), chip.getChipName())).collect(Collectors.toList());
                    for (Chip chip : chipList) {
                        chips.add(new JsonToObj(chip.getId().toString(), chip.getChipName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(chips);
                    continue;
                } else if ("flash".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(byteSizes);
                    continue;
                } else if ("DDR".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(byteSizes);
                    continue;
                } else if ("IRAM".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(byteSizes);
                } else if ("customHardware".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(yesOrNot());
                } else if ("needBurnFirmware".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(yesOrNot());
                }else if ("inUse".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(yesOrNot());
                } else if ("openShortDetection".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(yesOrNot());
                } else if ("frequency".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(frequencyList());
                } else if ("accuracy".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(accuracyList());
                } else if ("ephemeris".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(yesOrNot());
                } else if ("engineer".equals(extFieldRelation.getFieldName())) {
                    //工程师列表
                    List<User> engineerList = userService.queryByRole("engineer");
                    List<JsonToObj> engineers = new ArrayList<>();
                    for (User user : engineerList) {
                        engineers.add(new JsonToObj(user.getId().toString(), user.getRealName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(engineers);
                } else if ("sale".equals(extFieldRelation.getFieldName())) {
                    //销售列表
                    List<User> saleList = userService.queryByRole("sale");
                    List<JsonToObj> sales = new ArrayList<>();
                    for (User user : saleList) {
                        sales.add(new JsonToObj(user.getId().toString(), user.getRealName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(sales);
                } else if ("WAN".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(wanList());
                } else if ("baudRate".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(baudList());
                } else if ("TCXO".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(TCXOList());
                } else if ("category".equals(extFieldRelation.getFieldName())) {
                    List<Category> categoryList = categoryService.queryList();
                    List<JsonToObj> categories = new ArrayList<>();
                    for (Category category : categoryList) {
                        categories.add(new JsonToObj(category.getId().toString(), category.getCategoryName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(categories);
                } else if ("productTestTool".equals(extFieldRelation.getFieldName())) {
                    List<SoftTool> softToolList = softToolService.queryByType(1);
                    List<JsonToObj> softTools = new ArrayList<>();
                    for (SoftTool softTool : softToolList) {
                        softTools.add(new JsonToObj(softTool.getId().toString(), softTool.getName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(softTools);
                } else if ("gpioDetection".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setFieldName("GPIODetection");
                    List<SoftTool> softToolList = softToolService.queryByType(2);
                    List<JsonToObj> softTools = new ArrayList<>();
                    for (SoftTool softTool : softToolList) {
                        softTools.add(new JsonToObj(softTool.getId().toString(), softTool.getName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(softTools);
                } else if ("burnTool".equals(extFieldRelation.getFieldName())) {
                    List<SoftTool> softToolList = softToolService.queryByType(3);
                    List<JsonToObj> softTools = new ArrayList<>();
                    for (SoftTool softTool : softToolList) {
                        softTools.add(new JsonToObj(softTool.getId().toString(), softTool.getName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(softTools);
                } else if ("detectionTool".equals(extFieldRelation.getFieldName())) {
                    List<SoftTool> softToolList = softToolService.queryByType(4);
                    List<JsonToObj> softTools = new ArrayList<>();
                    for (SoftTool softTool : softToolList) {
                        softTools.add(new JsonToObj(softTool.getId().toString(), softTool.getName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(softTools);
                } else if ("labelPrintTool".equals(extFieldRelation.getFieldName())) {
                    List<SoftTool> softToolList = softToolService.queryByType(5);
                    List<JsonToObj> softTools = new ArrayList<>();
                    for (SoftTool softTool : softToolList) {
                        softTools.add(new JsonToObj(softTool.getId().toString(), softTool.getName()));
                    }
                    extFieldRelationVO.setType("select");
                    extFieldRelationVO.setValue(softTools);
                } else if ("uploadDate".equals(extFieldRelation.getFieldName())) {
                    extFieldRelationVO.setType("date");
                } else {
                    extFieldRelationVO.setType("text");
                }
            } else {
                List<JsonToObj> jsonToObjs = objectMapper.readValue(extFieldRelation.getValue(), new TypeReference<List<JsonToObj>>() {
                });
                extFieldRelationVO.setType("select");
                extFieldRelationVO.setValue(jsonToObjs);
            }
            extFieldRelationVOS.add(extFieldRelationVO);
        }
        return extFieldRelationVOS;
    }

    /**
     * yesOrNot
     *
     * @return list of yes and not
     */
    public List<JsonToObj> yesOrNot() {
        List<JsonToObj> yesOrNot = new ArrayList<>();
        yesOrNot.add(new JsonToObj("0", "否"));
        yesOrNot.add(new JsonToObj("1", "是"));
        return yesOrNot;
    }

    /**
     * TCXOList
     *
     * @return list of TCXO
     */
    public List<JsonToObj> TCXOList() {
        List<JsonToObj> TCXOList = new ArrayList<>();
        TCXOList.add(new JsonToObj("26/16.368M", "26/16.368M"));
        TCXOList.add(new JsonToObj("26M", "26M"));
        TCXOList.add(new JsonToObj("16.368M", "16.368M"));
        return TCXOList;
    }

    /**
     * wanList
     *
     * @return list of wan
     */
    public List<JsonToObj> wanList() {
        List<JsonToObj> wanList = new ArrayList<>();
        wanList.add(new JsonToObj("P0", "P0"));
        wanList.add(new JsonToObj("P1", "P1"));
        wanList.add(new JsonToObj("P2", "P2"));
        wanList.add(new JsonToObj("P3", "P3"));
        wanList.add(new JsonToObj("P4", "P4"));
        wanList.add(new JsonToObj("LAN", "LAN"));
        wanList.add(new JsonToObj("WAN", "WAN"));
        return wanList;
    }

    /**
     * baudList
     *
     * @return list of baud rate
     */
    public List<JsonToObj> baudList() {
        List<JsonToObj> baudList = new ArrayList<>();
        baudList.add(new JsonToObj("1200", "1200"));
        baudList.add(new JsonToObj("2400", "2400"));
        baudList.add(new JsonToObj("4800", "4800"));
        baudList.add(new JsonToObj("9600", "9600"));
        baudList.add(new JsonToObj("14400", "14400"));
        baudList.add(new JsonToObj("19200", "19200"));
        baudList.add(new JsonToObj("38400", "38400"));
        baudList.add(new JsonToObj("43000", "43000"));
        baudList.add(new JsonToObj("57600", "57600"));
        baudList.add(new JsonToObj("76800", "76800"));
        baudList.add(new JsonToObj("115200", "115200"));
        baudList.add(new JsonToObj("128000", "128000"));
        baudList.add(new JsonToObj("230400", "230400"));
        baudList.add(new JsonToObj("256000", "256000"));
        baudList.add(new JsonToObj("460800", "460800"));
        baudList.add(new JsonToObj("921600", "921600"));
        baudList.add(new JsonToObj("1382400", "1382400"));
        return baudList;
    }

    /**
     * 通过类型查询有效字段记录
     *
     * @return 响应数据
     */
    @GetMapping("getValidFieldsByCategory")
    @RequiresPermissions("field_select")
    public ResultBean<List<ExtFieldRelation>> getValidFieldsByCategory(int category) {
        List<ExtFieldRelation> extFieldRelations = extFieldRelationService.queryValidFieldsByCategory(category);
        for (int i = 0; i < extFieldRelations.size(); i++) {
//            System.out.println(extFieldRelations.get(i).getFieldName());
            //剔除一些字段不展示
            if (extFieldRelations.get(i).getFieldName().endsWith("tool") || "id".equals(extFieldRelations.get(i).getFieldName()) || "address".equals(extFieldRelations.get(i).getFieldName()) || "category".equals(extFieldRelations.get(i).getFieldName()) || "GPIO_detection".equals(extFieldRelations.get(i).getFieldName())) {
                extFieldRelations.remove(i--);
            } else {
                extFieldRelations.get(i).setFieldName(StrUtil.toCamelCase(extFieldRelations.get(i).getFieldName()));
            }
        }
        return ResultBean.success(extFieldRelations);
    }

    /**
     * frequencyList
     *
     * @return frequency of baud rate
     */
    public List<JsonToObj> frequencyList() {
        List<JsonToObj> frequencyList = new ArrayList<>();
        frequencyList.add(new JsonToObj("1HZ", "1HZ"));
        frequencyList.add(new JsonToObj("2HZ", "2HZ"));
        frequencyList.add(new JsonToObj("3HZ", "3HZ"));
        frequencyList.add(new JsonToObj("4HZ", "4HZ"));
        frequencyList.add(new JsonToObj("5HZ", "5HZ"));
        return frequencyList;
    }

    /**
     * accuracyList
     *
     * @return accuracy of baud rate
     */
    public List<JsonToObj> accuracyList() {
        List<JsonToObj> accuracyList = new ArrayList<>();
        accuracyList.add(new JsonToObj("0", "0"));
        accuracyList.add(new JsonToObj("2", "2"));
        accuracyList.add(new JsonToObj("4", "4"));
        accuracyList.add(new JsonToObj("6", "6"));
        accuracyList.add(new JsonToObj("8", "8"));
        return accuracyList;
    }

}