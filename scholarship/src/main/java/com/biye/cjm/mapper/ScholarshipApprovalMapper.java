package com.biye.cjm.mapper;

import com.biye.cjm.dto.ScholarshipApprovalDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  15:09
 */
@Mapper
public interface ScholarshipApprovalMapper {
    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(Map<String, Object> param);

    /**
     * 查询数据总数
     * @return
     */
    public int total(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(ScholarshipApprovalDTO scholarshipApprovalDTO);

    /**
     * 更新
     * @return
     */
    public Integer update(ScholarshipApprovalDTO scholarshipApprovalDTO);

    /**
     * 查询奖学金申请是否已审批
     * @param sqid
     * @return
     */
    @Select({"select count(1) from scholarshipapproval where SQID=#{sqid}"})
    public int select(int sqid);

    /**
     * 生成获奖名单
     * @param sqid
     * @return
     */
    public int create(int sqid);
}
