package com.biye.cjm.service;

import com.biye.cjm.dto.ScholarshipDefineDTO;
import com.biye.cjm.po.ScholarshipDefinePO;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
public interface ScholarshipDefineService {
    /**
     *  查询
     * @return
     */
    public List<ScholarshipDefineDTO> query(Map<String, Object> param);

    /**
     *  查询一条数据
     * @return
     */
    public ScholarshipDefinePO get(Integer pkid);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(ScholarshipDefineDTO scholarshipDefineDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(ScholarshipDefineDTO scholarshipDefineDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);

    /**
     *  批量删除
     * @return
     */
    public Integer deleteByMulti(List<String> pkids);

    /**
     *  查询启用的奖学金类型列表
     * @return
     */
    public List<Map<String,String>> queryType();

    /**
     *  根据主键查询是否分分级
     * @return
     */
    public Boolean querySffdj(int dyid);
}
