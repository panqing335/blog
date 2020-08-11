package com.pq.mapper;

import com.pq.entity.Role;
import com.pq.entity.User;
import com.pq.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * <p>
 * 用户角色关联表 Mapper 接口
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @Select("select * from tb_user where id in (select user_id from tb_user_role where role_id = #{roleId})")
    HashMap<String, User> selectUserByRoleId(String roleId);

    @Select("select * from tb_role where id in (select role_id from tb_user_role where user_id = #{userId})")
    HashMap<String, Role> selectRoleByUserId(String userId);
}
