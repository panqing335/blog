package com.pq.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum UserSexEnum {
    WOMEN(0, "妇女"),
    BOY(1, "男孩");

    UserSexEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final int code;
    private final String desc;
}
