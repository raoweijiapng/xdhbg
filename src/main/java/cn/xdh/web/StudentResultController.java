package cn.xdh.web;

import cn.xdh.entity.Student;
import cn.xdh.entity.Teacher;
import cn.xdh.service.impl.StudentResultServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentResultController {
    
    @Autowired
    private StudentResultServiceImpl studentResultServiceimpl;
    
    
    
    
    @GetMapping(value="/studentTest.studentResult")
    public ModelAndView selectStudentResult(@Param("id") Integer id, Model model) {
        Student selectStudentNameById = studentResultServiceimpl.selectStudentNameById(id);
        model.addAttribute("studentId",id);
        model.addAttribute("selectStudentNameById",selectStudentNameById);
        return new ModelAndView("studentTest/studentResult");
    }
}
