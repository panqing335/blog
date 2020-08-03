package com.pq.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
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
  @EqualsAndHashCode(callSuper = false)
    @TableName("tb_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID=1L;

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
      private String menuName;

      /**
     * 菜单URL
     */
      private String url;

      /**
     * 权限标识
     */
      private String perms;

      /**
     * 图标
     */
      private String icon;

      /**
     * 类型 0菜单 1按钮
     */
      private String type;

      /**
     * 排序
     */
      private Long orderNum;

      /**
     * 0：不可用，1：可用
     */
      private Integer available;

      /**
     * 0:不展开，1：展开
     */
      private Integer open;

      /**
     * 创建时间
     */
      private LocalDateTime createTime;

      /**
     * 修改时间
     */
      private LocalDateTime updateTime;

    private Integer deleted;


}
