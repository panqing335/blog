package com.pq.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.dto.ConditionDTO;
import com.pq.dto.RegisterDTO;
import com.pq.dto.UserListDTO;
import com.pq.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pq.entity.enums.UserSexEnum;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface UserService extends IService<User> {
    HashMap<String,Object> selectRoleNameByUserId(String userId);
    HashMap<String, Object> selectMenuListByUserId(String userId);
    User selectUserByUsername(String username);
    String doLogin(String username, String password);
    Boolean register(String username, String password, String email, String phone, UserSexEnum sex, String avatar, String nickname);
    Boolean updateUser(RegisterDTO registerDTO);
    Page<UserListDTO> selectUserListPage(Page<UserListDTO> page , List<ConditionDTO> condition);
}
