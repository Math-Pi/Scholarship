package com.biye.cjm.service;

import com.biye.cjm.dto.ClassDTO;
import com.biye.cjm.po.ClassPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  20:59
 */
public interface ClassService {
    /**
     * 查询
     *
     * @return
     */
    public List<ClassDTO> query(Map<String, Object> param);

    /**
     * 插入一条数据
     *
     * @return
     */
    public Integer create(ClassDTO classDTO);

    /**
     * 更新一条数据
     *
     * @return
     */
    public Integer update(ClassDTO classDTO);

    /**
     * 删除一条数据
     *
     * @return
     */
    public Integer delete(Integer pkid);
}
