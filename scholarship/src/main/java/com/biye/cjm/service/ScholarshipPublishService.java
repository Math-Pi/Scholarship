package com.biye.cjm.service;

import com.biye.cjm.dto.ScholarshipPublishDTO;
import com.biye.cjm.po.ScholarshipPublishPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-15  19:39
 */
public interface ScholarshipPublishService {
    /**
     *  查询
     * @return
     */
    public List<ScholarshipPublishDTO> query(Map<String, Object> param);

    /**
     *  查询一条数据
     * @return
     */
    public ScholarshipPublishPO get(Integer pkid);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(ScholarshipPublishDTO scholarshipPublishDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(ScholarshipPublishDTO scholarshipPublishDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);

    /**
     *  查询发布一条数据
     * @return
     */
    public List<Map<String,String>> queryName();
}
