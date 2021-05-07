package com.biye.cjm.dto;


import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-14  21:41
 */
@Data
public class RuleDTO {
    /**
     * 主键
     */
    private int gzid;

    /**
     * 规则编号
     */
    private String gzbh;

    /**
     * 规则名称
     */
    private String gzmc;

    /**
     * 数据总数
     */
    private int total;
}
