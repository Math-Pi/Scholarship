package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.dto.UserDTO;
import com.biye.cjm.po.RulePO;
import com.biye.cjm.po.UserPO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */

@Repository
public interface UserMapper extends BaseMapper<UserPO> {
    /**
     * 查询
     * @param params
     * @return
     */
    public List<Map<String,Object>> query(Map<String,Object> params);

    /**
     * 查询数据总数
     * @param params
     * @return
     */
    public int total(Map<String,Object> params);

    /**
     * 插入
     * @return
     */
    public Integer insert(UserDTO userDTO);

    /**
     * 根据用户名查询用户数据
     * @param username
     * @return
     */
    UserPO getUserByUserName(String username);

    /**
     * 更新密码
     * @param username
     * @return
     */
    public int updatePass(String username,String password);
}
