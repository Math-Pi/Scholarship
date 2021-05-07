package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  18:20
 */
@TableName("department")
@Data
public class DepartmentPO {
    /**
     * 主键（学院编号）
     */
    @TableId("xyid")
    private int xyid;

    /**
     * 学院编号
     */
    @TableField(value="xybh",el="xybh,jdbcType=VARCHAR")
    private String xybh;

    /**
     * 学院名称
     */
    @TableField(value="xymc",el="xymc,jdbcType=VARCHAR")
    private String xymc;
}
