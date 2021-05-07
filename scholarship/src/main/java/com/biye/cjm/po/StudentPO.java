package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-16  22:16
 */
@TableName("student")
@Data
public class StudentPO {
    /**
     * 主键
     */
    @TableId("XSID")
    private int xsid;

    /**
     * 学生编号
     */
    @TableField(value="XSBH",el="xsbh,jdbcType=VARCHAR")
    private String xsbh;

    /**
     * 学生姓名
     */
    @TableField(value="XSXM",el="xsxm,jdbcType=VARCHAR")
    private String xsxm;

    /**
     * 学生性别
     */
    @TableField(value="XSXB",el="xsxb,jdbcType=VARCHAR")
    private String xsxb;

    /**
     * 学生年龄
     */
    @TableField(value="XSNL",el="xsnl,jdbcType=VARCHAR")
    private String xsnl;

    /**
     * 年级
     */
    @TableField(value="NJ",el="nj,jdbcType=VARCHAR")
    private String nj;

    /**
     * 班级编号
     */
    @TableField(value="BJBH",el="bjbh,jdbcType=VARCHAR")
    private String bjbh;

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

    /**
     * 入学时间
     */
    @TableField(value="RXSJ",el="rxsj,jdbcType=VARCHAR")
    private Date rxsj;

    /**
     * 联系电话
     */
    @TableField(value="LXDH",el="lxdh,jdbcType=VARCHAR")
    private String lxdh;

    /**
     * 地址
     */
    @TableField(value="DZ",el="dz,jdbcType=VARCHAR")
    private String dz;

    /**
     * 备注
     */
    @TableField(value="BZ",el="bz,jdbcType=VARCHAR")
    private String bz;
}
