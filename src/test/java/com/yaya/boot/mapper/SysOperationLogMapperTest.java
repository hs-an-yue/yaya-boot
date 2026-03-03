package com.yaya.boot.mapper;

import com.yaya.boot.YayaBootApplicationTests;
import com.yaya.boot.entity.SysOperationLog;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;

import java.util.List;

class SysOperationLogMapperTest extends YayaBootApplicationTests {

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;

    @Test
    void getSysOperationLogList() {
        List<SysOperationLog> sysOperationLogList = sysOperationLogMapper.getSysOperationLogList(null, null,null,null, null,null, null, null,null,null,null);
        System.out.println(sysOperationLogList);
    }
}