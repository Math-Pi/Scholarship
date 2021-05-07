package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-14  17:33
 */
@TableName("library")
@Data
public class LibraryPO {
    /**
     * 主键
     */
    @TableId("DMID")
    private int dmid;
    /**
     * 代码规则标志
     */
    @TableField(value="DMBH",el="dmbh,jdbcType=VARCHAR")
    private String dmbh;
    /**
     * 代码
     */
    @TableField(value="DMK",el="dmk,jdbcType=VARCHAR")
    private String dmk;

    /**
     * 代码值
     */
    @TableField(value="DMV",el="dmv,jdbcType=VARCHAR")
    private String dmv;
}
