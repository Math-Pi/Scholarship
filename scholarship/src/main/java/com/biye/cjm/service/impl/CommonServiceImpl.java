package com.biye.cjm.service.impl;

import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.mapper.CommonMapper;
import com.biye.cjm.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-25  1:21
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonMapper commonMapper;
    
    @Override
    public List<Map<String,Object>> queryDept() {
        return commonMapper.queryDept();
    }

    @Override
    public List<Map<String,Object>> queryPro(String xybh) {
        return commonMapper.queryPro(xybh);
    }

    @Override
    public List<Map<String,Object>> queryClass(String zybh) {
        return commonMapper.queryClass(zybh);
    }

    @Override
    public List<Map<String,Object>> queryRule() {
        return commonMapper.queryRule();
    }

    @Override
    public List<Map<String,Object>> queryLib(String gzbh) {
        return commonMapper.queryLib(gzbh);
    }
}
