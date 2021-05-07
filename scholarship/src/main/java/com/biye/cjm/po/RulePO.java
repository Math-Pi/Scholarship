package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-14  21:39
 */
@TableName("rule")
@Data
public class RulePO {
    /**
     * 主键
     */
    @TableId("GZID")
    private int gzid;

    /**
     * 代码规则编号
     */
    @TableField(value="GZBH",el="gzbh,jdbcType=VARCHAR")
    private String gzbh;

    /**
     * 代码规则名称
     */
    @TableField(value="GZMC",el="gzmc,jdbcType=VARCHAR")
    private String gzmc;
}
