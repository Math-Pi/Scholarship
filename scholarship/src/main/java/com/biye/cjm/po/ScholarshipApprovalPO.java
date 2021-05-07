package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-17  15:09
 */
@TableName("scholarshipApproval")
@Data
public class ScholarshipApprovalPO {
    /**
     * 主键
     */
    @TableId("SPID")
    private int spid;

    /**
     * 审批状态（0：“暂存”，1：“已提交”，2：“审批中”，3：“审批通过”，4：“审批不通过”，5：“驳回”）
     */
    @TableField(value="SPZT",el="spzt,jdbcType=INTEGER")
    private int spzt;

    /**
     * 辅导员审批意见
     */
    @TableField(value="SPYJ1",el="spyj1,jdbcType=VARCHAR")
    private String spyj1;

    /**
     * 学工处审批意见
     */
    @TableField(value="SPYJ2",el="spyj2,jdbcType=VARCHAR")
    private String spyj2;

    /**
     * 申请id
     */
    @TableField(value="SQID",el="sqid,jdbcType=INTEGER")
    private int sqid;
}
