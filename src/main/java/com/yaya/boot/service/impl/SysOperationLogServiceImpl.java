package com.yaya.boot.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yaya.boot.dto.Page;
import com.yaya.boot.entity.SysOperationLog;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.entity.SysUser;
import com.yaya.boot.mapper.SysOperationLogMapper;
import com.yaya.boot.mapper.SysTenantMapper;
import com.yaya.boot.mapper.SysUserMapper;
import com.yaya.boot.service.SysOperationLogService;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class SysOperationLogServiceImpl implements SysOperationLogService {

    private static final Logger logger = LoggerFactory.getLogger(SysOperationLogService.class);

    @Resource
    private SysOperationLogMapper sysOperationLogMapper;
    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private SysTenantMapper sysTenantMapper;

    @Override
    public Page getSysOperationLogPage(Integer pageNo, Integer pageSize, String tenantId, Integer logType, String trackId, String username, String phone, String operationParams, String ip, String address, LocalDateTime startTime, LocalDateTime endTime,Integer status) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysOperationLog> sysOperationLogList = sysOperationLogMapper.getSysOperationLogList(tenantId, logType, trackId, username, phone,operationParams,ip,address,startTime,endTime,status);
        if(CollectionUtils.isNotEmpty(sysOperationLogList)){
            sysOperationLogList.forEach(sysOperationLog -> {
                String tenantId_ = sysOperationLog.getTenantId();
                SysTenant sysTenant = sysTenantMapper.selectById(tenantId_);
                System.out.println(sysTenant);
                if(sysTenant!=null){
                    sysOperationLog.setSysTenant(sysTenant);
                }
                String operationUserId = sysOperationLog.getOperationUserId();
                SysUser sysUser = sysUserMapper.selectById(operationUserId);
                System.out.println(sysUser);
                if(sysUser!=null){
                    sysOperationLog.setOperationUser(sysUser);
                }
            });
        }
        PageInfo<SysOperationLog> pageInfo = new PageInfo<>(sysOperationLogList);
        Page page = new Page();
        page.setPageNum(pageInfo.getPageNum());
        page.setPageSize(pageInfo.getPageSize());
        page.setHasNext(pageInfo.isHasNextPage());
        page.setHasPre(pageInfo.isHasPreviousPage());
        page.setCount(pageInfo.getTotal());
        page.setList(pageInfo.getList());
        return page;
    }

    @Async(value = "logExecutor") //指定使用的线程池(自定义日志线程池)
    @Override
    public void saveSysOperationLog(SysOperationLog sysOperationLog) {
        sysOperationLogMapper.insert(sysOperationLog);
        logger.info("save sysOperationLog success....");
    }
}
