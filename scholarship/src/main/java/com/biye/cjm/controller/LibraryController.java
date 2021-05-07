package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.po.LibraryPO;
import com.biye.cjm.service.LibraryService;
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
 * @Date 2021-03-14  17:30
 */
@RestController
@RequestMapping("/library")
@Api(tags = "代码库相关服务API")
public class LibraryController {
    private static Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

    @ApiOperation(value = "查询奖学金信息" , notes = "查询全部信息" , httpMethod = "GET")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public JsonResult query(@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {

        try {
            List<LibraryDTO> libraryDTOS= libraryService.query(pageNo,pageSize);
            return JsonResult.success(libraryDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加代码映射" , notes = "通过录入的参数，添加一条代码映射" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "代码映射", required = true, dataType = "RuleDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody LibraryDTO libraryDTO) {
        try {
            Integer result= libraryService.create(libraryDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新代码映射" , notes = "通过录入的参数，修改一条代码映射" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "代码映射", required = true, dataType = "LibraryDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody LibraryDTO libraryDTO) {
        try {
            int rs = libraryService.update(libraryDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条代码映射" , notes = "通过主键，删除单条代码映射" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dmid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> dmids) {
        try {
            int rs = libraryService.delete(dmids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
