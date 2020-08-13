package com.skylab.soft_v.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tree {
    /**
     * 节点ID
     */
    private int id;
    /**
     * 显示节点文本
     */
    private String title;
    /**
     * 节点的子节点
     */
    private List<Tree> children = new ArrayList<Tree>();

    /**
     * 父ID
     */
    private String parentId;

    private String checkArr="0";
}
