package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.service.SysRoleMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 角色菜单授权管理
 */
@Tag(name = "角色菜单授权管理")
@RestController
public class SysRoleMenuController {

    @Resource
    private SysRoleMenuService sysRoleMenuService;

    @Operation(summary = "角色菜单-授权")
    @Parameters(value = {
        @Parameter(name = "roleId",description = "角色ID",required = true),
        @Parameter(name = "menuIds",description = "菜单ID列表 格式为 1,2,3,4,...",required = false)
    })
    @PostMapping(value = "/addOrUpdateSysRoleMenu")
    public Result addOrUpdateSysRoleMenu(@RequestParam(value = "roleId") String roleId,
                                         @RequestParam(value = "menuIds",required = false) List<String> menuIds) {
        sysRoleMenuService.addOrUpdateSysRoleMenu(roleId, menuIds);
        return Result.ok();
    }

    @Operation(summary = "角色菜单-已授权菜单ID列表-用于回显")
    @Parameters(value = {
        @Parameter(name = "roleId",description = "角色ID",required = true)
    })
    @GetMapping(value = "/getAuthMenuIdsByRoleId")
    public Result getAuthMenuIdsByRoleId(@RequestParam(value = "roleId",required = true) String roleId){
        return Result.ok(sysRoleMenuService.getAuthMenuIdsByRoleId(roleId));
    }
}
