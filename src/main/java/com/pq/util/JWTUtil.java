package com.pq.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.pq.common.Constant;
import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Log4j
public class JWTUtil {
    /**
     * 生成 token
     * @param username 用户名
     * @param secret 用户的密码
     * @return token 加密的token
     */
    public static String sign(String username, String secret, String userId) {
        try {
            Date date = new Date(System.currentTimeMillis() + Constant.TOKEN_EXPIRE_TIME);
            username = StringUtils.lowerCase(username);
            Algorithm algorithm = Algorithm.HMAC256(CommonsUtils.encryptPassword(secret));

            return JWT.create()
                    .withClaim(Constant.TOKEN_CLAIM, username)
                    .withSubject(userId)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    /**
     * 校验 token是否正确
     *
     * @param token 密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verify(String token, String username, String secret, String userId) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(Constant.TOKEN_CLAIM, username)
                    .withSubject(userId)
                    .build();

            verifier.verify(token);
            return true;
        } catch (SignatureVerificationException | TokenExpiredException e) {
            log.info("token is invalid: "+e.getMessage());
            return false;
        }
    }

    public static String getUsername(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER_NAME);
        return getUsername(token);
    }

    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim(Constant.TOKEN_CLAIM).asString();
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getUserId(HttpServletRequest request) {
        String token = request.getHeader(Constant.TOKEN_HEADER_NAME);
        return getUserId(token);
    }

    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return (jwt.getSubject());
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static String getExpiresAt(HttpServletRequest request) {
        String token = request.getHeader("exp");
        return getExpiresAt(token);
    }

    public static String getExpiresAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return String.valueOf(jwt.getExpiresAt());
        } catch (JWTDecodeException e) {
            log.error(e.getMessage());
            return null;
        }
    }

}
