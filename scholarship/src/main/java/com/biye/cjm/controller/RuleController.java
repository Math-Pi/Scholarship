package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.po.RulePO;
import com.biye.cjm.service.RuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-15  15:25
 */
@RestController
@RequestMapping("/rule")
@Api(tags = "代码规则相关服务API")
public class RuleController {
    private static Logger logger = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private RuleService ruleService;

    @ApiOperation(value = "查询代码规则信息" , notes = "查询全部规则信息" , httpMethod = "GET")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public JsonResult query(@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {
        try {
            List<RuleDTO> ruleDTOS= ruleService.query(pageNo,pageSize);
            return JsonResult.success(ruleDTOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "添加代码规则" , notes = "通过录入的参数，添加一条代码规则" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "奖学金定义", required = true, dataType = "RuleDTO")
    })
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public JsonResult create(@RequestBody RuleDTO ruleDTO) {
        try {
            Integer result= ruleService.create(ruleDTO);
            return JsonResult.success(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "更新代码规则" , notes = "通过录入的参数，修改一条代码规则" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "dto", value = "代码规则", required = true, dataType = "RuleDTO")
    })
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public JsonResult update(@RequestBody RuleDTO ruleDTO) {
        try {
            int rs = ruleService.update(ruleDTO);
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "删除单条代码规则" , notes = "通过主键，删除单条代码规则" , httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", name = "gzid", dataType = "Integer", required = true, value = "主键")
    })
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public JsonResult delete( @RequestBody List<Integer> gzids) {
        try {
            int rs = ruleService.delete(gzids.get(0));
            return JsonResult.success(rs);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
