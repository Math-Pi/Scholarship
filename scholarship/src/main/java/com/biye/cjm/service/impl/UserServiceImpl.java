package com.biye.cjm.service.impl;

import com.biye.cjm.dto.UserDTO;
import com.biye.cjm.mapper.UserMapper;
import com.biye.cjm.po.UserPO;
import com.biye.cjm.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public List<Map<String,Object>> query(Map<String,Object> params){
        List<Map<String,Object>> maps;
        maps=userMapper.query(params);
        int total=userMapper.total(params);
        if(maps.size()>0)
            maps.get(0).put("total",total);
        return maps;
    }

    @Override
    public Integer create(UserDTO userDTO){
        return userMapper.insert(userDTO);
    }

    @Override
    public Integer update(UserDTO userDTO){
        UserPO userPO=new UserPO();
        //对象拷贝
        BeanUtils.copyProperties(userDTO,userPO);
        return userMapper.updateById(userPO);
    }

    @Override
    public Integer delete(Integer pkid) {
        return userMapper.deleteById(pkid);
    }
    @Override
    public UserPO getUserByUserName(String username) {
        UserPO userPO= userMapper.getUserByUserName(username);
        return userPO;
    }

    @Override
    public int updatePass(String username,String password) {
        return userMapper.updatePass(username,password);
    }
}
