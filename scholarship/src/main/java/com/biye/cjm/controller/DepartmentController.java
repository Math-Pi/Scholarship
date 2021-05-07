package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.po.DepartmentPO;
import com.biye.cjm.service.DepartmentService;
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
 * @Author CJM
 * @Date 2021-03-16  18:16
 */
@RestController
@RequestMapping("/department")
@Api(tags = "学院信息相关服务API")
public class DepartmentController {
    private static Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;
    @ApiOperation(value = "查询学院信息" , notes = "查询全部学院信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        System.out.println(param);
        try {
            List<DepartmentDTO> departmentDTOS= departmentService.query(param);
            return JsonResult.success(departmentDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加学院信息" , notes = "通过录入的参数，添加一条学院信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "学院信息", required = true, dataType = "DepartmentDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody DepartmentDTO departmentDTO) {
        try {
            Integer result= departmentService.create(departmentDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新学院信息" , notes = "通过录入的参数，修改一条学院信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "学院信息", required = true, dataType = "DepartmentDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody DepartmentDTO departmentDTO) {
        try {
            int rs = departmentService.update(departmentDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条学院信息" , notes = "通过主键，删除单条学院信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = departmentService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
