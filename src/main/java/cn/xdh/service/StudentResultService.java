package cn.xdh.service;

import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;

import java.util.List;

public interface StudentResultService {

    Student selectStudentNameById(int id);

    List<PaperAndTest> selectPaperAndTest(PaperAndTest paperAndTest);
}
