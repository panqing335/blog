package com.pq.service;

import com.pq.dto.MenuCheckedDTO;
import com.pq.entity.Menu;
import com.pq.entity.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色菜单关联表 服务类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface RoleMenuService extends IService<RoleMenu> {
    List<Menu> selectMenuTreeByRoleId(String roleId);
    boolean checked(List<MenuCheckedDTO> checked, String id);
}
