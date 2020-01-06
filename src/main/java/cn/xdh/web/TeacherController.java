package cn.xdh.web;

import cn.xdh.entity.NoticeData;
import cn.xdh.entity.TeacherLog;
import cn.xdh.service.TeacherService;
import cn.xdh.service.impl.DelNoticeServiceImpl;
import cn.xdh.service.impl.NoticeContentServerImpl;
import cn.xdh.service.impl.NoticeDataServiceImpl;
import cn.xdh.service.impl.StudentNumberServiceImpl;
import cn.xdh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

/**
 * @author victor
 * @site https://ttk1907.github.io/
 * @company XDL
 * @project xdhbg
 * @package cn.xdh.web
 * @created 2019-12-27 17:23
 * @function ""
 */

@Controller
public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @Autowired
    private NoticeContentServerImpl noticeContentServerImpl;
    @Autowired
    private DelNoticeServiceImpl delNoticeServiceImpl;
    @Autowired
    private NoticeDataServiceImpl noticeDataServiceImpl;
    @Autowired
    private StudentNumberServiceImpl studentNumberServiceImpl;

    @GetMapping(value = "/teacher/Log/{page}")
    public ModelAndView getTeacherLog(Model model, @PathVariable int page, HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        //获得日志中需要的数据
        String teacherId = "id";
        int teacher_id = 0;
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(teacherId)) {
                    //将字符串形式的id转换成int类型
                    teacher_id = Integer.parseInt(cookie.getValue());
                }
            }
            //查找所有的教师日志
            List<TeacherLog> teacherLogList = teacherService.selectTeacherLog(teacher_id);
            //数据量
            int total = teacherLogList.size();
            //防止数据库中没有值
            if (total == 0) {
                TeacherLog teacherLog = new TeacherLog();
                teacherLog.setTeacher_name("暂无日志");
                teacherLog.setAdd_time(0);
                teacherLogList.add(teacherLog);
                total = teacherLogList.size();
            }
            //总页数
            int totalPage = PageUtil.getTotalPage(total, PageUtil.count);
            //校对页数正确与否
            page = PageUtil.numberOfPage(page, totalPage);
            //页数集合
            List<Integer> totalPageList = PageUtil.pageUtil(page, totalPage);
            //分好页的未毕业学生集合
            List<TeacherLog> teacherLogs = PageUtil.teacherLogList(page, totalPage, total, teacherLogList);

            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalPageList", totalPageList);
            model.addAttribute("teacherLogs", teacherLogs);
            model.addAttribute("likeName",0);
            model.addAttribute("p",page);
            return new ModelAndView("teacher/TeacherLogList");
        }else {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');");
            out.println("</script>");
            return new ModelAndView("redirect:/");
        }
    }

    //教师日志的模糊查询/teacherLog/like/1/
    @GetMapping("/teacherLog/like/{page}/{action}")
    public String undergraduateStudentList(Model model, @PathVariable String action,@PathVariable int page,HttpServletRequest request,HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        //获得日志中需要的数据
        String teacherId = "id";
        int teacher_id = 0;
        if (cookies != null){
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(teacherId)) {
                    //将字符串形式的id转换成int类型
                    teacher_id = Integer.parseInt(cookie.getValue());
                }
            }
            //模糊查找所有的教师日志
            //System.out.println(teacher_id);
            List<TeacherLog> teacherLogList = teacherService.selectTeacherLogLikeAction(teacher_id,action);
            //数据量
            int total = teacherLogList.size();
            //防止数据库中没有值
            if (total == 0) {
                TeacherLog teacherLog = new TeacherLog();
                teacherLog.setTeacher_name("暂无日志");
                teacherLog.setAdd_time(0);
                teacherLogList.add(teacherLog);
                total = teacherLogList.size();
            }
            //总页数
            int totalPage = PageUtil.getTotalPage(total, PageUtil.count);
            //校对页数正确与否
            page = PageUtil.numberOfPage(page, totalPage);
            //页数集合
            List<Integer> totalPageList = PageUtil.pageUtil(page, totalPage);
            //分好页的未毕业学生集合
            List<TeacherLog> teacherLogs = PageUtil.teacherLogList(page, totalPage, total, teacherLogList);

            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalPageList", totalPageList);
            model.addAttribute("teacherLogs", teacherLogs);
            model.addAttribute("likeName",1);
            model.addAttribute("action",action);
            model.addAttribute("p",page);
            return "teacher/TeacherLogList";
        }else {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');");
            out.println("</script>");
            return "redirect:/";
        }
    }

    //    公告列表初始化
    @GetMapping(value = "aNoticeManage")
    @ResponseBody
    public List<NoticeData> aNoticeManage() {
        List<NoticeData> nd = noticeDataServiceImpl.selectNoticeData();
        return nd;
    }

    //公告管理页面
    @GetMapping(value = "noticeManage")
    public ModelAndView noticeManage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/noticeManage");
        return mav;
    }

    //模糊查询公告列表显示
    @GetMapping(value = "selectNotice/{cont}")
    @ResponseBody
    public List<NoticeData> selectNotice(@PathVariable String cont) {
        List<NoticeData> nd = noticeDataServiceImpl.searchNoticeData(cont);
        return nd;
    }

    //    增加公告
    @PostMapping(value = "noticeManage")
    public ModelAndView submitNotice(String noticeContent,HttpServletRequest request) {
        noticeContentServerImpl.addNoticeContent(noticeContent,new Date().getTime()/1000,request);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/noticeManage");
        return mav;
    }

    //    删除公告
    @GetMapping(value = "delManage/{id}")
    @ResponseBody
    public String delManage(@PathVariable Integer id,HttpServletRequest request) {
        //System.out.println(id);
//        ModelAndView mav = new ModelAndView();
//        mav.setViewName("teacher/noticeManage");
        delNoticeServiceImpl.delNoticeService(id,request);
        if (id != null){
            return "true";
        }else
            return "false";
//        return mav;
    }

    //教师端的首页
    @GetMapping("/teacher.index")
    public ModelAndView goIndex(){
        ModelAndView mav = new ModelAndView();
        mav.getModel().put("totalNumber", studentNumberServiceImpl.selectTotalNumber());
        mav.getModel().put("graduNumber", studentNumberServiceImpl.selectGraduNumber());
        mav.getModel().put("notGraduNumber", studentNumberServiceImpl.selectNotGraduNumber());
        mav.getModel().put("stageOne", studentNumberServiceImpl.selectStageOne());
        mav.getModel().put("stageTwo", studentNumberServiceImpl.selectStageTwo());
        mav.getModel().put("stageThree", studentNumberServiceImpl.selectStageThree());
        mav.getModel().put("stageFour", studentNumberServiceImpl.selectStageFour());
        mav.getModel().put("stageFive", studentNumberServiceImpl.selectStageFive());
        mav.setViewName("teacher/index");
        return mav;
    }

}
