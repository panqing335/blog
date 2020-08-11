package com.pq.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: TreeNode
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/1110:51 上午
 */
@Data
public class TreeNode {
    protected Long id;
    protected Long parentId;
    protected String name;
    protected List<TreeNode> children;
}
