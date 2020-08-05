package com.pq.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.common.Result;
import com.pq.dto.ListDTO;
import com.pq.entity.Role;
import com.pq.service.RoleService;
import com.pq.util.SearchUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 功能描述: <br>
     * 〈角色列表〉
     * @Param: [listDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/4 4:35 下午
     */
    @GetMapping(path = "/list")
    @RequiresRoles("super")
    public Result list(@RequestBody ListDTO listDTO) {
//        Page<Role> page = new Page<>(listDTO.getPageNo(), listDTO.getPageSize());
//        QueryWrapper<Role> queryWrapper = SearchUtil.parseWhereSql(listDTO.getCondition());
//        queryWrapper.orderByDesc("id");
//
//        Page<Role> list = roleService.page(page, queryWrapper);

        return Result.success(1);
    }

}

