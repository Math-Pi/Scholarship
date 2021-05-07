package com.biye.cjm.service.impl;

import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.dto.ScholarshipApplyDTO;
import com.biye.cjm.mapper.ScholarshipApplyMapper;
import com.biye.cjm.po.ScholarshipApplyPO;
import com.biye.cjm.service.ScholarshipApplyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  12:55
 */
@Service
public class ScholarshipApplyServiceImpl implements ScholarshipApplyService {
    @Autowired
    private ScholarshipApplyMapper scholarshipApplyMapper;

    @Override
    public List<Map<String,Object>> query(int pageNo,int pageSize){
        List<Map<String,Object>> maps;
        maps=scholarshipApplyMapper.query(pageNo,pageSize);
        int total=scholarshipApplyMapper.total();
        if(maps.size()>0)
            maps.get(0).put("total",total);
        return maps;
    }

    @Override
    public Integer create(ScholarshipApplyDTO scholarshipApplyDTO){
        boolean flag=scholarshipApplyMapper.hasSameApply(scholarshipApplyDTO.getFbid(),scholarshipApplyDTO.getSqr());
        System.out.println(flag);
        if(flag){
            return 0;
        }
        return scholarshipApplyMapper.insert(scholarshipApplyDTO);
    }

    @Override
    public Integer update(ScholarshipApplyDTO scholarshipApplyDTO){
        return scholarshipApplyMapper.alter(scholarshipApplyDTO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return scholarshipApplyMapper.deleteById(pkid);
    }

    @Override
    public Integer updateStatus(List<Integer> pkids) {
        return scholarshipApplyMapper.updateStatus(pkids);
    }
}
