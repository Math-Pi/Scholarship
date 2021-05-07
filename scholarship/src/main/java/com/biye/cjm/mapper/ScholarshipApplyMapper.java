package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.ScholarshipApplyDTO;
import com.biye.cjm.po.ScholarshipApplyPO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  12:54
 */
@Mapper
public interface ScholarshipApplyMapper extends BaseMapper<ScholarshipApplyPO> {
    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(int pageNo,int pageSize);

    /**
     * 查询数据总数
     * @return
     */
    public int total();

    /**
     * 插入
     * @return
     */
    public Integer insert(ScholarshipApplyDTO scholarshipApplyDTO);

    /**
     * 更新
     * @return
     */
    public Integer alter(ScholarshipApplyDTO scholarshipApplyDTO);

    /**
     * 更新申请奖学金审批状态
     * @return
     */
    @Update({"update scholarshipapply set spzt=#{spzt} where sqid=#{sqid}"})
    public Integer updateSpzt(int sqid,int spzt);

    /**
     * 批量更新申请奖学金审批状态
     * @return
     */
    public Integer updateStatus(List<Integer> pkids);

    /**
     * 查询是否已经申请过同一类型的奖学金
     * @return
     */
    public Boolean hasSameApply(int fbid,String sqr);
}
