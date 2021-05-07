package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.DepartmentDTO;
import com.biye.cjm.po.DepartmentPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  18:17
 */
@Mapper
public interface DepartmentMapper extends BaseMapper<DepartmentPO> {
    /**
     * 查询
     * @return
     */
    public List<DepartmentDTO> query(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(DepartmentDTO departmentDTO);

    /**
     * 查询是否存在相同的学院编号
     * @return
     */
    boolean hasSameXybh(String xybh);

    /**
     * 查询数据总数
     * @return
     */
    public int total();
}
