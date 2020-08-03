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
 * 用户角色关联表
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    @TableName("tb_user_role")
public class UserRole implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
      private Long userId;

      /**
     * 角色ID
     */
      private Long roleId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer deleted;


}
