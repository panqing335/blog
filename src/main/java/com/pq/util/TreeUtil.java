package com.pq.util;

import com.pq.entity.TreeNode;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TreeUtil
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/1110:41 上午
 */
@UtilityClass
public class TreeUtil {
    /**
     * 功能描述: <br>
     * 〈两层循环实现建树〉
     * @Param: [treeNodes, root]
     * @Return:
     * @Author: panqing
     * @Date: 2020/8/11 10:56 上午
     */
    public <T extends TreeNode> List<T> build(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            ArrayList<TreeNode> its = new ArrayList<>();
            for (T it : treeNodes) {
                if (it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(its);
                    }
                    its.add(it);
                    treeNode.setChildren(its);
                }
            }
        }
        return trees;
    }

    /**
     * 功能描述: <br>
     * 〈使用递归建树〉
     * @Param: [treeNodes, root]
     * @Return: java.util.List<T>
     * @Author: panqing
     * @Date: 2020/8/11 11:02 上午
     */
    public <T extends TreeNode> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    /**
     * 功能描述: <br>
     * 〈递归找子节点〉
     * @Param: [treeNode, treeNodes]
     * @Return: T
     * @Author: panqing
     * @Date: 2020/8/11 11:02 上午
     */
    public <T extends TreeNode> T findChildren(T treeNode, List<T> treeNodes) {
        ArrayList<TreeNode> its = new ArrayList<>();

        for (T it : treeNodes) {
            if (treeNode.getId() == it.getParentId()) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                its.add(findChildren(it, treeNodes));
                treeNode.setChildren(its);
            }
        }
        return treeNode;
    }

}
