package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.ScholarshipPublishDTO;
import com.biye.cjm.po.ScholarshipPublishPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-15  19:39
 */
@Mapper
public interface ScholarshipPublishMapper extends BaseMapper<ScholarshipPublishPO> {
    /**
     * 查询
     * @return
     */
    public List<ScholarshipPublishDTO> query(Map<String, Object> param);

    /**
     * 查询数据总数
     * @return
     */
    public int total(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(ScholarshipPublishDTO scholarshipPublishDTO);

    /**
     * 插入
     * @return
     */
    public Integer update(ScholarshipPublishDTO scholarshipPublishDTO);

    /**
     * 查询发布的奖学金名称列表
     * @return
     */
    public List<Map<String,String>> queryName();

    /**
     * 查询是否存在相同的奖学金名称
     * @return
     */
    public Boolean hasSameName(String lx);
}
