package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  21:00
 */
@TableName("class")
@Data
public class ClassPO {
    /**
     * 主键
     */
    @TableId("BJID")
    private int bjid;

    /**
     * 班级编号
     */
    @TableField(value="BJBH",el="bjbh,jdbcType=VARCHAR")
    private String bjbh;

    /**
     * 班级名称
     */
    @TableField(value="BJMC",el="bjmc,jdbcType=VARCHAR")
    private String bjmc;

    /**
     * 班主任
     */
    @TableField(value="BZR",el="bzr,jdbcType=VARCHAR")
    private String bzr;

    /**
     * 年级
     */
    @TableField(value="NJ",el="nj,jdbcType=VARCHAR")
    private String nj;

    /**
     * 专业编号
     */
    @TableField(value="ZYBH",el="zybh,jdbcType=VARCHAR")
    private String zybh;

    /**
     * 学院编号
     */
    @TableField(value="XYBH",el="xybh,jdbcType=VARCHAR")
    private String xybh;
}

