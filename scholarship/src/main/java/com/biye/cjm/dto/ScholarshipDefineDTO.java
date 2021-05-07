package com.biye.cjm.dto;

import lombok.Data;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
@Data
public class ScholarshipDefineDTO {
    /**
     * 主键
     */
    private int dyid;

    /**
     * 奖学金类型
     */
    private String lx;

    /**
     * 奖励金级别,代码库JLJJB（1：国家级、2：省级、3：校级、4:院级）
     */
    private int jljbdm;

    /**
     * 资金来源,代码库ZJLY（1:国家拨款、2：企业拨款:、3：学校拨款，4：其它）
     */
    private int zjlydm;

    /**
     * 是否分等级（1：是，0：否）
     */
    private int sffdj;

    /**
     * 是否启用（1：启用，0：停用）
     */
    private int sfqy;

    /**
     * 简介描述
     */
    private String jjms;

    /**
     * 附件id，关联附件表
     */
    private int fjid;

    /**
     * 数据总数
     */
    private int total;
}
