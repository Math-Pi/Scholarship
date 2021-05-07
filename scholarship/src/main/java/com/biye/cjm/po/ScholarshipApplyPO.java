package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-17  12:54
 */
@TableName("scholarshipApply")
@Data
public class ScholarshipApplyPO {
    /**
     * 主键
     */
    @TableId("SQID")
    private int sqid;

    /**
     * 申请描述
     */
    @TableField(value="SQMS",el="sqms,jdbcType=VARCHAR")
    private String sqms;

    /**
     * 申请材料
     */
    @TableField(value="SQCL",el="sqcl,jdbcType=VARCHAR")
    private String sqcl;

    /**
     * 申请人
     */
    @TableField(value="SQR",el="sqr,jdbcType=VARCHAR")
    private String sqr;

    /**
     * 文件路径
     */
    @TableField(value="PATH",el="path,jdbcType=VARCHAR")
    private String path;

    /**
     * 发布id
     */
    @TableField(value="FBID",el="fbid,jdbcType=INTEGER")
    private int fbid;

    /**
     * 审批状态（0：“暂存”，1：“已提交”，2：“审批中”，3：“审批通过”，4：“审批不通过”，5：“驳回”）
     */
    @TableField(value="SPZT",el="spzt,jdbcType=INTEGER")
    private int spzt;
}
