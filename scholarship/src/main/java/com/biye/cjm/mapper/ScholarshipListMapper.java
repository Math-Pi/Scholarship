package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.po.ScholarshipListPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  10:49
 */
@Mapper
public interface ScholarshipListMapper extends BaseMapper<ScholarshipListPO> {
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
}
