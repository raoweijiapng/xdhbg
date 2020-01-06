package cn.xdh.service.impl;

import cn.xdh.dao.StudentResultDao;
import cn.xdh.entity.Student;
import cn.xdh.service.StudentResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentResultServiceImpl implements StudentResultService {
    
    @Autowired
    private StudentResultDao studentResultDao;
    
    @Override
    public Student selectStudentNameById(int id) {
        Student selectStudentNameById = studentResultDao.selectStudentNameById(id);
        return selectStudentNameById;
    }
}
