package com.biye.cjm.service;

import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.po.DepartmentPO;
import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  18:18
 */
public interface DepartmentService {
    /**
     * 查询
     * @return
     */
    public List<DepartmentDTO> query(Map<String, Object> param);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(DepartmentDTO departmentDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(DepartmentDTO departmentDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer pkid);
}
