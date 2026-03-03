package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysMenu;
import com.yaya.boot.service.SysMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 菜单管理
 */
@Tag(name = "菜单管理")
@RestController
public class SysMenuController {
    @Resource
    private SysMenuService sysMenuService;


    @Operation(summary = "菜单-添加")
    @PostMapping(value = "/addSysMenu")
    public Result addSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.addSysMenu(sysMenu);
        return Result.ok();
    }

    @Operation(summary = "菜单-删除")
    @Parameters(value = {
        @Parameter(name = "menuId",description = "菜单ID",required = true)
    })
    @GetMapping(value = "/deleteSysMenu")
    public Result deleteSysMenu(@RequestParam(value = "menuId") String menuId){
        sysMenuService.deleteSysMenu(menuId);
        return Result.ok();
    }

    @Operation(summary = "菜单-更新")
    @PostMapping(value = "/updateSysMenu")
    public Result updateSysMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.updateSysMenu(sysMenu);
        return Result.ok();
    }

    @Operation(summary = "菜单-树状列表")
    @Parameters(value = {
        @Parameter(name = "menuTitle",description = "菜单名",required = false),
        @Parameter(name = "status",description = "是否禁用 1:是 0:否",required = false),
        @Parameter(name = "flag",description = "是否查询按钮 1:查询全部 2:排除按钮",required = false)
    })
    @GetMapping(value = "/getSysMenuTree")
    public Result getSysMenuTree(@RequestParam(value = "menuTitle",required = false) String menuTitle,
                                 @RequestParam(value = "status",required = false) Integer status,
                                 @RequestParam(value = "flag",required = false,defaultValue = "1") Integer flag){
        List<SysMenu> tree = sysMenuService.getSysMenuTree(menuTitle, status, flag);
        return Result.ok(tree);
    }

    @Operation(summary = "菜单-授权菜单列表")
    @Parameters(value = {
        @Parameter(name = "roleId",description = "角色ID",required = true)
    })
    @GetMapping(value = "/getAuthSysMenuTree")
    public Result getAuthSysMenuTree(@RequestParam(value = "roleId") String roleId){
        return Result.ok(sysMenuService.getAuthSysMenuTree(roleId));
    }
}
