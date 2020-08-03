package com.pq.exception;

import com.pq.common.Result;
import com.pq.entity.enums.ErrorEnum;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@RestControllerAdvice
public class ExceptionController {
    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e) {
        return Result.fail(401, e.getMessage(), null);
    }

    // 用户名或密码错误
    @ExceptionHandler
    @ResponseBody
    public Result ErrorHandler(AuthenticationException e) {
        return Result.fail(ErrorEnum.ERROR_ACCOUNT.getCode(),ErrorEnum.ERROR_ACCOUNT.getMsg(), e);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public Result handle401() {
        return Result.fail(401, "Unauthorized", null);
    }

    // 捕获request参数异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result validationBodyException(MethodArgumentNotValidException exception){

        BindingResult bindingResult = exception.getBindingResult();
        HashMap<String, String> errors = new HashMap<>();
        bindingResult.getAllErrors().forEach(o ->{
            FieldError error = (FieldError) o;
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return Result.fail(402, String.valueOf(errors), null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result globalException(HttpServletRequest request, Throwable ex) {
        return Result.fail(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
