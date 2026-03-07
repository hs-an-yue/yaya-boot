package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysNotice;
import com.yaya.boot.service.SysNoticeService;
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
 * 公告管理
 */
@Tag(name = "公告管理")
@RestController
public class SysNoticeController {

    @Resource
    private SysNoticeService sysNoticeService;

    @Operation(summary = "公告-发布")
    @PostMapping(value = "/publishSysNotice")
    public Result publishSysNotice(@RequestBody SysNotice sysNotice) {
        sysNoticeService.publishSysNotice(sysNotice);
        return Result.ok();
    }

    @Operation(summary = "公告-删除")
    @Parameters(value = {
        @Parameter(name = "noticeId",description = "公告ID",required = true)
    })
    @GetMapping(value = "/deleteSysNotice")
    public Result deleteSysNotice(@RequestParam(value = "noticeId") String noticeId){
        sysNoticeService.deleteSysNotice(noticeId);
        return Result.ok();
    }

    @Operation(summary = "公告-更新")
    @PostMapping(value = "/updateSysNotice")
    public Result updateSysNotice(@RequestBody SysNotice sysNotice) {
        sysNoticeService.updateSysNotice(sysNotice);
        return Result.ok();
    }

    @Operation(summary = "公告-详情")
    @Parameters(value = {
        @Parameter(name = "noticeId",description = "公告ID",required = true)
    })
    @GetMapping(value = "/getSysNoticeById")
    public Result getSysNoticeById(@RequestParam(value = "noticeId") String noticeId){
        return Result.ok(sysNoticeService.getSysNoticeById(noticeId));
    }

    @Operation(summary = "公告页-发布人")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页ID",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "publishUserId",description = "发布人ID",required = true)
    })
    @GetMapping(value = "/getSysNoticePageByPublishUserId")
    public Result getSysNoticePageByPublishUserId(@RequestParam(value = "pageNo") Integer pageNo,
                                                  @RequestParam(value = "pageSize") Integer pageSize,
                                                  @RequestParam(value = "publishUserId") String publishUserId){
        return Result.ok(sysNoticeService.getSysNoticePageByPublishUserId(pageNo,pageSize,publishUserId));
    }


    @Operation(summary = "公告页-接收人")
    @Parameters(value = {
        @Parameter(name = "pageNo",description = "当前页ID",required = true),
        @Parameter(name = "pageSize",description = "页容量",required = true),
        @Parameter(name = "deptId",description = "发布人部门ID",required = true)
    })
    @GetMapping(value = "/mySysNoticePageByDeptId")
    public Result mySysNoticePageByDeptId(@RequestParam(value = "pageNo") Integer pageNo,
                                          @RequestParam(value = "pageSize") Integer pageSize,
                                          @RequestParam(value = "deptId") String deptId){
        return Result.ok(sysNoticeService.mySysNoticePageByDeptId(pageNo,pageSize,deptId));
    }
}
