package cn.xdh.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class StudentResultController {
    @GetMapping(value="/studentTest.studentResult")
    public ModelAndView selectStudentResult(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("studentTest/studentResult");
        return modelAndView;
    }
}
