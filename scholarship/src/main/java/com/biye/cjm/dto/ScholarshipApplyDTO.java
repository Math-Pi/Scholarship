package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author CJM
 * @Date 2021-03-17  12:54
 */
@Data
public class ScholarshipApplyDTO {
    /**
     * 主键
     */
    private int sqid;

    /**
     * 申请描述
     */
    private String sqms;

    /**
     * 申请材料
     */
    private String sqcl;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 申请人
     */
    private String sqr;

    /**
     * 发布id
     */
    private int fbid;

    /**
     * 审批状态（0：“暂存”，1：“已提交”，2：“审批中”，3：“审批通过”，4：“审批不通过”，5：“驳回”）
     */
    private int spzt;
}
