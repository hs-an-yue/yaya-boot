package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysNoticeType;
import com.yaya.boot.service.SysNoticeTypeService;
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

/**
 * 公告类型管理
 */
@Tag(name = "公告类型管理")
@RestController
public class SysNoticeTypeController {

    @Resource
    private SysNoticeTypeService sysNoticeTypeService;

    @Operation(summary = "公告类型-创建")
    @PostMapping(value = "/addSysNoticeType")
    public Result addSysNoticeType(@RequestBody SysNoticeType sysNoticeTyp) {
        sysNoticeTypeService.addSysNoticeType(sysNoticeTyp);
        return Result.ok();
    }

    @Operation(summary = "公告类型-删除")
    @Parameters(value = {
        @Parameter(name = "noticeTypeId",description = "公告类型ID",required = true)
    })
    @GetMapping(value = "/deleteSysNoticeType")
    public Result deleteSysNoticeType(@RequestParam(value = "noticeTypeId") String noticeTypeId){
        sysNoticeTypeService.deleteSysNoticeType(noticeTypeId);
        return Result.ok();
    }

    @Operation(summary = "公告类型-更新")
    @PostMapping(value = "/updateSysNoticeType")
    public Result updateSysNoticeType(@RequestBody SysNoticeType sysNoticeTyp) {
        sysNoticeTypeService.updateSysNoticeType(sysNoticeTyp);
        return Result.ok();
    }

    @Operation(summary = "公告类型-分页")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页ID",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "tenantId",description = "租户ID",required = true),
        @Parameter(name = "noticeTypeName",description = "类型名称")
    })
    @GetMapping(value = "/getSysNoticeTypePageByTenantId")
    public Result getSysNoticeTypePageByTenantId(@RequestParam(value = "pageNo") Integer pageNo,
                                                 @RequestParam(value = "pageSize") Integer pageSize,
                                                 @RequestParam(value = "tenantId") String tenantId,
                                                 @RequestParam(value = "noticeTypeName",required = false) String noticeTypeName){
        return Result.ok(sysNoticeTypeService.getSysNoticeTypePageByTenantId(pageNo,pageSize,tenantId,noticeTypeName));
    }

    @Operation(summary = "公告类型-列表")
    @Parameters(value = {
        @Parameter(name = "tenantId",description = "租户ID",required = true)
    })
    @GetMapping(value = "/getSysNoticeTypeListByTenantId")
    public Result getSysNoticeTypeListByTenantId(@RequestParam(value = "tenantId") String tenantId){
        return Result.ok(sysNoticeTypeService.getSysNoticeTypeListByTenantId(tenantId));
    }


}
