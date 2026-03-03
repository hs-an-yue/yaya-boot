package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysTenant;
import com.yaya.boot.service.SysTenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 租户管理
 */
@Tag(name = "租户管理")
@RestController
public class SysTenantController {

    @Resource
    private SysTenantService sysTenantService;

    @Operation(summary = "租户-添加")
    @PostMapping(value = "/addSysTenant")
    public Result addSysTenant(@RequestBody SysTenant sysTenant) {
        sysTenantService.addSysTenant(sysTenant);
        return Result.ok();
    }

    @Operation(summary = "租户-单个删除")
    @Parameters(value = {
        @Parameter(name = "tenantId",description = "租户ID",required = true)
    })
    @GetMapping(value = "/deleteSysTenant")
    public Result deleteSysTenant(@RequestParam(value = "tenantId") String tenantId){
        sysTenantService.deleteSysTenant(tenantId);
        return Result.ok();
    }


    @Operation(summary = "租户-批量删除")
    @PostMapping(value = "/batchDeleteSysTenant")
    public Result batchDeleteSysTenant(@RequestBody List<String> tenantIds){
        sysTenantService.batchDeleteSysTenant(tenantIds);
        return Result.ok();
    }

    @Operation(summary = "租户-更新")
    @PostMapping(value = "/updateSysTenant")
    public Result updateSysTenant(@RequestBody SysTenant sysTenant) {
        sysTenantService.updateSysTenant(sysTenant);
        return Result.ok();
    }

    @Operation(summary = "租户-详情")
    @Parameters(value = {
        @Parameter(name = "tenantId",description = "租户ID",required = true)
    })
    @GetMapping(value = "/getSysTenantByTenantId")
    public Result getSysTenantByTenantId(@RequestParam(value = "tenantId") String tenantId){
        return Result.ok(sysTenantService.getSysTenantByTenantId(tenantId));
    }

    @Operation(summary = "租户-分页")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "status",description = "是否禁用 1:是 0:否"),
        @Parameter(name = "tenantName",description = "租户名称"),
        @Parameter(name = "startTime",description = "开始时间"),
        @Parameter(name = "endTime",description = "结束时间")
    })
    @GetMapping(value = "/getSysTenantPage")
    public Result getSysTenantPage(@RequestParam(value = "pageNo") Integer pageNo,
                                   @RequestParam(value = "pageSize") Integer pageSize,
                                   @RequestParam(value = "tenantName",required = false) String tenantName,
                                   @RequestParam(value = "status",required = false) Integer status,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "startTime",required = false) LocalDateTime startTime,
                                   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endTime",required = false) LocalDateTime endTime){
        return Result.ok(sysTenantService.getSysTenantPage(pageNo, pageSize, tenantName, status, startTime, endTime));
    }

    @Operation(summary = "租户-全部列表")
    @GetMapping(value = "/getSysTenantAll")
    public Result getSysTenantAll(){
        return Result.ok(sysTenantService.getSysTenantAll());
    }
}
