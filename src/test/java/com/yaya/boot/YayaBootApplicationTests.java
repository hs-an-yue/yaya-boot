package com.yaya.boot;

import cn.hutool.core.lang.Validator;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class YayaBootApplicationTests {

    @Resource
    private PasswordEncoder passwordEncoder;

	@Test
	void contextLoads() {
        String encode = passwordEncoder.encode("123456");
        System.out.println(encode);
        //$2a$10$iTBYWW89ZPbyQMowkfw9YubzrMgMgpEdCU3WgperYH1NCouKBdwBS
    }

    @Test
    void test1(){
        String phone="18895648521";
        String pwd = phone.substring(5);
        System.out.println(pwd);
    }

    @Test
    void test2(){
        List<String> all = List.of("1","2","3","4","5","6","7","8","9");
        List<String> subs = List.of("6","7","8","10","11");
        List<String> subs_ = new ArrayList<>(subs);
        subs_.removeAll(all);
        System.out.println(subs_);
    }

    @Test
    void test3(){
        double rate = 1.233333666333333d;
        System.out.println(new BigDecimal(rate).setScale(2, RoundingMode.HALF_UP).doubleValue());
    }
}
