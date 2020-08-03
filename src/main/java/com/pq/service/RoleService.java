package com.pq.service;

import com.pq.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
public interface RoleService extends IService<Role> {
    HashMap<String, Object> selectRoleNameByUserId(String userId);
}
