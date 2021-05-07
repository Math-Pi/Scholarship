package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-14  17:39
 */
@Data
public class LibraryDTO {
    /**
     * 主键
     */
    private int dmid;

    /**
     * 代码规则编号
     */
    private String gzbh;

    /**
     * 代码规则名称
     */
    private String gzmc;

    /**
     * 代码键
     */
    private String dmk;

    /**
     * 代码值
     */
    private String dmv;

    /**
     * 数据总数
     */
    private int total;
}
