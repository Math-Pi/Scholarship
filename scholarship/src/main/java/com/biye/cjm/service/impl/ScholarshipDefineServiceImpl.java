package com.biye.cjm.service.impl;

import com.biye.cjm.controller.ScholarshipDefineController;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.dto.ScholarshipDefineDTO;
import com.biye.cjm.mapper.ScholarshipDefineMapper;
import com.biye.cjm.po.ScholarshipDefinePO;
import com.biye.cjm.service.ScholarshipDefineService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
@Service
public class ScholarshipDefineServiceImpl implements ScholarshipDefineService {

    private static Logger logger = LoggerFactory.getLogger(ScholarshipDefineController.class);

    @Autowired
    private ScholarshipDefineMapper scholarshipDefineMapper;

    @Override
    public List<ScholarshipDefineDTO> query(Map<String, Object> param){
        List<ScholarshipDefineDTO> scholarshipDefineDTOS;
        scholarshipDefineDTOS=scholarshipDefineMapper.query(param);
        int total=scholarshipDefineMapper.total(param);
        if(scholarshipDefineDTOS.size()>0)
            scholarshipDefineDTOS.get(0).setTotal(total);
        return scholarshipDefineDTOS;
    }

    @Override
    public ScholarshipDefinePO get(Integer pkid){
        return scholarshipDefineMapper.selectById(pkid);
    }

    @Override
    public Integer create(ScholarshipDefineDTO scholarshipDefineDTO){
        boolean flag=scholarshipDefineMapper.hasSameType(scholarshipDefineDTO.getLx());
        if(flag){
            return 0;
        }else {
            return scholarshipDefineMapper.insert(scholarshipDefineDTO);
        }
    }

    @Override
    public Integer update(ScholarshipDefineDTO scholarshipDefineDTO){
        System.out.println(scholarshipDefineDTO);
        ScholarshipDefinePO scholarshipDefinePO=new ScholarshipDefinePO();
        //对象拷贝
        BeanUtils.copyProperties(scholarshipDefineDTO,scholarshipDefinePO);
        return scholarshipDefineMapper.updateById(scholarshipDefinePO);
    }

    @Override
    public Integer delete(Integer pkid) {
        Integer result=scholarshipDefineMapper.deleteById(pkid);
        return result;
    }

    @Override
    public Integer deleteByMulti(List<String> pkids) {
        try {

            Integer result=scholarshipDefineMapper.deleteBatchIds(pkids);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return pkids.size();
    }

    @Override
    public List<Map<String,String>> queryType() {
        return scholarshipDefineMapper.queryType();
    }

    @Override
    public Boolean querySffdj(int dyid) {
        return scholarshipDefineMapper.querySffdj(dyid);
    }
}
