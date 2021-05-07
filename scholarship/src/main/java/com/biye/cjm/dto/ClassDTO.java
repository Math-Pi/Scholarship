package com.biye.cjm.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  21:00
 */
@Data
public class ClassDTO {
    /**
     * 主键
     */
    private int bjid;

    /**
     * 班级编号
     */
    private String bjbh;

    /**
     * 班主任
     */
    private String bzr;

    /**
     * 班级名称
     */
    private String bjmc;

    /**
     * 年级
     */
    private String nj;

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
     * 学生总数
     */
    private int sum;

    /**
     * 数据总数
     */
    private int total;

    /**
     * 排序
     */
    private int sorter;
}


