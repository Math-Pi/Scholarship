package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.dto.ScholarshipListDTO;
import com.biye.cjm.service.ScholarshipListService;
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
 * @Date 2021-03-17  10:49
 */
@RestController
@RequestMapping("/scholarshipList")
@Api(tags = "奖学金-奖学金获奖名单相关服务API")
public class ScholarshipListController {
    private static Logger logger = LoggerFactory.getLogger(ScholarshipListController.class);

    @Autowired
    private ScholarshipListService scholarshipListService;

    @ApiOperation(value = "查询获奖名单信息" , notes = "查询全部获奖名单信息" , httpMethod = "GET")
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public JsonResult query(@RequestParam(value = "pageNo") int pageNo,@RequestParam(value = "pageSize") int pageSize) {
        try {
            List<Map<String,Object>> scholarshipListPOS= scholarshipListService.query(pageNo,pageSize);
            return JsonResult.success(scholarshipListPOS);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return JsonResult.failure(e.getMessage());
        }
    }

}
