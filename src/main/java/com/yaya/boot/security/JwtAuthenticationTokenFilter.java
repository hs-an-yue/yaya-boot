package com.yaya.boot.security;

import cn.hutool.json.JSONUtil;
import com.yaya.boot.utils.JwtUtils;
import com.yaya.boot.utils.RedisClient;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

/**
 * 校验和解析token的过滤器（统一token处理的过滤器）
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);
    @Resource
    private RedisClient redisClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            //获取token
            String token = request.getHeader("Authorization");
            if(StringUtils.hasText(token)){
                //获取token中的负载
                String phone_ = JwtUtils.getClaim(token);
                String key="login_user:"+phone_;
                String json = redisClient.get(key);
                if(StringUtils.hasText(json)){
                    LoginUserDetails userDetails = JSONUtil.toBean(json, LoginUserDetails.class);
                    if(Objects.nonNull(userDetails)){
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }else {
                        SecurityContextHolder.getContext().setAuthentication(null);
                    }
                }
            }
            //放行,后面交给Spring Security 框架
            filterChain.doFilter(request,response);
        }catch (RuntimeException e){
            LOGGER.error("doFilterInternal",e);
            throw new RuntimeException(e);
        }
    }
}
