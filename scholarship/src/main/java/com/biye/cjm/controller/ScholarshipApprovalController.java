package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.ScholarshipApprovalDTO;
import com.biye.cjm.service.ScholarshipApprovalService;
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
 * @Date 2021-03-17  15:09
 */
@RestController
@RequestMapping("/approval")
@Api(tags = "奖学金-奖学金审批相关服务API")
public class ScholarshipApprovalController {
    private static Logger logger = LoggerFactory.getLogger(ScholarshipApprovalController.class);

    @Autowired
    private ScholarshipApprovalService scholarshipApprovalService;

    @ApiOperation(value = "查询奖学金申请信息" , notes = "查询全部奖学金申请信息" , httpMethod = "POST")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public JsonResult query(@RequestBody Map<String, Object> param) {
        try {
            List<Map<String,Object>> scholarshipApplyPOS= scholarshipApprovalService.query(param);
            return JsonResult.success(scholarshipApplyPOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "审批奖学金申请" , notes = "通过录入的参数，添加奖学金申请" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金审批", required = true, dataType = "ScholarshipApplyDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody ScholarshipApprovalDTO scholarshipApprovalDTO) {
        try {
            Integer result= scholarshipApprovalService.approval(scholarshipApprovalDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
