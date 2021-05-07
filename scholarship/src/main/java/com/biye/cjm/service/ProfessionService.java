package com.biye.cjm.service;

import com.biye.cjm.dto.ProfessionDTO;
import com.biye.cjm.po.ProfessionPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  19:30
 */
public interface ProfessionService {
    /**
     * 查询
     * @return
     */
    public List<ProfessionDTO> query(Map<String, Object> param);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(ProfessionDTO professionDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(ProfessionDTO professionDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);
}
