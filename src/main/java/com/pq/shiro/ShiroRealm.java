package com.pq.shiro;

import com.pq.entity.User;
import com.pq.entity.enums.ErrorEnum;
import com.pq.entity.enums.UserStatusEnum;
import com.pq.mapper.UserMapper;
import com.pq.service.UserService;
import com.pq.util.JWTUtil;
import lombok.extern.log4j.Log4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Log4j
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     * 授权模块，获取用户角色和权限。
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        // 获取userId
        String userId = JWTUtil.getUserId(token.toString());

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        HashMap<String, Object> map = userService.selectMenuListByUserId(userId);

        // 获取用户角色
        Set<String> roleName = Collections.singleton(String.valueOf(map.get("role_name")));
        simpleAuthorizationInfo.setRoles(roleName);

        // 获取用户权限
        String permsList = String.valueOf(map.get("perms_list"));
        List<String> perms = Arrays.asList(permsList.split(","));

        // 获取权限接口
        String urlList = String.valueOf(map.get("url_list"));
        List<String> url = Arrays.asList(urlList.split(","));

        simpleAuthorizationInfo.addStringPermissions(perms);
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证:编写shiro判断逻辑，默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取userId
        String token = (String) authenticationToken.getCredentials();

        String userId = JWTUtil.getUserId(token);

        if (userId == null) {
            throw new AuthenticationException(ErrorEnum.TOKEN_EXCEPTION.getMsg());
        }
        // 获取用户信息
        User user = userService.getById(userId);

        //校验用户是否存在
        if(user == null || user.getStatus() == UserStatusEnum.LOCKED){
            throw new AuthenticationException(ErrorEnum.ACCOUNT_UNUSUAL.getMsg());
        }
        //操作时校验的是非对称加密是否成立.
        if (!JWTUtil.verify(token,user.getUsername(),user.getPassword(),userId)) {
            throw new AuthenticationException(ErrorEnum.TOKEN_EXCEPTION.getMsg());
        }

        return new SimpleAuthenticationInfo(token, token, "ShiroRealm");
    }
}
