package com.pq.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 角色菜单关联表
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("tb_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
      private Long roleId;

      /**
     * 菜单/按钮ID
     */
      private Long menuId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer deleted;


}
