package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.ProfessionDTO;
import com.biye.cjm.po.ProfessionPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  19:30
 */
@Repository
public interface ProfessionMapper extends BaseMapper<ProfessionPO> {
    /**
     * 查询
     * @return
     */
    public List<ProfessionDTO> query(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(ProfessionDTO professionDTO);

    /**
     * 查询数据总数
     * @return
     */
    public int total();
}
