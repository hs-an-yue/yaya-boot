package com.yaya.boot.controller;

import com.yaya.boot.dto.Result;
import com.yaya.boot.entity.SysDepartment;
import com.yaya.boot.service.SysDepartmentService;
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
 * 部门管理
 */
@Tag(name = "部门管理")
@RestController
public class SysDepartmentController {
    @Resource
    private SysDepartmentService sysDepartmentService;


    @Operation(summary = "部门-添加")
    @PostMapping(value = "/addSysDepartment")
    public Result addSysDepartment(@RequestBody SysDepartment sysDepartment){
        sysDepartmentService.addSysDepartment(sysDepartment);
        return Result.ok();
    }

    @Operation(summary = "部门-删除单个")
    @Parameters(value = {
        @Parameter(name = "deptId",description = "部门ID",required = true)
    })
    @GetMapping(value = "/deleteSysDepartment")
    public Result deleteSysDepartment(@RequestParam(value = "deptId",required = false) String deptId){
        sysDepartmentService.deleteSysDepartment(deptId);
        return Result.ok();
    }

    @Operation(summary = "部门-删除批量")
    @PostMapping(value = "/batchDeleteSysDepartment")
    public Result batchDeleteSysDepartment(@RequestBody List<String> deptIds){
        sysDepartmentService.batchDeleteSysDepartment(deptIds);
        return Result.ok();
    }

    @Operation(summary = "部门-更新")
    @PostMapping(value = "/updateSysDepartment")
    public Result updateSysDepartment(@RequestBody SysDepartment sysDepartment){
        sysDepartmentService.updateSysDepartment(sysDepartment);
        return Result.ok();
    }

    @Operation(summary = "部门-树状列表")
    @Parameters(value = {
        @Parameter(name = "tenantId",description = "租户ID",required = false),
        @Parameter(name = "status",description = "是否禁用 1:是 0:否",required = false),
        @Parameter(name = "deptName",description = "部门名称",required = false)
    })
    @GetMapping(value = "/getSysDepartmentTree")
    public Result getSysDepartmentTree(@RequestParam(value = "tenantId",required = false) String tenantId,
                                       @RequestParam(value = "status",required = false) Integer status,
                                       @RequestParam(value = "deptName",required = false) String deptName){
        return Result.ok(sysDepartmentService.getSysDepartmentTree(tenantId,status,deptName));
    }

    @Operation(summary = "部门-子部门列表")
    @Parameters(value = {
            @Parameter(name = "deptId",description = "部门ID",required = true),
            @Parameter(name = "tenantId",description = "租户ID",required = false),
            @Parameter(name = "status",description = "是否禁用 1:是 0:否",required = false)
    })
    @GetMapping(value = "/getSubSysDepartmentByDeptId")
    public Result getSubSysDepartmentByDeptId(@RequestParam(value = "tenantId",required = false) String tenantId,@RequestParam(value = "status",required = false) Integer status,@RequestParam(value = "deptId",required = true) String deptId){
        return Result.ok(sysDepartmentService.getSubSysDepartmentByDeptId(tenantId,status,deptId));
    }
}
