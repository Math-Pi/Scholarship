package com.biye.cjm.controller;

import com.biye.cjm.common.json.JsonResult;
import com.biye.cjm.utils.HttpContextUtils;
import com.biye.cjm.utils.SpringContextUtils;
import io.swagger.annotations.Api;
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
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-22  21:17
 */
@RestController
@RequestMapping("/upload")
@Api(tags = "奖学金-文件上传相关服务API")
public class FileUploadController {

    /**
     * 上传文件
     */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult uploadImage(@RequestParam(value = "avatar") MultipartFile avatar){

        Map<String,Object> map = new HashMap<>();
        if (avatar.isEmpty()) {
            return JsonResult.failure("上传失败");
        }else {

            //保存时的文件名
            DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
            Calendar calendar = Calendar.getInstance();
            String dateName = df.format(calendar.getTime())+avatar.getOriginalFilename();

            //保存文件的绝对路径
            WebApplicationContext webApplicationContext = (WebApplicationContext) SpringContextUtils.getApplicationContext();
            ServletContext servletContext = webApplicationContext.getServletContext();
            String realPath = System.getProperty("user.dir");//返回项目的根目录

            String filePath = realPath+ File.separator + "src"+ File.separator + "main" + File.separator +"resources" +  File.separator + "upload" + File.separator+dateName;//此路径是放在tomcat war包的绝对路径
            File newFile = new File(filePath);
            System.out.println("filePath=:"+filePath);
            //MultipartFile的方法直接写文件
            try {

                //上传文件
                avatar.transferTo(newFile);
                //数据库存储的相对路径
                String projectPath = servletContext.getContextPath();
                HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
                String contextpath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+projectPath;
                String url = contextpath + "/upload/"+dateName;//此路径是放在tomcat war包的相对路径
                //文件名与文件URL存入数据库表
                System.out.println("url=:"+url);
                map.put("fileUrl",url);//返回前台
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return JsonResult.success(map);
    }
}
