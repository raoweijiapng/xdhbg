package cn.xdh.web;

import cn.xdh.entity.AnswerList;
import cn.xdh.entity.PaperAndTest;
import cn.xdh.entity.Student;
import cn.xdh.entity.XdhClass;
import cn.xdh.service.impl.ClassServiceImpl;
import cn.xdh.service.impl.StudentResultServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class StudentResultController {

    @Autowired
    private StudentResultServiceImpl studentResultServiceimpl;
    @Autowired
    private ClassServiceImpl classServiceimpl;


    @GetMapping(value = "/studentTest.studentResult")
    public ModelAndView selectStudentResult(@Param("id") Integer id, Model model, PaperAndTest paperAndTest) {
        //通过学生id查询学生名字
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(id);
        //查询试卷表与参加试卷表
        List<PaperAndTest> paperAndTest1 = studentResultServiceimpl.selectPaperAndTest(paperAndTest);
        model.addAttribute("paperAndTest1", paperAndTest1);
        model.addAttribute("selectStudentNameById", selectStudentNameById);
        return new ModelAndView("studentTest/studentResult");
    }

    @GetMapping(value = "/studentTest.studentResultList")
    public ModelAndView selectStudentResultToList(Model model, PaperAndTest paperAndTest,AnswerList answerList) {
        //根据试卷id与学生id查询数据
        List<PaperAndTest> selectPaperToList = studentResultServiceimpl.selectPaperToList(paperAndTest);
        answerList.setTest_id(selectPaperToList.get(0).getTest_id());
        answerList.setStudent_id(selectPaperToList.get(0).getStudent_id());
        List<AnswerList> selectAnswerListById = studentResultServiceimpl.selectAnswerListById(answerList);
        //通过参加考试的学生id来查询出学生姓名
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(selectPaperToList.get(0).getStudent_id());
        //通过考试所在班级id查询出班级名
        XdhClass selectClassById = classServiceimpl.selectClassById(selectPaperToList.get(0).getClass_id());
        System.out.println(selectAnswerListById);
        model.addAttribute("selectAnswerListById",selectAnswerListById);
        model.addAttribute("selectPaperToList", selectPaperToList);
        model.addAttribute("selectClassById", selectClassById);
        model.addAttribute("selectStudentNameById", selectStudentNameById);
        return new ModelAndView("studentTest/studentResultList");
    }


    @GetMapping(value = "/studentTest.studentResultSearch")
    public ModelAndView selectPaperToLookname(@Param("id") Integer id, @Param("lookname") String lookname, Model model, PaperAndTest paperAndTest) {
        //通过学生id查询学生名字
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(id);
        //如果没输入关键字就搜索全部,有关键字则用关键字模糊查询
        String lookname1 = lookname.trim();
        if (lookname1 == "") {
            List<PaperAndTest> paperAndTest1 = studentResultServiceimpl.selectPaperAndTest(paperAndTest);
            model.addAttribute("paperAndTest1", paperAndTest1);
        } else {
            List<PaperAndTest> selectPaperToLookname = studentResultServiceimpl.selectPaperToLookname(id, lookname);
            model.addAttribute("paperAndTest1", selectPaperToLookname);
        }
        model.addAttribute("selectStudentNameById", selectStudentNameById);
        model.addAttribute("studentId", id);
        return new ModelAndView("studentTest/studentResult");

    }
}
