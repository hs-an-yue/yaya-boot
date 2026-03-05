package com.yaya.boot.controller;

import cn.hutool.json.JSONUtil;
import com.yaya.boot.config.YaYaConfig;
import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.exception.GlobalCommonException;
import com.yaya.boot.security.LoginUserDetails;
import com.yaya.boot.service.SysRoleService;
import com.yaya.boot.utils.JwtUtils;
import com.yaya.boot.utils.RedisClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 登录-注销管理
 */
@Tag(name = "登录-注销管理")
@RestController
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private RedisClient redisClient;
    @Resource
    private YaYaConfig yaYaConfig;
    @Resource
    private SysRoleService sysRoleService;

    /**
     * 注销
     * 注意: 注销其实可以不自己实现,直接使用SpringSecurity内置的即可
     *      写一个空方法的原因方便前端调用和查找,也方便后来者理解
     *      注销逻辑在 com.yue.security.LogoutStatusSuccessHandler 类中 由SpringSecurity实现
     */
    @Operation(summary = "注销")
    @GetMapping(value = "/logout")
    public Result logout(){
        return Result.ok();
    }

    /**
     * 登录
     */
    @Operation(summary = "登录")
    @Parameters(value = {
            @Parameter(name = "phone",description = "手机号",required = true),
            @Parameter(name = "password",description = "密码",required = true),
            @Parameter(name = "captcha",description = "验证码",required = true),
            @Parameter(name = "uuid",description = "验证码接口返回的uuid",required = true)
    })
    @PostMapping(value = "/login")
    public Result login(@RequestParam(value = "phone",required = true) String phone,
                        @RequestParam(value = "password",required = true) String password,
                        @RequestParam(value = "captcha",required = true) String captcha,
                        @RequestParam(value = "uuid",required = true) String uuid){
        /*
         * 校验验证码
         * uuid: 验证码接口中向redis中保存数据的key值
         * captcha: 用户输如的验证码
         */
        String keyCaptcha = "captcha:"+uuid;
        //从数据库中获取
        String captcha_ = redisClient.get(keyCaptcha);
        if(StringUtils.isEmpty(captcha_)){
            throw new GlobalCommonException("验证码为空");
        }
        //判断生成的验证码和用户输入的验证码是否相同(忽略大小写)
        if(!captcha.equalsIgnoreCase(captcha_)){
            throw new GlobalCommonException("验证码错误");
        }
        /*
         * 验证账户(手机号)和密码
         */
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(phone,password);
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            //如果为空,说明用户名或密码错误,造成认证失败
            if(Objects.isNull(authenticate)){
                throw new GlobalCommonException("认证失败");
            }
            /*
             * 如果认证成功,SpringSecurity会将用户信息封装到Authentication对象中
             */
            LoginUserDetails principal = (LoginUserDetails) authenticate.getPrincipal();
            //基于账户(手机号)生成token
            String token = JwtUtils.sign(principal.getUsername());
            //校验token是否生成成功
            if(StringUtils.isEmpty(token)){
                throw new GlobalCommonException("TOKEN生成失败");
            }
            //以token作为key将用户信息json化,保存到redis中
            String json = JSONUtil.toJsonStr(principal);
            //获取token中的负载
            String phone_ = JwtUtils.getClaim(token);
            String keyUser = "login_user:"+phone_;//使用账号作为key
            redisClient.set(keyUser,json,yaYaConfig.getTokenTimeout());//过期时间默认为30天,可以在yml文件中进行自定义配置
            //将token返回给客户端
            Map<String,Object> map = new HashMap<>();
            map.put("token",token);//令牌
            Map<String,Object> userMap = new HashMap<>();
            //登录的用户信息
            SysUser sysUser = principal.getSysUser();
            userMap.put("userId",sysUser.getUserId()); //用户ID
            userMap.put("avatar",sysUser.getAvatar());//用户头像
            userMap.put("phone",sysUser.getPhone());//用户账号
            userMap.put("username",sysUser.getUsername());//用户名称
            userMap.put("deptId",sysUser.getDeptId());//用户部门ID
            userMap.put("deptName",sysUser.getSysDepartment().getDeptName());//用户部门名称
            userMap.put("roleId",sysUser.getRoleId());//用户角色ID
            userMap.put("roleName",sysUser.getSysRole().getRoleName());//用户角色名称
            userMap.put("roleSymbol",sysUser.getSysRole().getRoleSymbol());//用户角色符号
            SysTenant sysTenant = sysUser.getSysTenant();
            userMap.put("tenantId",sysTenant.getTenantId());//租户ID
            userMap.put("tenantName",sysTenant.getTenantName());//租户名称
            userMap.put("sex",sysUser.getSex()==1?"男":"女");//用户性别
            userMap.put("email",sysUser.getEmail());//用户邮箱
            userMap.put("createTime",sysUser.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));//创建时间
            map.put("user",userMap);
            return Result.ok(map);
        }catch (RuntimeException e){
            log.error("登陆异常:",e);
            switch (e) {
                case AccountExpiredException ignored -> throw new GlobalCommonException("账号已过期");
                case LockedException ignored -> throw new GlobalCommonException("账号被锁定");
                case CredentialsExpiredException ignored ->
                        throw new GlobalCommonException("账号已过期");
                case DisabledException ignored -> throw new GlobalCommonException("账号不可用");
                default -> {
                    throw new GlobalCommonException(e.getMessage());
                }
            }
        }
    }

    /**
     * 校验token是否有效
     */
    @Operation(summary = "校验token是否有效")
    @GetMapping(value = "/checkToken")
    public Result checkToken(){
        return Result.ok();
    }
}
