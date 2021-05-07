package com.biye.cjm.service.impl;

import com.biye.cjm.dto.StudentDTO;
import com.biye.cjm.mapper.StudentMapper;
import com.biye.cjm.po.StudentPO;
import com.biye.cjm.service.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  22:15
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDTO> query(Map<String, Object> param){
        List<StudentDTO> studentDTOS;
        studentDTOS=studentMapper.query(param);
        int total=studentMapper.total(param);
        if(studentDTOS.size()>0)
            studentDTOS.get(0).setTotal(total);
        return studentDTOS;
    }

    @Override
    public Integer create(StudentDTO studentDTO){
        return studentMapper.insert(studentDTO);
    }

    @Override
    public Integer update(StudentDTO studentDTO){
        StudentPO studentPO=new StudentPO();
        //对象拷贝
        BeanUtils.copyProperties(studentDTO,studentPO);
        return studentMapper.updateById(studentPO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return studentMapper.deleteById(pkid);
    }
}
