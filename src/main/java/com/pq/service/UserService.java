package com.pq.service;

import com.pq.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pq.entity.enums.ErrorEnum;
import com.pq.util.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;

import java.util.HashMap;

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
    Boolean register(String username, String password, String email, String phone, Integer sex);
}
