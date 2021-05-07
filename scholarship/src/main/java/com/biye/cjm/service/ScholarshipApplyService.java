package com.biye.cjm.service;

import com.biye.cjm.dto.ScholarshipApplyDTO;
import com.biye.cjm.po.ScholarshipApplyPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  12:54
 */
public interface ScholarshipApplyService {
    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(int pageNo,int pageSize);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(ScholarshipApplyDTO scholarshipApplyDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(ScholarshipApplyDTO scholarshipApplyDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);

    /**
     *  删除一条数据
     * @return
     */
    public Integer updateStatus(List<Integer> pkids);
}
