package com.pq.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.pq.entity.enums.UserStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_user")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 用户ID
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Long id;

      /**
     * 用户名
     */
      private String username;

    private String nickname;

      /**
     * 邮箱
     */
      private String email;

      /**
     * 头像
     */
      private String avatar;

      /**
     * 联系电话
     */
      private String phone;

      /**
     * 状态 0锁定 1有效
     */
      private UserStatusEnum status;

      /**
     * 性别 0男 1女 2保密
     */
      private Integer sex;

      /**
     * 盐
     */
      private String salt;

      /**
     * 0:超级管理员，1：系统用户
     */
      private Integer type;

      /**
     * 密码
     */
      private String password;

      /**
     * 创建时间
     */
      @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 修改时间
     */
      @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      @TableLogic
      private Integer deleted;
}
