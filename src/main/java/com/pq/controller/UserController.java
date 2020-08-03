package com.pq.controller;


import com.pq.common.Result;
import com.pq.dto.LoginDTO;
import com.pq.dto.RegisterDTO;
import com.pq.entity.enums.ErrorEnum;
import com.pq.exception.SysException;
import com.pq.service.UserService;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;

import static org.apache.shiro.SecurityUtils.getSubject;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@RestController
@RequestMapping("/user")
@Log4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Result requireRole() {
        return Result.success(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(value = {"user:edit"})
    public Result requirePermission() {
        return Result.success(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.success(401, "Unauthorized", null);
    }


    @PostMapping(value = "/doLogin",name = "用户密码登录")
    public Result doLogin(@Valid @RequestBody LoginDTO loginDTO) throws Exception {
        log.info("传递的请求参数:" + loginDTO);
        try {
            String token = userService.doLogin(loginDTO.getUsername(),loginDTO.getPassword());
            return Result.success(token);
        }catch (AuthenticationException e){
            throw e;
        }catch (Exception e){
            log.info("错误信息:{}",e);
            throw new Exception(ErrorEnum.SERVER_ERROR.getMsg());
        }
    }

    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        getSubject().logout();
        return Result.success(null);
    }

    //此为shiro开放端口
    @PostMapping(value = "/register",name = "用户注册")
    public Result userRegister(@RequestBody @Valid RegisterDTO registerDTO) throws Exception {
        try {
            Boolean register = userService.register(registerDTO.getUsername(),
                                                    registerDTO.getPassword(),
                                                    registerDTO.getEmail(),
                                                    registerDTO.getPhone(),
                                                    registerDTO.getSex()
                                                    );

            return Result.success(register);
        } catch (SysException e) {
            System.out.println(e.getCode());
            throw new SysException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
}

