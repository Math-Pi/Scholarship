package com.biye.cjm.service;

import com.biye.cjm.dto.StudentDTO;
import com.biye.cjm.po.StudentPO;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  22:15
 */
public interface StudentService {
    /**
     * 查询
     *
     * @return
     */
    public List<StudentDTO> query(Map<String, Object> param);

    /**
     * 插入一条数据
     *
     * @return
     */
    public Integer create(StudentDTO studentDTO);

    /**
     * 更新一条数据
     *
     * @return
     */
    public Integer update(StudentDTO studentDTO);

    /**
     * 删除一条数据
     *
     * @return
     */
    public Integer delete(Integer pkid);
}
