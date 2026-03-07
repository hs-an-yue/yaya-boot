package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.service.SysNoticeDeptService;
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
 * 公告分配管理
 */
@Tag(name = "公告分配管理")
@RestController
public class SysNoticeDeptController {

    @Resource
    private SysNoticeDeptService sysNoticeDeptService;


    @Operation(summary = "公告分配管理-分配部门")
    @Parameters(value = {
            @Parameter(name = "noticeId",description = "公告ID",required = true),
            @Parameter(name = "deptIds",description = "部门ID列表 格式为 1,2,3,4,...",required = false)
    })
    @PostMapping(value = "/addSysNoticeDept")
    public Result addSysNoticeDept(@RequestParam(value = "noticeId") String noticeId,
                                   @RequestParam(value = "deptIds",required = false) List<String> deptIds) {
        sysNoticeDeptService.addSysNoticeDept(noticeId, deptIds);
        return Result.ok();
    }

    @Operation(summary = "公告分配管理-已分配部门ID列表-用于回显")
    @Parameters(value = {
        @Parameter(name = "noticeId",description = "消息ID",required = true)
    })
    @GetMapping(value = "/getDeptIdsByNoticeId")
    public Result getDeptIdsByNoticeId(@RequestParam(value = "noticeId",required = true) String noticeId){
        return Result.ok(sysNoticeDeptService.getDeptIdsByNoticeId(noticeId));
    }
}
