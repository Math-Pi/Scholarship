package com.biye.cjm.service.impl;

import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.dto.ProfessionDTO;
import com.biye.cjm.mapper.ProfessionMapper;
import com.biye.cjm.po.ProfessionPO;
import com.biye.cjm.service.ProfessionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  19:30
 */
@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    private ProfessionMapper professionMapper;

    @Override
    public List<ProfessionDTO> query(@RequestBody Map<String, Object> param){
        List<ProfessionDTO> professionDTOS;
        professionDTOS=professionMapper.query(param);
        int total=professionMapper.total();
        if(professionDTOS.size()>0)
            professionDTOS.get(0).setTotal(total);
        return professionDTOS;
    }

    @Override
    public Integer create(ProfessionDTO professionDTO){
        return professionMapper.insert(professionDTO);
    }

    @Override
    public Integer update(ProfessionDTO professionDTO){
        ProfessionPO professionPO=new ProfessionPO();
        //对象拷贝
        BeanUtils.copyProperties(professionDTO,professionPO);
        return professionMapper.updateById(professionPO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return professionMapper.deleteById(pkid);
    }
}
