package com.biye.cjm.service.impl;

import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.mapper.RuleMapper;
import com.biye.cjm.po.RulePO;
import com.biye.cjm.service.RuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-15  15:31
 */
@Service
public class RuleServiceImpl implements RuleService {
    @Autowired
    private RuleMapper ruleMapper;

    @Override
    public List<RuleDTO> query(int pageNo,int pageSize){
        List<RuleDTO> ruleDTOS;
        ruleDTOS=ruleMapper.query(pageNo,pageSize);
        int total=ruleMapper.total();
        if(ruleDTOS.size()>0)
            ruleDTOS.get(0).setTotal(total);
        return ruleDTOS;
    }

    @Override
    public Integer create(RuleDTO ruleDTO){
        return ruleMapper.insert(ruleDTO);
    }

    @Override
    public Integer update(RuleDTO ruleDTO){
        RulePO rulePO=new RulePO();
        //对象拷贝
        BeanUtils.copyProperties(ruleDTO,rulePO);
        return ruleMapper.updateById(rulePO);
    }

    @Override
    public Integer delete(Integer gzid) {
        return ruleMapper.deleteById(gzid);
    }
}
