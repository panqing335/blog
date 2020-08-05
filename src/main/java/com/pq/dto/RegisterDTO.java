package com.pq.dto;

import com.pq.entity.enums.UserSexEnum;
import com.pq.entity.enums.UserStatusEnum;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    private String email;

    private String avatar;

    private String phone;

    private UserSexEnum sex;

    private String nickname;

    private UserStatusEnum status;
}
