package com.yaya.boot.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yaya.boot.exception.GlobalCommonException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * JWT工具
 */
public class JwtUtils {
    //声明算法
    private static final Algorithm HMAC256 = Algorithm.HMAC256("YLWTSMTJFYHDCMGSCWHSSYBZSDKC");
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 签名生成token
     * @param payload 负载
     * @return token
     */
    public static String sign(String payload){
        return JWT.create() //生成令牌函数
                .withIssuedAt(new Date()) //签发时间,添加一个动态时间,防止每次生成的token都相同
                .withIssuer(payload) //自定义负载部分
                .sign(HMAC256);
    }

    /**
     * 校验token
     * @param token 令牌
     * @return 正确返回true 错误直接抛出异常
     */
    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(HMAC256).build();
            //如果正确,直接代码向下执行,如果错误,抛异常
            verifier.verify(token);
        }catch (RuntimeException e){
            LOGGER.error("verify",e);
            throw new GlobalCommonException("TOKEN校验失败");
        }
        return true;
    }

    /**
     * 从token中获取负载
     * @param token 令牌
     * @return 保存的负载
     */
    public static String getClaim(String token){
        DecodedJWT jwt = JWT.decode(token);
        Claim iss = jwt.getClaim("iss");
        return iss.asString();
    }

}
