package cn.xdh.service;

import cn.xdh.entity.AnswerList;
import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentResultService {

    Student selectStudentNameById(int id);

    List<PaperAndTest> selectPaperAndTest(PaperAndTest paperAndTest);

    List<PaperAndTest> selectPaperToList(PaperAndTest paperAndTest);

    List <AnswerList> selectAnswerListById(AnswerList answerList);

    List<PaperAndTest> selectPaperToLookname(@Param("id") Integer id, @Param("lookname") String lookname);
}
