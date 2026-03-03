package com.yaya.boot.mapper;

import com.yaya.boot.YayaBootApplicationTests;
import com.yaya.boot.entity.SysTenant;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

class SysTenantMapperTest extends YayaBootApplicationTests {

    @Resource
    private SysTenantMapper sysTenantMapper;

    @Test
    void add(){
        SysTenant sysTenant = new SysTenant();
        sysTenant.setTenantName("丫丫实业集团");
        sysTenant.setCreateById("1");
        sysTenant.setUpdateById("1");
        sysTenantMapper.insert(sysTenant);
        String tenantId = sysTenant.getTenantId();
        System.out.println("租户ID："+tenantId);
    }
}