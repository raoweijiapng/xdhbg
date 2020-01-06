package cn.xdh.service;

import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;

public interface StudentResultService {

    Student selectStudentNameById(int id);

    PaperAndTest selectPaperAndTest(PaperAndTest paperAndTest);
}
