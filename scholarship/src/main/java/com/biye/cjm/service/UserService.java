package com.biye.cjm.service;

import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.dto.UserDTO;
import com.biye.cjm.po.RulePO;
import com.biye.cjm.po.UserPO;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */


public interface UserService {

    /**
     * 查询
     * @return
     */
    public List<Map<String,Object>> query(Map<String,Object> params);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(UserDTO userDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(UserDTO userDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);

    /**
     * 根据用户名查询用户数据
     * @param username
     * @return
     */
    public UserPO getUserByUserName(String username);

    /**
     * 更新密码
     * @param username
     * @return
     */
    public int updatePass(String username,String password);

}