package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.ScholarshipDefineDTO;
import com.biye.cjm.po.ScholarshipDefinePO;
import com.biye.cjm.service.ScholarshipDefineService;
import com.biye.cjm.utils.HttpContextUtils;
import com.biye.cjm.utils.SpringContextUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author chenjiaming
 * @Date 2021/2/24
 */
@RestController
@RequestMapping("/scholarshipDefine")
@Api(tags = "奖学金-奖学金定义相关服务API")
public class ScholarshipDefineController {
    private static Logger logger = LoggerFactory.getLogger(ScholarshipDefineController.class);
    @Autowired
    private ScholarshipDefineService scholarshipDefineService;

    @ApiOperation(value = "查询奖学金信息" , notes = "查询全部信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        try {
            List<ScholarshipDefineDTO> scholarshipDefineDTOS= scholarshipDefineService.query(param);
            return JsonResult.success(scholarshipDefineDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询单条奖学金定义" , notes = "根据id查询查询单条奖学金定义" , httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", name = "pkid", dataType = "Integer", required = true, value = "表的主键")
    })
    @RequestMapping(value = "/{pkid}/get", method = RequestMethod.GET)
    public JsonResult query2(@PathVariable() Integer pkid) {
        try {
            ScholarshipDefinePO scholarshipDefinePO= scholarshipDefineService.get(pkid);
            return JsonResult.success(scholarshipDefinePO);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    @ApiOperation(value = "添加奖学金定义" , notes = "通过录入的参数，添加一条奖学金定义" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金定义", required = true, dataType = "ScholarshipDefineDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ScholarshipDefineDTO scholarshipDefineDTO) {
        try {
            Integer result= scholarshipDefineService.create(scholarshipDefineDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新奖学金定义" , notes = "通过录入的参数，修改一条奖学金定义" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金定义", required = true, dataType = "ScholarshipDefineDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ScholarshipDefineDTO dto) {
        try {
            int rs = scholarshipDefineService.update(dto);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条奖学金定义" , notes = "通过主键，删除单条奖学金定义" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = scholarshipDefineService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
    @ApiOperation(value = "批量删除奖学金定义" , notes = "通过主键，批量删除多条奖学金定义" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键")
    })
    @RequestMapping(value = "/deleteByMulti", method = RequestMethod.POST)
    public JsonResult deleteByMulti(@RequestBody List<String> pkids) {
        try {
//            //删除前验证该奖学金是否发布过任务
//            if(jxjDyService.cheakTask(pkids)) {
//                throw new NHWarmingException("删除中有发布的任务，不能删除！");
//            }
            int rs = scholarshipDefineService.deleteByMulti(pkids);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询启用的奖学金类型列表" , notes = "查询启用的奖学金类型列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryType", method = RequestMethod.GET)
    public JsonResult queryType() {
        try {
            List<Map<String,String>> list= scholarshipDefineService.queryType();
            return JsonResult.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询奖学金类型是否分分级" , notes = "查询奖学金类型是否分分级" , httpMethod = "GET")
    @RequestMapping(value = "/{dyid}/querySffdj", method = RequestMethod.GET)
    public JsonResult querySffdj(@PathVariable int dyid) {
        try {
            boolean flag= scholarshipDefineService.querySffdj(dyid);
            return JsonResult.success(flag);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
