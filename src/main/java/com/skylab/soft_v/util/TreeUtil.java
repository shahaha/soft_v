package com.skylab.soft_v.util;



import com.skylab.soft_v.bean.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeUtil {
    public static Tree build1(List<Tree> treeNodes) {
        if(treeNodes == null){
            return null;
        }
        List<Tree> topNodes = new ArrayList<Tree>();
        List<Tree> topNodes2 = new ArrayList<Tree>();

        for (Tree children : treeNodes) {
            String pid = children.getParentId();
            if (pid == null || "".equals(pid) ) {
                topNodes.add(children);
                continue;
            }else if (pid.equals("0")){
                topNodes2.add(children);
            }
            for (Tree parent : treeNodes) {
                int id = parent.getId();
                if (id == Integer.parseInt(pid)) {
                    parent.getChildren().add(children);
                    continue;
                }
            }

        }

        Tree root;
        if (topNodes.size() == 1) {
            root = topNodes.get(0);
        } else {
            root=topNodes2.get(0);
        }
        return root;
    }

    public static List<Tree> build(List<Tree> treeNodes) {
        if(treeNodes == null){
            return null;
        }
        Map<Integer,List<Tree>> children = new HashMap<>();
        List<Tree> roots = new ArrayList<>();
        for (Tree tree : treeNodes){
            if ("0".equals(tree.getParentId())){
                roots.add(tree);
                continue;
            }
            //children.put(Integer.parseInt(tree.getParentId()),new ArrayList<>());
            children.computeIfAbsent(Integer.parseInt(tree.getParentId()), k -> new ArrayList<>());
            children.get(Integer.parseInt(tree.getParentId())).add(tree);
        }
        List<Tree> resultTree = new ArrayList<>();
        for (Tree root : roots){
            resultTree.add(setChild(children,root));
        }
        return resultTree;
    }

    private static Tree setChild(Map<Integer,List<Tree>> map,Tree root){
        if (map.get(root.getId()) == null){
            return root;
        }
        List<Tree> children = new ArrayList<>();
        for (Tree tree : map.get(root.getId())){
            children.add(setChild(map,tree));
        }
        root.setChildren(children);
        return root;
    }
}
