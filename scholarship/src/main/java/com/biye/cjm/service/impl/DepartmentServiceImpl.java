package com.biye.cjm.service.impl;

import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.mapper.DepartmentMapper;
import com.biye.cjm.po.DepartmentPO;
import com.biye.cjm.service.DepartmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  18:18
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDTO> query(Map<String, Object> param){
        List<DepartmentDTO> departmentDTOS;
        departmentDTOS=departmentMapper.query(param);
        int total=departmentMapper.total();
        if(departmentDTOS.size()>0)
            departmentDTOS.get(0).setTotal(total);
        return departmentDTOS;
    }

    @Override
    public Integer create(DepartmentDTO departmentDTO){
        boolean flag=departmentMapper.hasSameXybh(departmentDTO.getXybh());
        if(flag){
            return 0;
        }else {
            return departmentMapper.insert(departmentDTO);
        }
    }

    @Override
    public Integer update(DepartmentDTO departmentDTO){
        DepartmentPO departmentPO=new DepartmentPO();
        //对象拷贝
        BeanUtils.copyProperties(departmentDTO,departmentPO);
        return departmentMapper.updateById(departmentPO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return departmentMapper.deleteById(pkid);
    }
}
