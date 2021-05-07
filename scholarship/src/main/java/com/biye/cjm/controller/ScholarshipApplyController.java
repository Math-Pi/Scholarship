package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.ScholarshipApplyDTO;
import com.biye.cjm.service.ScholarshipApplyService;
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
 * @Date 2021-03-17  12:54
 */
@RestController
@RequestMapping("/apply")
@Api(tags = "奖学金-奖学金申请相关服务API")
public class ScholarshipApplyController {
    private static Logger logger = LoggerFactory.getLogger(ScholarshipApplyController.class);

    @Autowired
    private ScholarshipApplyService scholarshipApplyService;

    @ApiOperation(value = "查询奖学金申请信息" , notes = "查询全部奖学金申请信息" , httpMethod = "GET")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public JsonResult query(@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {
        try {
            List<Map<String,Object>> scholarshipApplyPOS= scholarshipApplyService.query(pageNo,pageSize);
            return JsonResult.success(scholarshipApplyPOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加奖学金申请" , notes = "通过录入的参数，添加一条奖学金申请" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金申请", required = true, dataType = "ScholarshipApplyDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ScholarshipApplyDTO scholarshipApplyDTO) {
        try {
            Integer result= scholarshipApplyService.create(scholarshipApplyDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新奖学金申请" , notes = "通过录入的参数，修改一条奖学金申请" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金申请", required = true, dataType = "ScholarshipApplyDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody ScholarshipApplyDTO scholarshipApplyDTO) {
        try {
            int rs = scholarshipApplyService.update(scholarshipApplyDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条奖学金申请" , notes = "通过主键，删除单条奖学金申请" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> pkids) {
        try {
            int rs = scholarshipApplyService.delete(pkids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "送审" , notes = "通过主键列表，批量送审" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "pkids", dataType = "List", required = true, value = "主键")
    })
    @RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
    public JsonResult updateStatus( @RequestBody List<Integer> pkids) {
        try {
            int rs = scholarshipApplyService.updateStatus(pkids);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
