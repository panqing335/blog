package com.pq.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.common.Result;
import com.pq.dto.ListDTO;
import com.pq.dto.MenuCheckedDTO;
import com.pq.dto.RoleDTO;
import com.pq.entity.Menu;
import com.pq.entity.Role;
import com.pq.entity.User;
import com.pq.exception.SysException;
import com.pq.service.RoleMenuService;
import com.pq.service.RoleService;
import com.pq.service.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;

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

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleMenuService roleMenuService;

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

        Page<Role> page = new Page<>(listDTO.getPageNo(), listDTO.getPageSize());
        Page<Role> list = roleService.page(page);

        return Result.success(list);
    }

    /**
     * 功能描述: <br>
     * 〈添加修改角色〉
     * @Param: [roleDTO]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/10 11:32 上午
     */
    @PostMapping(value = "/save")
    public Result save(@RequestBody RoleDTO roleDTO) {
        Role role = new Role();
        if (roleDTO.getId() != null) {
            role.setId(roleDTO.getId());
        }
        role.setRoleName(roleDTO.getRoleName());
        role.setRemark(roleDTO.getRemark());
        role.setStatus(roleDTO.getStatus());
        boolean res = roleService.save(role);

        return Result.success(res);
    }

    /**
     * 功能描述: <br>
     * 〈删除角色〉
     * @Param: [id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/10 11:51 上午
     */
    @PostMapping(value = "/delete")
    public Result delete(@RequestBody String id) {
        HashMap<String, User> user = userRoleService.selectUserByRoleId(id);
        if (!user.isEmpty()) {
            throw new SysException(400,"用户绑定，无法删除");
        }
        boolean res = roleService.removeById(id);

        return Result.success(res);
    }

    /**
     * 功能描述: <br>
     * 〈获取menu树结构〉
     * @Param: [id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 2:02 下午
     */
    @GetMapping(path = "/menu_tree")
    public Result getMenuTree(@RequestBody String id) {
        List<Menu> tree = roleMenuService.selectMenuTreeByRoleId(id);
        return Result.success(tree);
    }

    /**
     * 功能描述: <br>
     * 〈添加删除角色权限〉
     * @Param: [checked, id]
     * @Return: com.pq.common.Result
     * @Author: panqing
     * @Date: 2020/8/11 3:17 下午
     */
    @PostMapping(value = "/menu_tree")
    public Result editMenuTree(@RequestBody List<MenuCheckedDTO> checked, String id) {
        boolean res = roleMenuService.checked(checked, id);
        return Result.success(res);
    }
}

