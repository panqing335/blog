package com.pq.service.impl;

import com.pq.entity.User;
import com.pq.entity.enums.ErrorEnum;
import com.pq.entity.enums.UserStatusEnum;
import com.pq.mapper.UserMapper;
import com.pq.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pq.util.CommonsUtils;
import com.pq.util.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author pq
 * @since 2020-07-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public HashMap<String, Object> selectRoleNameByUserId(String userId) {
        return this.baseMapper.selectRoleNameByUserId(userId);
    }

    @Override
    public HashMap<String, Object> selectMenuListByUserId(String userId) {
        return this.baseMapper.selectMenuListByUserId(userId);
    }

    @Override
    public User selectUserByUsername(String username) {
        return this.baseMapper.selectUserByUsername(username);
    }

    @Override
    public String doLogin(String username, String password) {
        User user = this.selectUserByUsername(username);
        if (user == null) throw new AuthenticationException(ErrorEnum.ACCOUNT_UNUSUAL.getMsg());
        if (!user.getPassword().equals(CommonsUtils.encryptPassword(password))) throw new AuthenticationException(ErrorEnum.ERROR_ACCOUNT.getMsg());
        String token = JWTUtil.sign(username, password, String.valueOf(user.getId()));

        return token;
    }

    @Override
    public Boolean register(String username, String password, String email, String phone, Integer sex) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(CommonsUtils.encryptPassword(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setAvatar("");
        user.setStatus(UserStatusEnum.ACTIVE);
        user.setNickname(username);
        this.baseMapper.insert(user);

        return true;
    }

}
