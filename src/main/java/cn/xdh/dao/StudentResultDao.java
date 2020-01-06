package cn.xdh.dao;

import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentResultDao {
    
    Student selectStudentNameById(@Param("id") int id);
    
    PaperAndTest selectPaperAndTest(PaperAndTest paperAndTest);
}
