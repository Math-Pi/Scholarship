package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  19:31
 */
@Data
public class ProfessionDTO {
    /**
     * 主键
     */
    private int zyid;

    /**
     * 专业编号
     */
    private String zybh;

    /**
     * 专业名称
     */
    private String zymc;

    /**
     * 学院编号
     */
    private String xybh;

    /**
     * 学院名称
     */
    private String xymc;

    /**
     * 班级总数
     */
    private int sum;

    /**
     * 班级总数
     */
    private int total;

    /**
     * 排序
     */
    private int sorter;
}
