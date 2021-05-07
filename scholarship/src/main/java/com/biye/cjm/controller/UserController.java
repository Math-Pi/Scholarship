package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.UserDTO;
import com.biye.cjm.po.UserPO;
import com.biye.cjm.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/23
 */
@RestController
@RequestMapping("/user")
@Api(tags = "奖学金-用户管理相关服务API")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询用户信息" , notes = "查询全部用户信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        try {
            List<Map<String,Object>> maps= userService.query(param);
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加用户" , notes = "通过录入的参数，添加一条用户" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "添加用户", required = true, dataType = "UserDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody UserDTO userDTO) {
        try {
            Integer result= userService.create(userDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新用户" , notes = "通过录入的参数，修改一条用户" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "用户", required = true, dataType = "UserDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody UserDTO userDTO) {
        try {
            int rs = userService.update(userDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条用户" , notes = "通过主键，删除单条用户" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = userService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户名查询用户数据" , notes = "通过用户名，查询用户数据" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "username", dataType = "String", required = true, value = "用户名")
    })
    @RequestMapping(value="/getUserByUserName", method = RequestMethod.GET)
    public JsonResult getUserByUserName(@RequestParam(value = "username") String username){
        UserPO userPO= userService.getUserByUserName(username);
        return JsonResult.success(userPO);
    }

    @ApiOperation(value = "根据用户名查询用户数据" , notes = "通过用户名，查询用户数据" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "username", dataType = "Map", required = true, value = "用户名")
    })
    @RequestMapping(value="/updatePass", method = RequestMethod.POST)
    public JsonResult updatePass(@RequestBody Map<String,String> map){

        try {
            String username = map.get("username");
            String password = map.get("password");
            int rs=userService.updatePass(username,password);
            return JsonResult.success(rs);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }


}
