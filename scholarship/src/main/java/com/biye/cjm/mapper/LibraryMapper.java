package com.biye.cjm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.po.LibraryPO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-14  17:32
 */
@Mapper
public interface LibraryMapper extends BaseMapper<LibraryPO> {

    /**
     * 查询
     * @return
     */
    public List<LibraryDTO> query(int pageNo,int pageSize);

    /**
     * 查询数据总数
     * @return
     */
    public int total();

    /**
     * 插入
     * @return
     */
    public Integer insert(LibraryDTO libraryDTO);

}
