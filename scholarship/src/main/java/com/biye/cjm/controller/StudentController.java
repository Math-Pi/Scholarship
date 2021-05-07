package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.StudentDTO;
import com.biye.cjm.po.StudentPO;
import com.biye.cjm.service.StudentService;
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
 * @Date 2021-03-16  22:15
 */
@RestController
@RequestMapping("/student")
@Api(tags = "学生信息相关服务API")
public class StudentController {
    private static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentService studentService;
    
    @ApiOperation(value = "查询学生信息" , notes = "查询全部学生信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        try {
            List<StudentDTO> studentDTOS= studentService.query(param);
            return JsonResult.success(studentDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加学生信息" , notes = "通过录入的参数，添加一条学生信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "学生信息", required = true, dataType = "StudentDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody StudentDTO studentDTO) {
        try {
            Integer result= studentService.create(studentDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新学生信息" , notes = "通过录入的参数，修改一条学生信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "学院信息", required = true, dataType = "StudentDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody StudentDTO studentDTO) {
        try {
            int rs = studentService.update(studentDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条学生信息" , notes = "通过主键，删除单条学生信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = studentService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
