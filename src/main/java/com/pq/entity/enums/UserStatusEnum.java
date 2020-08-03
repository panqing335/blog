package com.pq.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ACTIVE(1, "激活"),
    LOCKED(0, "锁定");

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @EnumValue
    private final int code;
    private final String desc;
}
