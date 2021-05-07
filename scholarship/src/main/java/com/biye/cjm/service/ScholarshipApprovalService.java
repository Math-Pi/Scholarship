package com.biye.cjm.service;

import com.biye.cjm.dto.ScholarshipApprovalDTO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  15:10
 */
public interface ScholarshipApprovalService {
    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(Map<String, Object> param);

    /**
     *  审批
     * @return
     */
    public Integer approval(ScholarshipApprovalDTO scholarshipApprovalDTO);
}
