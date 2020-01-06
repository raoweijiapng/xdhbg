package cn.xdh.web;

import cn.xdh.SomeMethods;
import cn.xdh.entity.*;
import cn.xdh.service.impl.AdminServiceImpl;
import cn.xdh.service.impl.ClassServiceImpl;
import cn.xdh.service.impl.StudentServiceImpl;
import cn.xdh.service.impl.TeacherServiceImpl;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminController {
    @Autowired
    private StudentServiceImpl studentservice;
    @Autowired
    private TeacherServiceImpl teacherservice;
    @Autowired
    private AdminServiceImpl adminservice;
    @Autowired
    private ClassServiceImpl classservice;

    //查询日志列表带分页
    @GetMapping(value = "/admin.adminlog")
    public ModelAndView getAdminlog(
            HttpServletRequest request,
            @RequestParam(name="page",required=false,defaultValue="1")int page,
            @RequestParam(name="size",required=false,defaultValue="10")int size,
            @RequestParam(name="type",required=false,defaultValue="all")String type) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/adminlog");
        String lookname = request.getParameter("lookname");
        if (lookname == null) {
        }else {
            lookname = lookname.replaceAll(" ", "");
        }
        Page<AdminLog> adminloglist = null;
        //判断查找的方式，然后通过sql获取所有管理员日志
        if (type.equals("likename")){
            //根据名字模糊查找所有日志
            adminloglist = adminservice.selectAllAdminLogByName(lookname,page - 1, size);
        }else if (type.equals("likeip")){
            //根据ip模糊查找日志
            adminloglist = adminservice.selectAllAdminLogByIp(lookname,page - 1, size);
        }else if (type.equals("likeall")) {
            //根据所有字段模糊查询日志
            adminloglist = adminservice.selectAllAdminLogByAll(lookname,page - 1,size);
        }else {
            //查找所有的管理员日志
            adminloglist = adminservice.selectAllAdminLog(page - 1, size);
        }
        mav.getModel().put("lookname", lookname);
        mav.getModel().put("current", adminloglist.getNumber()+1);
        mav.getModel().put("total", adminloglist.getTotalPages());
        mav.getModel().put("type", type);
        mav.addObject("adminLog",adminloglist.getContent());
        return mav;
    }

    //查询日志列表带分页
    @GetMapping(value = "/admin.teacherlog")
    public ModelAndView getTeacherlog(
            HttpServletRequest request,
            @RequestParam(name="page",required=false,defaultValue="1")int page,
            @RequestParam(name="size",required=false,defaultValue="10")int size,
            @RequestParam(name="type",required=false,defaultValue="all")String type) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/teacherlog");
        String lookname = request.getParameter("lookname");
        if (lookname == null) {
        }else {
            lookname = lookname.replaceAll(" ", "");
        }
        Page<TeacherLog> teacherlogs = null;
        //判断查找的方式，然后通过sql获取所有管理员日志
        if (type.equals("likename")){
            //根据名字模糊查找所有日志
            teacherlogs = adminservice.selectAllTeacherLogByName(lookname,page - 1, size);
        }else if (type.equals("likeip")){
            //根据ip模糊查找日志
            teacherlogs = adminservice.selectAllTeacherLogByIp(lookname,page - 1, size);
        }else if (type.equals("likeall")) {
            //根据所有字段模糊查询日志
            teacherlogs = adminservice.selectAllTeacherLogByAll(lookname,page - 1,size);
        }else {
            //查找所有的管理员日志
            teacherlogs = adminservice.selectAllTeacherLog(page - 1, size);
        }
        mav.getModel().put("lookname", lookname);
        mav.getModel().put("current", teacherlogs.getNumber()+1);
        mav.getModel().put("total", teacherlogs.getTotalPages());
        mav.getModel().put("type", type);
        mav.addObject("adminLog",teacherlogs.getContent());
        return mav;
    }



    //页面内点击首页
    @GetMapping(value = "/admin.index")
    public ModelAndView loginGet() {
        ModelAndView mav = new ModelAndView();
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
        mav.addObject("adminLog",adminloglist);
        return mav;
    }

    //将查询到老师的数据通过list集合发送到指定页面并分页
    @GetMapping(value = "/admin.teacher")
    public ModelAndView selectTeacher(Model model, HttpServletRequest request,
                                @RequestParam(name="page",required=false,defaultValue="1")int page,
                                @RequestParam(name="type",required=false,defaultValue="all")String type,
                                @RequestParam(name="size",required=false,defaultValue="5")int size){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/teacher");
        String lookname = request.getParameter("lookname");
        if (lookname == null) {
        }else {
            lookname = lookname.replaceAll(" ", "");
        }
        Page<Teacher> selectByTeacher = null;
        //判断查找的方式，然后通过sql获取所有管理员日志
        if (type.equals("likename")){
            selectByTeacher = teacherservice.getAllTeacherBy(page - 1,size,lookname);
        }else {
            selectByTeacher = teacherservice.selectAllTeacher(page - 1, size);
        }
        model.addAttribute("current", selectByTeacher.getNumber() + 1);
        model.addAttribute("total", selectByTeacher.getTotalPages());
        model.addAttribute("selectTeacher", selectByTeacher.getContent());
        mav.getModel().put("type", type);
        mav.getModel().put("lookname", lookname);
        return mav;
    }

    //通过传来的id进行删除老师操作
    @GetMapping(value = "/admindeleteteacher/{id}")
    @ResponseBody
    public ModelAndView deleteByTeacher(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
        teacherservice.deleteByTeacher(id);
        String content = "管理员删除老师";
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
            //重定向到teacher页面
            return new ModelAndView("redirect:/admin.teacher");
        }else{
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

    //老师增加操作的页面
    @GetMapping("/admin.toInsertTeacher")
    public ModelAndView toInsertTeacher(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/addteacher");
        return mav;
    }

    //老师增加操作提交的地址,并重定向到教师管理列表
    @PostMapping(value = "/admin.addteacher")
    public Map<String, Object> insertTeacher(Teacher teacher, HttpServletRequest request, HttpServletResponse response) {
        String md5password = SomeMethods.md5(teacher.getPassword());
        teacher.setPassword(md5password);
        Teacher result = teacherservice.selectTeacherMobile(teacher.getMobile());
        Map<String,Object> map = new HashMap<String,Object>();
        if (result == null){
            teacherservice.insertByTeacher(teacher);
            map.put("msg","success");
        }else {
            map.put("msg","failed");
        }
        String content = "管理员增加老师";
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');window.location.href='/';");
            out.println("</script>");
        }
        return map;
    }

    //通过老师的id,与查询出来的老师信息,实现老师信息的显示和修改
    @GetMapping("/admintoUpdateTeacher")
    public ModelAndView toUpdateTeacher(@RequestParam(name="id",required=false)int id,Model model){
        Teacher selectByTeacher = teacherservice.selectByTeacher(id);
        model.addAttribute("teacherId",id);
        model.addAttribute("selectByTeacher",selectByTeacher);
        return new ModelAndView("admin/updateteacher");
    }

    @PostMapping("/updateteacher")
    public Teacher updateTeacher(@RequestParam(name="mobile",required=false)String mobile){
        Teacher selectByTeacher = teacherservice.selectTeacherMobile(mobile);
        return selectByTeacher;

    }

    //修改老师信息
    @PostMapping(value = "/adminupdateteacher/{id}")
    public Map<String,Object> updateTeacher(Teacher teacher,HttpServletRequest request,HttpServletResponse response) {
        String md5password = SomeMethods.md5(teacher.getPassword());
        teacher.setPassword(md5password);
        int a = teacherservice.updateByTeacher(teacher);
        Map<String,Object> map = new HashMap<String,Object>();
        if (a==1){
            map.put("msg","success");
        }else {
            map.put("msg","failed");
        }
        String content = "管理员修改老师信息";
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');window.location.href='/';");
            out.println("</script>");
        }
        return map;
    }

    //将查询出来的老师和班级有关的数据发到页面
    @GetMapping(value = "/admin.xdhclass" )
    public ModelAndView selectTeacherClass(Model model, HttpServletRequest request,
                                           @RequestParam(name="type",required=false,defaultValue="all")String type,
                                     @RequestParam(name="page",required=false,defaultValue="1")int page,
                                     @RequestParam(name="size",required=false,defaultValue="5")int size){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin/xdhclass");
        String lookname = request.getParameter("lookname");
        if (lookname == null) {
        }else {
            lookname = lookname.replaceAll(" ", "");
        }
        Page<TeacherClass> selectAllXdhClass = null;
        //判断查找的方式，然后通过sql获取所有管理员日志
        if (type.equals("likename")){
            selectAllXdhClass = classservice.getAllTeacherClassBy(page - 1,size,lookname);
        }else {
            selectAllXdhClass = classservice.selectAllXdhClass(page - 1, size);
        }
        model.addAttribute("current", selectAllXdhClass.getNumber()+1);
        model.addAttribute("total", selectAllXdhClass.getTotalPages());
        model.addAttribute("selectByXdhClass",selectAllXdhClass.getContent());
        mav.getModel().put("type", type);
        mav.getModel().put("lookname", lookname);
        return mav;
    }

    //添加班级的时候要选择查询已经存在的老师
    @GetMapping(value = "/admin.toInsertXdhClass" )
    public ModelAndView addClassForm(Model model){
        List<Teacher> selectByTeacher = teacherservice.selectAllTeacher();
        model.addAttribute("selectByTeacher",selectByTeacher);
        return new ModelAndView("admin/xdhclassform");
    }

    //判断数据库中是否有一样的号码
    @PostMapping(value = "/admincheckmobile")
    public Map<String,Object> checkmobile(HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        String mobile = request.getParameter("mobile");
        Admin admin = adminservice.selectByMobile(mobile);
        Teacher teacher = teacherservice.selectTeacherMobile(mobile);
        Student student = studentservice.selectByMobile(mobile);
        if (teacher == null && student == null && admin == null){
            map.put("msg","notexist");
            return map;
        }else {
            map.put("msg","exist");
            return map;
        }
    }

    @PostMapping(value = "/admincheckclassname")
    public XdhClass checkclassname(HttpServletRequest request){
        String class_name = request.getParameter("class_name");
        XdhClass xdhclass = classservice.selectByClassName(class_name);
        return xdhclass;
    }

    //添加班级信息
    @PostMapping(value = "/adminaddxdhclassform")
    public Map<String,Object> insertXdhClass(XdhClass xdhClass,HttpServletRequest request,HttpServletResponse response){
        Long addTime = SomeMethods.getCurrentTime();
        xdhClass.setAdd_time(addTime);
        int result = classservice.insertByXdhClass(xdhClass);
        System.out.println(result);
        String content = "管理员增加班级";
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');window.location.href='/';");
            out.println("</script>");
        }
        Map<String,Object> map = new HashMap<String,Object>();
        if (result==1){
            map.put("msg","success");
        }else {
            map.put("msg","failed");
        }
        return map;
    }

    //通过班级id来修改班级信息
    @GetMapping("/admintoUpdateXdhClass/{id}")
    public ModelAndView toUpdateXdhClass(@PathVariable int id,Model model){
        model.addAttribute("xdhclassId",id);
        List<Teacher> selectByTeacher = teacherservice.selectAllTeacher();
        XdhClass xdhClass = classservice.selectClassById(id);
        model.addAttribute("xdhClass",xdhClass);
        model.addAttribute("selectByTeacher",selectByTeacher);
        return new ModelAndView("admin/updatexdhclass");
    }

    //修改班级信息页面
    @PostMapping(value = "/adminupdatexdhclass/{id}")
    public Map<String,Object> updateXdhClass(XdhClass xdhClass,HttpServletRequest request,HttpServletResponse response){
        int a = classservice.updateByXdhClass(xdhClass);
        System.out.println(xdhClass.getIs_graduate());
        Map<String,Object> map = new HashMap<String,Object>();
        if (a==1){
            map.put("msg","success");
        }else {
            map.put("msg","failed");
        }
        String content = "管理员修改班级信息";
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');window.location.href='/';");
            out.println("</script>");
        }
        return map;
    }

    //通过id执行删除班级操作
    @DeleteMapping(value = "/admindeletexdhclass/{id}")
    public Map<String, Object> deleteByXdhClass(@PathVariable("id") int id,HttpServletRequest request,HttpServletResponse response) {
        List<Student> result =studentservice.selectStudentByClass(id);
        Map<String,Object> map = new HashMap<String,Object>();
        String content = "1";
        if (result.size()==0){
            classservice.deleteByXdhClass(id);
            map.put("msg","success");
            content = "管理员删除班级成功";
        }else {
            map.put("msg","failed");
            content = "管理员删除班级失败";
        }
        
        String mobile = null;
        String password = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                } else if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Admin admin = adminservice.selectByPhoneAndPassword(mobile,password);
            AdminLog adminlog = new AdminLog(admin.getId(), admin.getUsername(), content, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            adminservice.addAdminLog(adminlog);
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = null;
            try {
                out = response.getWriter();
            } catch (IOException e) {
                e.printStackTrace();
            }
            out.println("<script>");
            out.println("alert('请先登录,再进行操作!');window.location.href='/';");
            out.println("</script>");
        }
        return map;

    }


}
