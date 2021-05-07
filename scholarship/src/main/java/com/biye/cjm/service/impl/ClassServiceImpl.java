package com.biye.cjm.service.impl;

import com.biye.cjm.dto.ClassDTO;
import com.biye.cjm.dto.ProfessionDTO;
import com.biye.cjm.mapper.ClassMapper;
import com.biye.cjm.po.ClassPO;
import com.biye.cjm.service.ClassService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  20:59
 */
@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public List<ClassDTO> query(Map<String, Object> param){
        List<ClassDTO> classDTOS;
        classDTOS=classMapper.query(param);
        int total=classMapper.total();
        if(classDTOS.size()>0)
            classDTOS.get(0).setTotal(total);
        return classDTOS;
    }

    @Override
    public Integer create(ClassDTO classDTO){
        return classMapper.insert(classDTO);
    }

    @Override
    public Integer update(ClassDTO classDTO){
        ClassPO classPO=new ClassPO();
        //对象拷贝
        BeanUtils.copyProperties(classDTO,classPO);
        return classMapper.updateById(classPO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return classMapper.deleteById(pkid);
    }
}
