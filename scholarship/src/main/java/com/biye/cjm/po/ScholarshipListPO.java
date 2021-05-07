package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-17  10:49
 */
@TableName("scholarshipList")
@Data
public class ScholarshipListPO {
    /**
     * 主键
     */
    @TableId("MDID")
    private int mdid;

    /**
     * 学生编号
     */
    @TableField(value="XSBH",el="xsbh,jdbcType=VARCHAR")
    private String xsbh;

    /**
     * 奖学金发布主键
     */
    @TableField(value="FBID",el="fbid,jdbcType=VARCHAR")
    private int fbid;

    /**
     * 奖学金定义主键
     */
    @TableField(value="DYID",el="dyid,jdbcType=VARCHAR")
    private int dyid;

    /**
     * 获奖时间
     */
    @TableField(value="CJSJ",el="cjsj,jdbcType=DATE")
    private Date cjsj;

}
