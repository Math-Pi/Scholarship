package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.ScholarshipPublishDTO;
import com.biye.cjm.po.ScholarshipPublishPO;
import com.biye.cjm.service.ScholarshipPublishService;
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
 * @Date 2021-03-15  19:27
 */
@RestController
@RequestMapping("/scholarshipPublish")
@Api(tags = "奖学金-奖学金发布相关服务API")
public class ScholarshipPublishController {
    private static Logger logger = LoggerFactory.getLogger(ScholarshipPublishController.class);
    @Autowired
    private ScholarshipPublishService scholarshipPublishService;

    @ApiOperation(value = "查询发布信息" , notes = "查询全部发布信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        try {
            List<ScholarshipPublishDTO> scholarshipPublishDTOS= scholarshipPublishService.query(param);
            return JsonResult.success(scholarshipPublishDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加奖学金发布信息" , notes = "通过录入的参数，添加一条奖学金发布信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金发布信息", required = true, dataType = "ScholarshipPublishDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ScholarshipPublishDTO scholarshipPublishDTO) {
        try {
            Integer result= scholarshipPublishService.create(scholarshipPublishDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新奖学金发布信息" , notes = "通过录入的参数，修改一条奖学金发布信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金发布信息", required = true, dataType = "ScholarshipPublishDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ScholarshipPublishDTO scholarshipPublishDTO) {
        System.out.println(scholarshipPublishDTO);
        try {
            int rs = scholarshipPublishService.update(scholarshipPublishDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条发布信息" , notes = "通过主键，删除单条发布信息" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = scholarshipPublishService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询发布的奖学金名称列表" , notes = "查询发布的奖学金名称列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryName", method = RequestMethod.GET)
    public JsonResult queryType() {
        try {
            List<Map<String,String>> list= scholarshipPublishService.queryName();
            return JsonResult.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
