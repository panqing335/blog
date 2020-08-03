package com.pq.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @NotNull(message = "用户密码登录传递的username不能为空")
    private String username;
    @NotBlank(message = "用户密码登录传递的密码名称不能为空")
    private String password;
}
