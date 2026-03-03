package com.yaya.boot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 封装分页
 */
@Data
public class Page {
    /**
     * 当前页
     */
    @Schema(description = "当前页")
    private Integer pageNum;
    /**
     * 页容量
     */
    @Schema(description = "页容量")
    private Integer pageSize;
    /**
     * 总记录数
     */
    @Schema(description = "总记录数")
    private Long count;
    /**
     * 是否有上一页
     */
    @Schema(description = "是否有上一页")
    private Boolean hasPre;
    /**
     * 是否有下一页
     */
    @Schema(description = "是否有下一页")
    private Boolean hasNext;
    /**
     * 当前页数据
     */
    @Schema(description = "当前页数据")
    private Object list;
}
