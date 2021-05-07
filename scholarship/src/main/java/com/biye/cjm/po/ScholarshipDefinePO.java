package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */
@TableName("scholarshipDefine")
@Data
public class ScholarshipDefinePO {
    /**
     * 主键
     */
    @TableId("DYID")
    private int dyid;

    /**
     * 奖学金类型
     */
    @TableField(value="LX",el="lx,jdbcType=VARCHAR")
    private String lx;

    /**
     * 奖励金级别,代码库JLJJB（1：国家级、2：省级、3：校级、4:院级）
     */
    @TableField(value="JLJBDM",el="jljbdm,jdbcType=INTEGER")
    private int jljbdm;

    /**
     * 资金来源,代码库ZJLY（1:国家拨款、2：企业拨款:、3：学校拨款，4：其它）
     */
    @TableField(value="ZJLYDM",el="zjlydm,jdbcType=INTEGER")
    private int zjlydm;

    /**
     * 是否分等级（1：是，0：否）
     */
    @TableField(value="SFFDJ",el="sffdj,jdbcType=INTEGER")
    private int sffdj;

    /**
     * 是否启用（1：启用，0：停用）
     */
    @TableField(value="SFQY",el="sfqy,jdbcType=INTEGER")
    private int sfqy;

    /**
     * 简介描述
     */
    @TableField(value="JJMS",el="jjms,jdbcType=VARCHAR")
    private String jjms;

    /**
     * 附件id，关联附件表
     */
    @TableField(value="FJID",el="fjid,jdbcType=INTEGER")
    private int fjid;
}
