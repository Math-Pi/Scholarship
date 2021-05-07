package com.biye.cjm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-15  19:38
 */
@Data
public class ScholarshipPublishDTO {
    /**
     * 主键
     */
    private int fbid;

    /**
     * 奖学金定义ID
     */
    private int dyid;

    /**
     * 奖学金名称
     */
    private String jxjmc;

    /**
     * 奖学金等级,代码库JXJDJ（1：一级、2：二级、3：三级、4:四级）
     */
    private String jxjdj;

    /**
     * 申请学年,代码库SQXN
     */
    private String sqxn;

    /**
     * 奖学金金额
     */
    private int jxjje;

    /**
     * 开始时间
     */
    private Date kssj;

    /**
     * 结束时间
     */
    private Date jssj;

    /**
     * 是否发布
     */
    private int sffb;

    /**
     * 绩点标准
     */
    private String jdbz;

    /**
     * 成绩标准
     */
    private String cjbz;

    /**
     * 数据总数
     */
    private int total;

    /**
     * 排序
     */
    private int sorter;
}
