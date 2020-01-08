package cn.xdh.service.impl;

import cn.xdh.dao.StudentResultDao;
import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;
import cn.xdh.service.StudentResultService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentResultServiceImpl implements StudentResultService {
    
    @Autowired
    private StudentResultDao studentResultDao;
    
    @Override
    public Student selectStudentNameById(int id) {
        Student selectStudentNameById = studentResultDao.selectStudentNameById(id);
        return selectStudentNameById;
    }

    @Override
    public List<PaperAndTest> selectPaperAndTest(PaperAndTest paperAndTest) {
        List<PaperAndTest> selectPaperAndTest = studentResultDao.selectPaperAndTest(paperAndTest);
        return selectPaperAndTest;
    }

    @Override
    public List<PaperAndTest> selectPaperToList(PaperAndTest paperAndTest) {
        List<PaperAndTest> selectPaperToList = studentResultDao.selectPaperToList(paperAndTest);
        return selectPaperToList;
    }

    @Override
    public List<PaperAndTest> selectPaperToLookname(@Param("id") Integer id, @Param("lookname") String lookname) {
        List<PaperAndTest> selectPaperToLookname = studentResultDao.selectPaperToLookname(id,lookname);
        return selectPaperToLookname;
    }
}
