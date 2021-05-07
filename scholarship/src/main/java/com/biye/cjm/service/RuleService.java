package com.biye.cjm.service;

import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.po.RulePO;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-15  15:30
 */
public interface RuleService {

    /**
     * 查询
     * @return
     */
    public List<RuleDTO> query(int pageNo,int pageSize);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(RuleDTO ruleDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(RuleDTO ruleDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer gzid);
}
