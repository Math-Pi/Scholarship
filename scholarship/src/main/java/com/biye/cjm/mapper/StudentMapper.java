package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.StudentDTO;
import com.biye.cjm.po.StudentPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  22:15
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentPO> {
    /**
     * 查询
     * @return
     */
    public List<StudentDTO> query(Map<String, Object> param);

    /**
     * 查询数据总数
     * @return
     */
    public int total(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(StudentDTO studentDTO);

    /**
     * 更新
     * @return
     */
    public Integer update(StudentDTO studentDTO);
}
