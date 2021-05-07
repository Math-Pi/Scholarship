package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-17  15:09
 */
@Data
public class ScholarshipApprovalDTO {
    /**
     * 主键
     */
    private int spid;

    /**
     * 审批状态（0：“暂存”，1：“已提交”，2：“审批中”，3：“审批通过”，4：“审批不通过”，5：“驳回”）
     */
    private int spzt;

    /**
     * 审批意见
     */
    private String spyj;

    /**
     * 申请id
     */
    private int sqid;
}
