package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.ScholarshipDefineDTO;
import com.biye.cjm.po.ScholarshipDefinePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
@Mapper
public interface ScholarshipDefineMapper extends BaseMapper<ScholarshipDefinePO>{
    /**
     * 查询
     * @return
     */
    public List<ScholarshipDefineDTO> query(Map<String, Object> param);

    /**
     * 查询数据总数
     * @return
     */
    public int total(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(ScholarshipDefineDTO scholarshipDefineDTO);

    /**
     * 更新
     * @return
     */
    public Integer update(ScholarshipDefineDTO scholarshipDefineDTO);

    /**
     * 查询启用的奖学金类型列表
     * @return
     */
    public List<Map<String,String>> queryType();

    /**
     * 查询是否存在相同的奖学金类型
     * @return
     */
    public Boolean hasSameType(String lx);

    /**
     * 根据主键查询是否分分级
     * @return
     */
    public Boolean querySffdj(int dyid);

}
