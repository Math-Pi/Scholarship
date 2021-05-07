package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.ClassDTO;
import com.biye.cjm.po.ClassPO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author CJM
 * @Date 2021-03-16  21:00
 */
@Repository
public interface ClassMapper extends BaseMapper<ClassPO> {
    /**
     * 查询
     * @return
     */
    public List<ClassDTO> query(Map<String, Object> param);

    /**
     * 插入
     * @return
     */
    public Integer insert(ClassDTO classDTO);

    /**
     * 查询数据总数
     * @return
     */
    public int total();
}
