package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.dto.ScholarshipDefineDTO;
import com.biye.cjm.po.RulePO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-14  21:48
 */
@Mapper
public interface RuleMapper extends BaseMapper<RulePO> {

    /**
     * 查询
     * @return
     */
    public List<RuleDTO> query(int pageNo,int pageSize);

    /**
     * 查询数据总数
     * @return
     */
    public int total();

    /**
     * 插入
     * @return
     */
    public Integer insert(RuleDTO ruleDTO);
}
