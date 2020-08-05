package com.pq.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.dto.UserListDTO;
import com.pq.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface UserMapper extends BaseMapper<User> {

    @Select("select u.*, r.role_name from tb_user u, tb_user_role ur, tb_role r where u.id=ur.user_id AND ur.role_id=r.id AND u.id = #{userId}")
    HashMap<String, Object> selectRoleNameByUserId(String userId);

    // 根据用户的userId查询出其对应的角色以及角色相关的所有权限
    @Select("select u.id, u.username, u.password,\n" +
            "       GROUP_CONCAT(m.menu_name)menu_name_list,\n" +
            "       GROUP_CONCAT(case m.perms when '' then null else m.perms end order by m.id)perms_list,\n" +
            "       GROUP_CONCAT(case m.url when '' then null else m.url end)url_list,\n" +
            "       GROUP_CONCAT(distinct r.role_name separator ';') role_name\n" +
            "from tb_user u\n" +
            "right join tb_user_role ur on ur.user_id = u.id\n" +
            "right join tb_role r on r.id = ur.role_id\n" +
            "left join tb_role_menu rm on rm.role_id = r.id\n" +
            "left join tb_menu m on m.id = rm.menu_id\n" +
            "where u.id = #{userId}")
    HashMap<String, Object> selectMenuListByUserId(String userId);

    @Select("select u.* from tb_user as u where u.userName=#{username}")
    User selectUserByUsername(String username);

    @Select("select u.*, r.role_name, r.id from tb_user u left join  tb_user_role ur on u.id = ur.user_id left join tb_role r on ur.role_id = r.id ${ew.customSqlSegment}")
    List<UserListDTO> selectUserListPage(Page<UserListDTO> page , @Param(Constants.WRAPPER) Wrapper<User> queryWrapper);
}