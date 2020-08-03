package com.pq.mapper;

import com.pq.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface RoleMapper extends BaseMapper<Role> {
    @Select("select r.role_name from tb_role r\n" +
            "left join tb_user_role ur on r.id = ur.role_id\n" +
            "left join tb_user u on u.id = ur.user_id\n" +
            "    where u.id = #{userId}")
    HashMap<String, Object> selectRoleNameByUserId(String userId);
}
