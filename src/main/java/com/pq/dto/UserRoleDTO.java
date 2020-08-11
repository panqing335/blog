package com.pq.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Title: UserRoleDTO
 * @author: panqing
 * @projectName git-blog
 * @description: TODO
 * @data: 2020/8/1011:05 上午
 */

@Data
public class UserRoleDTO {
    @NotNull(message = "用户名ID为空")
    private Long userId;
    @NotNull(message = "角色ID为空")
    private Long roleId;
}
