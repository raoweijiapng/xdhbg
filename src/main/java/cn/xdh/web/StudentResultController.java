package cn.xdh.web;

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
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(id);
        List<PaperAndTest> paperAndTest1 = studentResultServiceimpl.selectPaperAndTest(paperAndTest);
        model.addAttribute("paperAndTest1", paperAndTest1);
        model.addAttribute("selectStudentNameById", selectStudentNameById);
        return new ModelAndView("studentTest/studentResult");
    }

    @GetMapping(value = "/studentTest.studentResultList")
    public ModelAndView selectStudentResultToList(@Param("id") Integer id, Model model, PaperAndTest paperAndTest) {
        List<PaperAndTest> selectPaperToList = studentResultServiceimpl.selectPaperToList(paperAndTest);
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(selectPaperToList.get(0).getStudent_id());
        XdhClass selectClassById = classServiceimpl.selectClassById(selectPaperToList.get(0).getClass_id());
        model.addAttribute("selectPaperToList", selectPaperToList);
        model.addAttribute("selectClassById", selectClassById);
        model.addAttribute("selectStudentNameById", selectStudentNameById);
        return new ModelAndView("studentTest/studentResultList");
    }


    @GetMapping(value = "/studentTest.studentResultSearch")
    public ModelAndView selectPaperToLookname(@Param("id") Integer id, @Param("lookname") String lookname, Model model, PaperAndTest paperAndTest) {
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(id);
        if (lookname == "") {
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
