package com.biye.cjm.vo;

import java.io.Serializable;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
public class ScholarshipDefineVO implements Serializable {
    /**
     * 主键
     */
    private String dyid;
    /**
     * 奖学金名称
     */
    private String mc;
    /**
     * 奖励级别,代码库JXJ_JLJBDM（1：国家级、2：省级、3：校级、4:院级）
     */
    private String jljbdm;
    /**
     * 资金来源,代码库JXJ_ZJLYDM（1:国家拨款、2：企业拨款:、3：学校拨款，99：其它）
     */
    private String zjlydm;
    /**
     * 是否分等级（1：是，0：否）
     */
    private String sffdj;
    /**
     * 评奖流程id
     */
    private String lcid;
    /**
     * 简介描述
     */
    private String jjms;
    /**
     * 附件id，关联附件表
     */
    private String fjid;
    /**
     * 参评范围id（引入资格鉴定器id）
     */
    private String cpfwid;
    /**
     * 是否启用（1：启用，0：停用）
     */
    private String sfqy;

    public String getDyid() {
        return dyid;
    }
    public void setDyid(String dyid) {
        this.dyid = dyid == null ? null : dyid.trim();
    }
    public String getMc() {
        return mc;
    }
    public void setMc(String mc) {
        this.mc = mc == null ? null : mc.trim();
    }
    public String getJljbdm() {
        return jljbdm;
    }
    public void setJljbdm(String jljbdm) {
        this.jljbdm = jljbdm == null ? null : jljbdm.trim();
    }
    public String getZjlydm() {
        return zjlydm;
    }
    public void setZjlydm(String zjlydm) {
        this.zjlydm = zjlydm == null ? null : zjlydm.trim();
    }
    public String getSffdj() {
        return sffdj;
    }
    public void setSffdj(String sffdj) {
        this.sffdj = sffdj == null ? null : sffdj.trim();
    }
    public String getLcid() {
        return lcid;
    }
    public void setLcid(String lcid) {
        this.lcid = lcid == null ? null : lcid.trim();
    }
    public String getJjms() {
        return jjms;
    }
    public void setJjms(String jjms) {
        this.jjms = jjms == null ? null : jjms.trim();
    }
    public String getFjid() {
        return fjid;
    }
    public void setFjid(String fjid) {
        this.fjid = fjid == null ? null : fjid.trim();
    }
    public String getCpfwid() {
        return cpfwid;
    }
    public void setCpfwid(String cpfwid) {
        this.cpfwid = cpfwid == null ? null : cpfwid.trim();
    }
    public String getSfqy() {
        return sfqy;
    }
    public void setSfqy(String sfqy) {
        this.sfqy = sfqy == null ? null : sfqy.trim();
    }
}
