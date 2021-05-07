package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-15  19:28
 */
@TableName("scholarshipPublish")
@Data
public class ScholarshipPublishPO {
    /**
     * 主键
     */
    @TableId("FBID")
    private int fbid;

    /**
     * 奖学金定义ID
     */
    @TableId("DYID")
    private int dyid;

    /**
     * 奖学金名称
     */
    @TableField(value="JXJMC",el="jxjmc,jdbcType=VARCHAR")
    private String jxjmc;

    /**
     * 奖学金等级,代码库JXJDJ（1：一级、2：二级、3：三级、4:四级）
     */
    @TableField(value="JXJDJ",el="jxjdj,jdbcType=VARCHAR")
    private String jxjdj;

    /**
     * 申请学年,代码库SQXN
     */
    @TableField(value="SQXN",el="sqxn,jdbcType=VARCHAR")
    private String sqxn;

    /**
     * 奖学金金额
     */
    @TableField(value="JXJJE",el="jxjje,jdbcType=INTEGER")
    private int jxjje;

    /**
     * 开始时间
     */
    @TableField(value="KSSJ",el="kssj,jdbcType=VARCHAR")
    private Date kssj;

    /**
     * 结束时间
     */
    @TableField(value="JSSJ",el="jssj,jdbcType=VARCHAR")
    private Date jssj;

    /**
     * 是否发布
     */
    @TableField(value="SFFB",el="sffb,jdbcType=INTEGER")
    private int sffb;

    /**
     * 绩点标准
     */
    @TableField(value="JDBZ",el="jdbz,jdbcType=VARCHAR")
    private String jdbz;

    /**
     * 成绩标准
     */
    @TableField(value="CJBZ",el="cjbz,jdbcType=VARCHAR")
    private String cjbz;
}
