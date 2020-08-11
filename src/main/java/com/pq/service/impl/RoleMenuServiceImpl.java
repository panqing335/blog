package com.pq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pq.dto.MenuCheckedDTO;
import com.pq.entity.Menu;
import com.pq.entity.RoleMenu;
import com.pq.mapper.RoleMenuMapper;
import com.pq.service.MenuService;
import com.pq.service.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pq.util.TreeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色菜单关联表 服务实现类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private MenuService menuService;

    @Override
    public List<Menu> selectMenuTreeByRoleId(String roleId) {

        List<Menu> menus = menuService.list();

        List<Menu> menuByRoleId = this.baseMapper.selectMenuByRoleId(roleId);

        for (Menu menu : menus) {
            menuByRoleId.forEach(menuB -> {
                if (menu.getId().equals(menuB.getId())) {
                    menu.setChecked(1);
                }
            });
        }
        return TreeUtil.build(menus, 0L);
    }

    @Override
    public boolean checked(List<MenuCheckedDTO> checked, String id) {
        checked.forEach(item -> {
            QueryWrapper<RoleMenu> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("role_id", id);
            queryWrapper.eq("menu_id", item.getId());

            RoleMenu roleMenuE = this.getOne(queryWrapper);

            if (item.getType() == 1 && roleMenuE == null) {
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(Long.valueOf(id));
                roleMenu.setMenuId(item.getId());
                this.save(roleMenu);
            }
            if (item.getType() == 2) {
                this.remove(queryWrapper);
            }
        });
        return true;
    }
}
