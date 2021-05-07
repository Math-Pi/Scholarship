package com.biye.cjm.service;

import com.biye.cjm.dto.ScholarshipListDTO;
import com.biye.cjm.po.ScholarshipListPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  10:49
 */
public interface ScholarshipListService {
    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(int pageNo,int pageSize);

}
