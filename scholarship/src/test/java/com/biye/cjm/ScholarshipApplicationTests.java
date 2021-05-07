package com.biye.cjm;

import com.biye.cjm.mapper.ScholarshipApplyMapper;
import com.biye.cjm.mapper.ScholarshipDefineMapper;
import com.biye.cjm.mapper.ScholarshipPublishMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

@SpringBootTest
class ScholarshipApplicationTests {

    @Autowired
    private ScholarshipDefineMapper scholarshipDefineMapper;

    @Autowired
    private ScholarshipPublishMapper scholarshipPublishMapper;

    @Autowired
    private ScholarshipApplyMapper scholarshipApplyMapper;

    //查询是否已经申请过同一类型的奖学金
    @Test
    void hasSameApply() {
        boolean flag=scholarshipApplyMapper.hasSameApply(2,"171548203");
        System.out.println(flag);
    }

    //查询是否已经申请过同一类型的奖学金
    @Test
    void hasSameType() {
        boolean flag=scholarshipDefineMapper.hasSameType("国家奖学金");
        System.out.println(flag);
    }

    //查询是否存在相同的奖学金名称
    @Test
    void hasSameName() {
        boolean flag=scholarshipPublishMapper.hasSameName("国奖学金");
        System.out.println(flag);
    }

    @Test
    void file() {
        File file=new File("http://localhost:8001/upload/test.txt");
        System.out.println(file.delete());
    }

    //根据主键查询是否分分级
    @Test
    void querySffdj() {
        boolean flag=scholarshipDefineMapper.querySffdj(10);
        System.out.println(flag);
    }

}
