package com.biye.cjm.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */
@TableName("sys_user")
@Data
public class UserPO {
    @TableId("id")
    private int id;
    @TableField(value = "username")
    private String username;
    @TableField(value = "password")
    private String password;
}