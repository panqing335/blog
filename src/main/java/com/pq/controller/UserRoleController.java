package com.pq.controller;


import com.pq.common.Result;
import com.pq.dto.UserRoleDTO;
import com.pq.entity.UserRole;
import com.pq.service.UserRoleService;
import com.pq.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 用户角色关联表 前端控制器
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Controller
@RequestMapping("/userRole")
public class UserRoleController {

    @Autowired
    protected UserRoleService userRoleService;

    /**
     * 功能描述: <br>
     * 〈修改用户角色〉
     * @Param: [userRoleDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 3:18 下午
     */
    @PostMapping(value = "/save")
    public Result userRole(@RequestBody UserRoleDTO userRoleDTO) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userRoleDTO.getUserId());
        userRole.setRoleId(userRoleDTO.getRoleId());

        boolean ret = userRoleService.save(userRole);
        return Result.success(ret);
    }

}

