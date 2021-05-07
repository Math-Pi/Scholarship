package com.biye.cjm.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Author CJM
 * @Date 2021-03-16  22:15
 */
@Data
public class StudentDTO {
    /**
     * 主键
     */
    private int xsid;

    /**
     * 学生编号
     */
    private String xsbh;

    /**
     * 学生姓名
     */
    private String xsxm;

    /**
     * 学生性别
     */
    private String xsxb;

    /**
     * 学生年龄
     */
    private String xsnl;

    /**
     * 年级
     */
    private String nj;

    /**
     * 班级编号
     */
    private String bjbh;

    /**
     * 班级名称
     */
    private String bjmc;

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
     * 入学时间
     */
    private Date rxsj;

    /**
     * 联系电话
     */
    private String lxdh;

    /**
     * 地址
     */
    private String dz;

    /**
     * 备注
     */
    private String bz;

    /**
     * 学数据总数
     */
    private int total;
}
