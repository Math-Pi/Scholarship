package com.biye.cjm.service;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-25  1:20
 */
public interface CommonService {
    /**
     * 查询学院列表
     * @return
     */
    public List<Map<String,Object>> queryDept();

    /**
     * 查询专业列表
     * @return
     */
    public List<Map<String,Object>> queryPro(String xybh);

    /**
     * 查询班级列表
     * @return
     */
    public List<Map<String,Object>> queryClass(String zybh);

    /**
     * 查询规则列表
     * @return
     */
    public List<Map<String,Object>> queryRule();

    /**
     * 查询代码映射列表
     * @return
     */
    public List<Map<String,Object>> queryLib(String gzbh);
}
