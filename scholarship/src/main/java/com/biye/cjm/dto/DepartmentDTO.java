package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  18:21
 */
@Data
public class DepartmentDTO {
    /**
     * 主键
     */
    private int xyid;

    /**
     * 学院编号
     */
    private String xybh;

    /**
     * 学院名称
     */
    private String xymc;

    /**
     * 专业总数
     */
    private int sum;

    /**
     * 数据总数
     */
    private int total;

    /**
     * 排序
     */
    private int sorter;
}
