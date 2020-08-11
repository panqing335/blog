package com.pq.service;

import com.pq.entity.Role;
import com.pq.entity.User;
import com.pq.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 用户角色关联表 服务类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface UserRoleService extends IService<UserRole> {

    HashMap<String, User> selectUserByRoleId(String roleId);

    HashMap<String, Role> selectRoleByUserId(String userId);
}
