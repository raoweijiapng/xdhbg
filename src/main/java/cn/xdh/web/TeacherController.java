package cn.xdh.web;

import cn.xdh.SomeMethods;
import cn.xdh.entity.*;
import cn.xdh.service.TeacherService;
import cn.xdh.service.impl.*;
import cn.xdh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.soap.SOAPMessage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

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
    @Autowired
    private KnowledgeServiceImpl knowledgeServiceImpl;
    @Autowired
    private TeacherServiceImpl teacherServiceimpl;
    @Autowired
    private QuestionsServiceImpl questionsServiceimpl;


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

    //知识点列表
    @GetMapping("/teacher.knowledge")
    public ModelAndView goKnowledgeList(HttpServletRequest request,
                                        @RequestParam(name="type",required=false,defaultValue="all")String type,
                                        @RequestParam(name="page",required=false,defaultValue="1")int page,
                                        @RequestParam(name="size",required=false,defaultValue="10")int size){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/knowledge");
        String lookname = request.getParameter("lookname");
        if (lookname == null) {
        }else {
            lookname = lookname.replaceAll(" ", "");
        }
        Page<Knowledge> knowledges = null;
        if (type.equals("likename")){
            knowledges = knowledgeServiceImpl.getAllKnowledgeBy(page - 1,size,lookname);
        }else {
            knowledges = knowledgeServiceImpl.selectAllKnowledge(page - 1, size);
        }
        mav.getModel().put("current", knowledges.getNumber()+1);
        mav.getModel().put("total", knowledges.getTotalPages());
        mav.addObject("knowledges",knowledges.getContent());
        mav.getModel().put("type", type);
        mav.getModel().put("lookname", lookname);
        return mav;
    }

    //跳转到增加知识点的页面
    @GetMapping("/teacher.addknowledgeview")
    public ModelAndView goKnowledgeList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/AddKnowledge");
        return mav;
    }

    //接受添加知识点页面发来的参数
    @PostMapping("/teacheraddknowledge")
    @ResponseBody
    public Map<String,Object> goAddKnowledge(Knowledge knowledge,HttpServletRequest request){
        knowledge.setAdd_time(SomeMethods.getCurrentTime());
        //Knowledge knowledge = new Knowledge(subject_id,stage_id,title, SomeMethods.getCurrentTime());
        List<Knowledge> knowledge1 = knowledgeServiceImpl.selectknowledgeByTitle(knowledge.getTitle());
        Map<String,Object> map = new HashMap<>();
        if (knowledge1.isEmpty()) {
            //3是添加成功
            knowledgeServiceImpl.addKnowledge(knowledge);
            //获取cookie中的老师信息
            String action = "添加知识点";
            Cookie[] cookies = request.getCookies();
            String mobile = null;
            String password = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("mobile")) {
                        mobile = cookie.getValue();
                    }
                    if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                    }

                }
                Teacher teacher = teacherServiceimpl.selectByPhoneAndPassword(mobile, password);
                TeacherLog teacherLog = new TeacherLog(teacher.getId(), teacher.getName(), action, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
                //将日志实体类添加到日志表中
                teacherServiceimpl.addTeacherLog(teacherLog);
                map.put("msg","success");
                return map;
            }else {
                map.put("msg","cookiefailed");
                return map;
            }
        }else {
            map.put("msg","failed");
            return map;
        }
    }

    //批量添加知识点
    @PostMapping("/teacher/addknowledge/{suffixName}")
    @ResponseBody
    public Msg addAllKnowledge(@RequestParam("ExcelFile") MultipartFile excelFile, HttpServletRequest request, @PathVariable String suffixName) throws Exception {
        //获取cookie中的老师信息
        String action = "批量添加知识点";
        Cookie[] cookies = request.getCookies();
        String mobile = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Teacher teacher = teacherServiceimpl.selectByPhoneAndPassword(mobile, password);
            TeacherLog teacherLog = new TeacherLog(teacher.getId(), teacher.getName(), action, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            //将日志实体类添加到日志表中
            teacherServiceimpl.addTeacherLog(teacherLog);
            return knowledgeServiceImpl.batchAddKnowledge(request, suffixName, excelFile);
        }else{
            Msg msg = new Msg();
            msg.setMsg("cookie为空");
            return msg;
        }

    }

    //跳转到修改知识点的页面
    @GetMapping("/teacher.updateknowledgeview/{id}")
    public ModelAndView goupdateKnowledgeview(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/updateKnowledge");
        mav.getModel().put("knowledgeid",id);
        return mav;
    }

    //提交修改知识点的数据并返回状态
    @PostMapping("/teacherupdateknowledge")
    @ResponseBody
    public Map<String,Object> goupdateKnowledge(Knowledge knowledge,HttpServletRequest request){
        Map<String,Object> map = new HashMap<>();
        String action = "修改知识点";
        Cookie[] cookies = request.getCookies();
        String mobile = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Teacher teacher = teacherServiceimpl.selectByPhoneAndPassword(mobile, password);
            TeacherLog teacherLog = new TeacherLog(teacher.getId(), teacher.getName(), action, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            //将日志实体类添加到日志表中
            teacherServiceimpl.addTeacherLog(teacherLog);
            int result = knowledgeServiceImpl.updateKnowledge(knowledge.getSubject_id(),knowledge.getStage_id(),knowledge.getTitle(),SomeMethods.getCurrentTime(),knowledge.getId());
            if (result == 1){
                map.put("msg","success");
                return map;
            }else{
                map.put("msg","failed");
                return map;
            }
        }else {
            map.put("msg","cookiefailed");
            return map;
        }

    }

    //跳转到修改知识点的页面
    @GetMapping("/teacher.deleteknowledgeview/{id}")
    public ModelAndView godeleteKnowledgeview(@PathVariable Integer id){
        knowledgeServiceImpl.deleteKnowledge(id);
        return new ModelAndView("redirect:/teacher.knowledge");
    }

    //跳转到试题列表的页面
    @GetMapping("/teacher.question")
    public ModelAndView goquestionview(@RequestParam(name="type",required=false,defaultValue="all")String type,
                                       @RequestParam(name="page",required=false,defaultValue="1")int page,
                                       @RequestParam(name="size",required=false,defaultValue="2")int size){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/question");
        Page<Question> questions = null;
        questions = questionsServiceimpl.getAllQuestion(page - 1, size);
        mav.getModel().put("current", questions.getNumber()+1);
        mav.getModel().put("total", questions.getTotalPages());
        mav.addObject("questions",questions.getContent());
        mav.getModel().put("type", type);
        //mav.getModel().put("lookname", lookname);
        return mav;
    }


    //跳转到增加试题的页面
    @GetMapping("/teacher.addquestionview")
    public ModelAndView goquestionList(){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("teacher/AddQuestion");
        return mav;
    }


    //添加试题的选择知识点
    @PostMapping("/teacherselectknowledge")
    @ResponseBody
    public List<Knowledge> goselectKnowledge(int subject_id,int stage_id){
        List<Knowledge> knowledgelist = questionsServiceimpl.findBySubject_idAndStage_id(subject_id,stage_id);
        //System.out.println(knowledgelist);
        return knowledgelist;
    }


    //添加试题
    @PostMapping("/teacheraddquestion")
    @ResponseBody
    public Map<String,Object> goaddquestion(Question question,HttpServletRequest request){
        question.setAdd_time(SomeMethods.getCurrentTime());
        List<Question> questionList = questionsServiceimpl.selectQuestionByTitle(question.getTitle());
        //System.out.println(knowledgelist);
        Map<String,Object> map = new HashMap<>();
        if (questionList.isEmpty()) {
            //3是添加成功
            questionsServiceimpl.addQuestion(question);
            //获取cookie中的老师信息
            String action = "添加试题";
            Cookie[] cookies = request.getCookies();
            String mobile = null;
            String password = null;
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("mobile")) {
                        mobile = cookie.getValue();
                    }
                    if (cookie.getName().equals("password")) {
                        password = cookie.getValue();
                    }

                }
                Teacher teacher = teacherServiceimpl.selectByPhoneAndPassword(mobile, password);
                TeacherLog teacherLog = new TeacherLog(teacher.getId(), teacher.getName(), action, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
                //将日志实体类添加到日志表中
                teacherServiceimpl.addTeacherLog(teacherLog);
                map.put("msg","success");
                return map;
            }else {
                map.put("msg","cookiefailed");
                return map;
            }
        }else {
            map.put("msg","failed");
            return map;
        }

    }


    //批量添加试题
    @PostMapping("/teacher/addquestion/{suffixName}")
    @ResponseBody
    public Msg addAllQuestion(@RequestParam("ExcelFile") MultipartFile excelFile, HttpServletRequest request, @PathVariable String suffixName) throws Exception {
        //获取cookie中的老师信息
        String action = "批量添加试题";
        Cookie[] cookies = request.getCookies();
        String mobile = null;
        String password = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("mobile")) {
                    mobile = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
            Teacher teacher = teacherServiceimpl.selectByPhoneAndPassword(mobile, password);
            TeacherLog teacherLog = new TeacherLog(teacher.getId(), teacher.getName(), action, SomeMethods.getCurrentTime(), SomeMethods.getIp4());
            //将日志实体类添加到日志表中
            teacherServiceimpl.addTeacherLog(teacherLog);
            return questionsServiceimpl.batchAddQuestion(request, suffixName, excelFile);
        }else{
            Msg msg = new Msg();
            msg.setMsg("cookie为空");
            return msg;
        }

    }


}
