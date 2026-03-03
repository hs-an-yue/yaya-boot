package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysRole;
import com.yaya.boot.service.SysRoleService;
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
 * 角色管理
 */
@Tag(name = "角色管理")
@RestController
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    @Operation(summary = "角色-添加")
    @PostMapping(value = "/addSysRole")
    public Result addSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.addSysRole(sysRole);
        return Result.ok();
    }

    @Operation(summary = "角色-删除单个")
    @Parameters(value = {
        @Parameter(name = "roleId",description = "角色ID",required = true)
    })
    @GetMapping(value = "/deleteSysRole")
    public Result deleteSysRole(@RequestParam(value = "roleId") String roleId){
        sysRoleService.deleteSysRole(roleId);
        return Result.ok();
    }

    @Operation(summary = "角色-批量删除")
    @PostMapping(value = "/batchDeleteSysRole")
    public Result batchDeleteSysRole(@RequestBody List<String> roleIds){
        sysRoleService.batchDeleteSysRole(roleIds);
        return Result.ok();
    }

    @Operation(summary = "角色-更新")
    @PostMapping(value = "/updateSysRole")
    public Result updateSysRole(@RequestBody SysRole sysRole) {
        sysRoleService.updateSysRole(sysRole);
        return Result.ok();
    }

    @Operation(summary = "角色-详情")
    @Parameters(value = {
        @Parameter(name = "roleId",description = "角色ID",required = true)
    })
    @GetMapping(value = "/getSysRoleByRoleId")
    public Result getSysRoleByRoleId(@RequestParam(value = "roleId") String roleId){
        return Result.ok(sysRoleService.getSysRoleByRoleId(roleId));
    }

    @Operation(summary = "角色-列表-基于租户")
    @Parameters(value = {
        @Parameter(name = "tenantId",description = "租户ID")
    })
    @GetMapping(value = "/getSysRoleListByTenantId")
    public Result getSysRoleListByTenantId(@RequestParam(value = "tenantId",required = false) String tenantId){
        return Result.ok(sysRoleService.getSysRoleListByTenantId(tenantId));
    }

    @Operation(summary = "角色-分页")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "status",description = "是否禁用 1:是 0:否"),
        @Parameter(name = "roleName",description = "角色名称"),
        @Parameter(name = "tenantId",description = "租户ID"),
        @Parameter(name = "roleSymbol",description = "角色符号"),
        @Parameter(name = "startTime",description = "开始时间"),
        @Parameter(name = "endTime",description = "结束时间")
    })
    @GetMapping(value = "/getSysRolePage")
    public Result getSysRolePage(@RequestParam(value = "pageNo") Integer pageNo,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "roleName",required = false) String roleName,
                                 @RequestParam(value = "roleSymbol",required = false) String roleSymbol,
                                 @RequestParam(value = "status",required = false) Integer status,
                                 @RequestParam(value = "tenantId",required = false) String tenantId,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "startTime",required = false) LocalDateTime startTime,
                                 @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam(value = "endTime",required = false) LocalDateTime endTime){
        return Result.ok(sysRoleService.getSysRolePage(pageNo, pageSize, roleName,roleSymbol,status, startTime, endTime,tenantId));
    }
}
