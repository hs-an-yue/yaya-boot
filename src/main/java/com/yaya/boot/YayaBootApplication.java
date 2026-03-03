package com.yaya.boot;

import cn.hutool.core.net.NetUtil;
import com.jthinking.common.util.ip.IPInfo;
import com.jthinking.common.util.ip.IPInfoUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = {"com.yaya.boot.mapper"})
public class YayaBootApplication {

    private static final Logger logger = LoggerFactory.getLogger(YayaBootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(YayaBootApplication.class, args);
        //初始化ip地理信息库
        logger.info("IP地理信息数据库组件启动中....");
        IPInfoUtils.init();
        //通过hutool工具获取当前应用服务所在的宿主机IP
        String ip = NetUtil.getLocalhostStr();
        //当前IP所在的地理信息定位
        IPInfo ipInfo = IPInfoUtils.getIpInfo(ip);
        logger.info("IP地理信息数据库组件启动完成,当前服务器所在的城市:{}",ipInfo.getAddress());
	}

}
