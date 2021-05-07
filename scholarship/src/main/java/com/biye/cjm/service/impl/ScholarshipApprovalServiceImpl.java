package com.biye.cjm.service.impl;

import com.biye.cjm.dto.ScholarshipApprovalDTO;
import com.biye.cjm.mapper.ScholarshipApplyMapper;
import com.biye.cjm.mapper.ScholarshipApprovalMapper;
import com.biye.cjm.po.ScholarshipApplyPO;
import com.biye.cjm.service.ScholarshipApprovalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  15:10
 */
@Service
public class ScholarshipApprovalServiceImpl implements ScholarshipApprovalService {
    @Autowired
    private ScholarshipApplyMapper scholarshipApplyMapper;

    @Autowired
    private ScholarshipApprovalMapper scholarshipApprovalMapper;

    @Override
    public List<Map<String,Object>> query(Map<String, Object> param){
        List<Map<String,Object>> maps;
        maps=scholarshipApprovalMapper.query(param);
        int total=scholarshipApprovalMapper.total(param);
        if(maps.size()>0)
            maps.get(0).put("total",total);
        return maps;
    }

    @Override
    public Integer approval(ScholarshipApprovalDTO scholarshipApprovalDTO){
        int flag=scholarshipApprovalMapper.select(scholarshipApprovalDTO.getSqid());
        scholarshipApplyMapper.updateSpzt(scholarshipApprovalDTO.getSqid(),scholarshipApprovalDTO.getSpzt());   //更新申请表审批状态
        //如果全部审批通过则插入获奖人员名单
        if(scholarshipApprovalDTO.getSpzt()==3){
            scholarshipApprovalMapper.create(scholarshipApprovalDTO.getSqid());
        }
        if (flag!=0) {
            //如果是已经审批过的则进行更新
            return scholarshipApprovalMapper.update(scholarshipApprovalDTO);
        }else {
            //如果是没有审批过的则进行插入
            return scholarshipApprovalMapper.insert(scholarshipApprovalDTO);
        }

    }
}
