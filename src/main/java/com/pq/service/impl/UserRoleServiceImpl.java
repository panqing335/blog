package com.pq.service.impl;

import com.pq.entity.Role;
import com.pq.entity.User;
import com.pq.entity.UserRole;
import com.pq.mapper.UserRoleMapper;
import com.pq.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 用户角色关联表 服务实现类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public HashMap<String, User> selectUserByRoleId(String roleId) {
        return this.baseMapper.selectUserByRoleId(roleId);
    }

    @Override
    public HashMap<String, Role> selectRoleByUserId(String userId) {
        return this.baseMapper.selectRoleByUserId(userId);
    }
}
