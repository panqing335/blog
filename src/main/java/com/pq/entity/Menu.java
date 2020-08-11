package com.pq.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("tb_menu")
public class Menu extends TreeNode implements Serializable{

      /**
     * 菜单/按钮ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 上级菜单ID
     */
      private Long parentId;

      /**
     * 菜单/按钮名称
     */
      @TableField(value = "menu_name")
      private String name;

      /**
     * 菜单URL
     */
      private String url;

      /**
     * 权限标识
     */
      private String perms;

      /**
     * 类型 0菜单 1按钮
     */
      private String type;

      /**
     * 排序
     */
      private Long orderNum;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 修改时间
     */
      private LocalDateTime updateTime;

      private Integer deleted;

      @TableField(exist = false)
      private int checked;

      @TableField(exist = false)
      protected List<TreeNode> children = new ArrayList<>();

}
