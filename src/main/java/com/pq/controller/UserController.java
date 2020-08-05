package com.pq.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.common.Constant;
import com.pq.common.Result;
import com.pq.dto.*;
import com.pq.entity.Role;
import com.pq.entity.User;
import com.pq.entity.enums.ErrorEnum;
import com.pq.entity.enums.UserSexEnum;
import com.pq.exception.SysException;
import com.pq.service.UserService;
import com.pq.util.CommonsUtils;
import com.pq.util.SearchUtil;
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

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.validation.constraints.NotBlank;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static org.apache.shiro.SecurityUtils.getSubject;


@RestController
@RequestMapping("/user")
@Log4j
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 功能描述: <br>
     * 〈测试角色〉
     * @Param: []
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:36 下午
     */
    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public Result requireRole() {
        return Result.success(200, "You are visiting require_role", null);
    }

    /**
     * 功能描述: <br>
     * 〈测试权限〉
     * @Param: []
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:36 下午
     */
    @GetMapping("/require_permission")
    @RequiresPermissions(value = {"user:edit"})
    public Result requirePermission() {
        return Result.success(200, "You are visiting permission require edit,view", null);
    }

    /**
     * 功能描述: <br>
     * 〈权限不足〉
     * @Param: []
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:37 下午
     */
    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.success(401, "Unauthorized", null);
    }



    /**
     * 功能描述: <br>
     * 〈登录接口〉
     * @Param: [loginDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:37 下午
     */
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

    /**
     * 功能描述: <br>
     * 〈登出〉
     * @Param: []
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:38 下午
     */
    @RequiresAuthentication
    @GetMapping("/logout")
    public Result logout() {
        getSubject().logout();
        return Result.success(null);
    }

    /**
     * 功能描述: <br>
     * 〈用户列表〉
     * @Param: [request]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/4 11:12 上午
     */
    @RequiresRoles("super")
    @GetMapping("/list")
    public Result list(@RequestBody ListDTO listDTO) {
        Page<UserListDTO> page = new Page<>(listDTO.getPageNo(), listDTO.getPageSize());
        Page<UserListDTO> list = userService.selectUserListPage(page, listDTO.getCondition());

        return Result.success(list);
    }

    /**
     * 功能描述: <br>
     * 〈用户注册接口〉
     * @Param: [registerDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/3 4:38 下午
     */
    @PostMapping(value = "/register",name = "用户注册")
    public Result userRegister(@RequestBody @Valid RegisterDTO registerDTO) throws Exception {
        try {
            Boolean register = userService.register(registerDTO.getUsername(),
                                                    registerDTO.getPassword(),
                                                    registerDTO.getEmail(),
                                                    registerDTO.getPhone(),
                                                    registerDTO.getSex(),
                                                    registerDTO.getAvatar(),
                                                    registerDTO.getNickname()
                                                    );

            return Result.success(register);
        } catch (SysException e) {
            System.out.println(e.getCode());
            throw new SysException(e.getCode(), e.getMessage());
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * 功能描述: <br>
     * 〈修改用户基本信息〉
     * @Param: [registerDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/4 10:17 上午
     */
    @PostMapping(value = "/update")
    @RequiresRoles("super")
//    @RequiresPermissions(value = {"user:edit"})
    public Result updateUser(@RequestBody RegisterDTO registerDTO) {

        boolean update = userService.updateUser(registerDTO);

        return Result.success(update);
    }

    /**
     * 功能描述: <br>
     * 〈删除用户〉
     * @Param: [id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/4 10:17 上午
     */
    @PostMapping(value = "/delete")
    @RequiresRoles("super")
//    @RequiresPermissions(value = {"user:del"})
    public Result delUser(@RequestBody String id) {

        boolean del = userService.removeById(id);

        return Result.success(del);
    }
}

