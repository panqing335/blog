package com.pq.service.impl;

import com.pq.entity.Role;
import com.pq.mapper.RoleMapper;
import com.pq.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public HashMap<String, Object> selectRoleNameByUserId(String userId) {
        return this.baseMapper.selectRoleNameByUserId(userId);
    }
}
