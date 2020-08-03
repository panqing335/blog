package com.pq.exception;

import com.pq.entity.enums.ErrorEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 自定义业务异常
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SysException extends RuntimeException {
    private int code;
    private String message;

    public SysException(ErrorEnum errorEnum) {
        this.code = errorEnum.getCode();
        this.message = errorEnum.getMsg();
    }

    public SysException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}