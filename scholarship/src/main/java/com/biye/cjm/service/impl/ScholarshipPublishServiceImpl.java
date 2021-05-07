package com.biye.cjm.service.impl;

import com.biye.cjm.dto.ScholarshipPublishDTO;
import com.biye.cjm.mapper.ScholarshipPublishMapper;
import com.biye.cjm.po.ScholarshipPublishPO;
import com.biye.cjm.service.ScholarshipPublishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-15  19:39
 */
@Service
public class ScholarshipPublishServiceImpl implements ScholarshipPublishService {

    @Autowired
    private ScholarshipPublishMapper scholarshipPublishMapper;

    @Override
    public List<ScholarshipPublishDTO> query(Map<String, Object> param){
        List<ScholarshipPublishDTO> scholarshipPublishDTOS;
        scholarshipPublishDTOS=scholarshipPublishMapper.query(param);
        int total=scholarshipPublishMapper.total(param);
        if(scholarshipPublishDTOS.size()>0)
            scholarshipPublishDTOS.get(0).setTotal(total);
        return scholarshipPublishDTOS;
    }

    @Override
    public ScholarshipPublishPO get(Integer pkid){
        return scholarshipPublishMapper.selectById(pkid);
    }

    @Override
    public Integer create(ScholarshipPublishDTO scholarshipPublishDTO){
        boolean flag=scholarshipPublishMapper.hasSameName(scholarshipPublishDTO.getJxjmc());
        if(flag){
            return 0;
        }else {
            return scholarshipPublishMapper.insert(scholarshipPublishDTO);
        }
    }

    @Override
    public Integer update(ScholarshipPublishDTO scholarshipPublishDTO){
        ScholarshipPublishPO scholarshipPublishPO=new ScholarshipPublishPO();
        //对象拷贝
        BeanUtils.copyProperties(scholarshipPublishDTO,scholarshipPublishPO);
        return scholarshipPublishMapper.update(scholarshipPublishDTO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return scholarshipPublishMapper.deleteById(pkid);
    }

    @Override
    public List<Map<String,String>> queryName() {
        return scholarshipPublishMapper.queryName();
    }
}
