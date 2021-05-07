package com.biye.cjm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-17  10:49
 */
@Data
public class ScholarshipListDTO {
    /**
     * 主键
     */
    private int mdid;

    /**
     * 学生编号
     */
    private String xsbh;

    /**
     * 奖学金发布主键
     */
    private int fbid;

    /**
     * 奖学金定义主键
     */
    private int dyid;

    /**
     * 获奖时间
     */
    private Date cjsj;

    /**
     * 数据总数
     */
    private int total;
}
