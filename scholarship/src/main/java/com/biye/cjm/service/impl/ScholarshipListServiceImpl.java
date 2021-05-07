package com.biye.cjm.service.impl;

import com.biye.cjm.mapper.ScholarshipListMapper;
import com.biye.cjm.service.ScholarshipListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-17  10:49
 */
@Service
public class ScholarshipListServiceImpl implements ScholarshipListService {
    @Autowired
    private ScholarshipListMapper scholarshipListMapper;

    @Override
    public List<Map<String,Object>> query(int pageNo,int pageSize){
        List<Map<String,Object>> maps;
        maps=scholarshipListMapper.query(pageNo,pageSize);
        int total=scholarshipListMapper.total();
        if(maps.size()>0)
            maps.get(0).put("total",total);
        return maps;
    }

}
