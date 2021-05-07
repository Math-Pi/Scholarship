package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.RuleDTO;
import com.biye.cjm.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-25  1:13
 */
@RestController
@RequestMapping("/common")
@Api(tags = "列表相关服务API")
public class CommonController {
    private static Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "查询学院列表" , notes = "查询学院列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryDept", method = RequestMethod.GET)
    public JsonResult queryDept() {
        try {
            List<Map<String,Object>> maps= commonService.queryDept();
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询专业列表" , notes = "查询专业列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryPro", method = RequestMethod.GET)
    public JsonResult queryPro(@RequestParam(value = "xybh",defaultValue = "") String xybh) {
        try {
            List<Map<String,Object>> maps= commonService.queryPro(xybh);
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询班级列表" , notes = "查询班级列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryClass", method = RequestMethod.GET)
    public JsonResult queryClass(@RequestParam(value = "zybh",defaultValue = "") String zybh) {
        try {
            List<Map<String,Object>> maps= commonService.queryClass(zybh);
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询代码规则列表" , notes = "查询代码规则列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryRule", method = RequestMethod.GET)
    public JsonResult queryRule() {
        try {
            List<Map<String,Object>> maps= commonService.queryRule();
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

    @ApiOperation(value = "查询代码映射列表" , notes = "根据规则编号，查询代码映射列表" , httpMethod = "GET")
    @RequestMapping(value = "/queryLib/{gzbh}", method = RequestMethod.GET)
    public JsonResult queryLib(@PathVariable(value = "gzbh") String gzbh) {
        try {
            List<Map<String,Object>> maps= commonService.queryLib(gzbh);
            return JsonResult.success(maps);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }
}
