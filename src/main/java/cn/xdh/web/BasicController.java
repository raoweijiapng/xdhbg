package cn.xdh.web;

import cn.xdh.SomeMethods;
import cn.xdh.entity.*;
import cn.xdh.service.impl.*;
import cn.xdh.util.ImageVerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
public class BasicController {
    @Autowired
    private StudentServiceImpl studentservice;
    @Autowired
    private TeacherServiceImpl teacherservice;
    @Autowired
    private AdminServiceImpl adminservice;
    @Autowired
    private ClassServiceImpl classservice;
    @Autowired
    private StudentNumberServiceImpl studentNumberServiceImpl;

    @GetMapping("/getVerifiCode")
    public void getVerifiCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         1.生成验证码
         2.把验证码上的文本存在session中
         3.把验证码图片发送给客户端
         */
        //用我们的验证码类，生成验证码类对象
        ImageVerifyCode ivc = new ImageVerifyCode();
        //获取验证码
        BufferedImage image = ivc.getImage();
        //将验证码的文本存在session中
        HttpSession session =  request.getSession();
        //把用户名存入session
        session.setAttribute("text",ivc.getText());
        ivc.output(image, response.getOutputStream());
    }


    //登录页面
    @GetMapping(value = "/")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    //登录后,分析身份,跳转不同的页面,并把名字存入cookie和session
    @PostMapping(value = "/index")
    public ModelAndView loginPost(String mobile, String password,String verifycode, HttpServletResponse response, HttpServletRequest request) throws Exception {
        ModelAndView mav = new ModelAndView();
        //密码md5加密
        password = SomeMethods.md5(password);
        //从cookie中获取真正的验证码
        request.setCharacterEncoding("utf-8");
        String cookie_vcode = null;
        HttpSession session  = request.getSession();
        cookie_vcode = (String) session.getAttribute("text");
        //判断是否为管理员,是否为教师,是否为教师
        Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
        Teacher teacher = teacherservice.selectByPhoneAndPassword(mobile,password);
        Student student = studentservice.selectByPhoneAndPassword(mobile,password);
        if (verifycode.equalsIgnoreCase(cookie_vcode)) {
            if (admin != null) {
                //调用SomeMethods的设置cookie和session的方法
                SomeMethods.setCookieAndSession("admin",admin.getId(),admin.getUsername(), password, mobile, response, request);
                //添加管理员登录的日志
                String content = "管理员登录";
                AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
                adminservice.addAdminLog(adminlog);
                //通过sql获取班级,教师,学生数量
                int studentNumber = studentservice.selectAllNumber();
                int classNumber = classservice.selectAllNumber();
                int teacherNumber = teacherservice.selectAllNumber();
                //通过sql获取最新五个管理员日志
                List<AdminLog> adminloglist = adminservice.selectNewAdminLog();
                //设置mav的视图
                mav.setViewName("admin/index");
                //设置键值对
                mav.getModel().put("studentNumber", studentNumber);
                mav.getModel().put("classNumber", classNumber);
                mav.getModel().put("teacherNumber", teacherNumber);
                mav.getModel().put("username", admin.getUsername());
                mav.addObject("adminLog", adminloglist);
                return mav;
            } else if (teacher != null) {
                //调用SomeMethods的设置cookie和session的方法
                SomeMethods.setCookieAndSession("teacher",teacher.getId(),teacher.getName(), password, mobile, response, request);
                //添加教师登录的日志
                String content = "教师登录";
                TeacherLog teacherLog = new TeacherLog(teacher.getId(),teacher.getName(),content,SomeMethods.getCurrentTime(), SomeMethods.getIp4());
                teacherservice.addTeacherLog(teacherLog);
                mav.setViewName("teacher/index");
                mav.getModel().put("totalNumber", studentNumberServiceImpl.selectTotalNumber());
                mav.getModel().put("graduNumber", studentNumberServiceImpl.selectGraduNumber());
                mav.getModel().put("notGraduNumber", studentNumberServiceImpl.selectNotGraduNumber());
                mav.getModel().put("stageOne", studentNumberServiceImpl.selectStageOne());
                mav.getModel().put("stageTwo", studentNumberServiceImpl.selectStageTwo());
                mav.getModel().put("stageThree", studentNumberServiceImpl.selectStageThree());
                mav.getModel().put("stageFour", studentNumberServiceImpl.selectStageFour());
                mav.getModel().put("stageFive", studentNumberServiceImpl.selectStageFive());
                mav.getModel().put("username", teacher.getName());
                return mav;
            } else if (student != null) {
                //调用SomeMethods的设置cookie和session的方法
                SomeMethods.setCookieAndSession("student",student.getId(),student.getUsername(), password, mobile, response, request);
                List<Notice> notices = studentservice.getNotices();
                mav.addObject("msgs",notices);
                mav.getModel().put("username", student.getUsername());
                mav.setViewName("student/index");
                return mav;
            }
            //如果都不是,则弹出窗口说明账号密码错误
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('账号密码错误!')");
            out.println("</script>");
            mav.setViewName("login");
            return mav;
        }
        //如果验证码不正确
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('验证码错误!')");
        out.println("</script>");
        mav.setViewName("login");
        return mav;
    }

    //退出登录,去除cookie和session
    @GetMapping(value = "/loginout")
    public ModelAndView loginOut(HttpServletResponse response, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        //获得cookie里的mobile和password
        Cookie[] cookies = request.getCookies();
        String mobile = null;
        String password = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("mobile")) {
                mobile = cookie.getValue();
            } else if (cookie.getName().equals("password")) {
                password = cookie.getValue();
            }
        }
        //根据mobile和password获得信息
        Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
        Teacher teacher = teacherservice.selectByPhoneAndPassword(mobile,password);
        Student student = studentservice.selectByPhoneAndPassword(mobile,password);
        //清楚cookie和session
        SomeMethods.deleteCookieAndSession(response,request);
        if (admin != null) {
            //添加管理员退出的日志
            String content = "管理员退出";
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else if (teacher != null){
            //添加教师退出的日志
            String content = "教师退出";
        }else if (student != null){
            //添加学员退出的日志
            String content = "学员退出";
        }
        return mav;
    }


}
