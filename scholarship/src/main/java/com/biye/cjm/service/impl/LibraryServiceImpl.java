package com.biye.cjm.service.impl;

import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.mapper.LibraryMapper;
import com.biye.cjm.po.LibraryPO;
import com.biye.cjm.service.LibraryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-14  17:32
 */
@Service
public class LibraryServiceImpl implements LibraryService {
    @Autowired
    private LibraryMapper libraryMapper;

    @Override
    public List<LibraryDTO> query(int pageNo,int pageSize){
        List<LibraryDTO> libraryDTOS;
        libraryDTOS=libraryMapper.query(pageNo,pageSize);
        int total=libraryMapper.total();
        if(libraryDTOS.size()>0)
            libraryDTOS.get(0).setTotal(total);
        return libraryDTOS;
    }

    @Override
    public Integer create(LibraryDTO libraryDTO){
        libraryDTO.setGzbh(libraryDTO.getGzmc());   //设置规则名称对应的规则编号
        return libraryMapper.insert(libraryDTO);
    }

    @Override
    public Integer update(LibraryDTO libraryDTO){
        LibraryPO libraryPO=new LibraryPO();
        //对象拷贝
        BeanUtils.copyProperties(libraryDTO,libraryPO);
        return libraryMapper.updateById(libraryPO);
    }

    @Override
    public Integer delete(Integer dmid) {
        return libraryMapper.deleteById(dmid);
    }
}
