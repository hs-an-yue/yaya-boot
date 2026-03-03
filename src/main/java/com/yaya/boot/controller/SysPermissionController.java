package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysPermission;
import com.yaya.boot.service.SysPermissionService;
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

/**
 * 权限管理
 */
@Tag(name = "权限管理")
@RestController
public class SysPermissionController {

    @Resource
    private SysPermissionService sysPermissionService;


    @Operation(summary = "权限-添加")
    @PostMapping(value = "/addSysPermission")
    public Result addSysPermission(@RequestBody SysPermission sysPermission) {
        sysPermissionService.addSysPermission(sysPermission);
        return Result.ok();
    }

    @Operation(summary = "权限-删除")
    @Parameters(value = {
        @Parameter(name = "permissionId",description = "权限ID",required = true)
    })
    @GetMapping(value = "/deleteSysPermission")
    public Result deleteSysPermission(@RequestParam(value = "permissionId") String permissionId){
        sysPermissionService.deleteSysPermission(permissionId);
        return Result.ok();
    }

    @Operation(summary = "权限-更新")
    @PostMapping(value = "/updateSysPermission")
    public Result updateSysPermission(@RequestBody SysPermission sysPermission) {
        sysPermissionService.updateSysPermission(sysPermission);
        return Result.ok();
    }

    @Operation(summary = "权限-详情")
    @Parameters(value = {
        @Parameter(name = "permissionId",description = "权限ID",required = true)
    })
    @GetMapping(value = "/getSysPermissionByPermissionId")
    public Result getSysPermissionByPermissionId(@RequestParam(value = "permissionId") String permissionId){
        return Result.ok(sysPermissionService.getSysPermissionByPermissionId(permissionId));
    }

    @Operation(summary = "权限-分页")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "status",description = "是否禁用 1:是 0:否",required = false),
        @Parameter(name = "permissionName",description = "权限名称",required = false),
        @Parameter(name = "permissionSymbol",description = "权限符号",required = false),
        @Parameter(name = "startTime",description = "开始时间",required = false),
        @Parameter(name = "endTime",description = "结束时间",required = false)
    })
    @GetMapping(value = "/getSysPermissionPage")
    public Result getSysPermissionPage(@RequestParam(value = "pageNo") Integer pageNo,
                                       @RequestParam(value = "pageNo") Integer pageSize,
                                       @RequestParam(value = "permissionName",required = false) String permissionName,
                                       @RequestParam(value = "permissionSymbol",required = false) String permissionSymbol,
                                       @RequestParam(value = "status",required = false) Integer status,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "startTime",required = false) LocalDateTime startTime,
                                       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endTime",required = false) LocalDateTime endTime){
        return Result.ok(sysPermissionService.getSysPermissionPage(pageNo, pageSize, permissionName,permissionSymbol,status, startTime, endTime));
    }
}
