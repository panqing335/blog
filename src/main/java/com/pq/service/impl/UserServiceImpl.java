package com.pq.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pq.dto.ConditionDTO;
import com.pq.dto.RegisterDTO;
import com.pq.dto.UserListDTO;
import com.pq.entity.User;
import com.pq.entity.enums.ErrorEnum;
import com.pq.entity.enums.UserSexEnum;
import com.pq.entity.enums.UserStatusEnum;
import com.pq.mapper.UserMapper;
import com.pq.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pq.util.CommonsUtils;
import com.pq.util.JWTUtil;
import com.pq.util.SearchUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

import static org.apache.shiro.SecurityUtils.getSubject;

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

        return JWTUtil.sign(username, password, String.valueOf(user.getId()));
    }

    @Override
    public Boolean register(String username, String password, String email, String phone, UserSexEnum sex, String avatar, String nickname) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(CommonsUtils.encryptPassword(password));
        user.setEmail(email);
        user.setPhone(phone);
        user.setSex(sex);
        user.setAvatar(avatar);
        user.setStatus(UserStatusEnum.ACTIVE);
        user.setNickname(nickname);
        this.baseMapper.insert(user);

        return true;
    }

    @Override
    public Boolean updateUser(RegisterDTO registerDTO) {
        // 获取用户信息
        User user = (User) getSubject().getPrincipal();
        // 更新字段
        user.setEmail(registerDTO.getEmail());
        user.setSex(registerDTO.getSex());
        user.setNickname(registerDTO.getNickname());
        user.setAvatar(registerDTO.getAvatar());
        user.setPhone(registerDTO.getPhone());
        // 密码加密
        user.setPassword(CommonsUtils.encryptPassword(registerDTO.getPassword()));
        user.setStatus(registerDTO.getStatus());

        updateById(user);

        return true;
    }

    @Override
    public Page<UserListDTO> selectUserListPage(Page<UserListDTO> page , List<ConditionDTO> condition) {
        QueryWrapper<User> wrapper = SearchUtil.parseWhereSql(condition, new User());
        LambdaQueryWrapper<User> lambda = wrapper.lambda();
        return page.setRecords(this.baseMapper.selectUserListPage(page, lambda));
    }
}
