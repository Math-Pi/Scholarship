package com.biye.cjm.service;

import com.biye.cjm.dto.LibraryDTO;
import com.biye.cjm.po.LibraryPO;

import java.util.List;

/**
 * @Author CJM
 * @Date 2021-03-14  17:31
 */
public interface LibraryService {

    /**
     * 查询
     * @return
     */
    public List<LibraryDTO> query(int pageNo,int pageSize);

    /**
     *  插入一条数据
     * @return
     */
    public Integer create(LibraryDTO libraryDTO);

    /**
     *  更新一条数据
     * @return
     */
    public Integer update(LibraryDTO libraryDTO);

    /**
     *  删除一条数据
     * @return
     */
    public Integer delete(Integer dmid);
}
