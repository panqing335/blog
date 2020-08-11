package com.pq.mapper;

import com.pq.entity.Menu;
import com.pq.entity.Role;
import com.pq.entity.RoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pq.entity.User;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色菜单关联表 Mapper 接口
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @Select("select * from tb_menu where id in (select menu_id from tb_role_menu where role_id = #{roleId} and deleted = 0) and deleted = 0")
    List<Menu> selectMenuByRoleId(String roleId);
}
