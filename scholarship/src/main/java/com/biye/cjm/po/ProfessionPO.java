package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-16  19:31
 */
@TableName("profession")
@Data
public class ProfessionPO {
    /**
     * 主键
     */
    @TableId("ZYID")
    private int zyid;

    /**
     * 专业编号
     */
    @TableField(value="ZYBH",el="zybh,jdbcType=VARCHAR")
    private String zybh;

    /**
     * 专业名称
     */
    @TableField(value="ZYMC",el="zymc,jdbcType=VARCHAR")
    private String zymc;

    /**
     * 学院编号
     */
    @TableField(value="XYBH",el="xybh,jdbcType=VARCHAR")
    private String xybh;
}
