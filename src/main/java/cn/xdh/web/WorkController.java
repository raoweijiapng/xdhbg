package cn.xdh.web;

import cn.xdh.entity.Exeperience;
import cn.xdh.entity.Page;
import cn.xdh.entity.Student;
import cn.xdh.entity.Works;
import cn.xdh.service.impl.StudentServiceImpl;
import cn.xdh.service.impl.WorksServiceImpl;
import cn.xdh.util.GetCurrentPage;
import cn.xdh.util.MyPaging;
import cn.xdh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author TZB
 */
@Controller
public class WorkController {
    /**
     * 定义每个页面显示的数据条数
     */
    private final int pageSize=5;


    @Autowired
    private WorksServiceImpl worksServiceimpl;
    @Autowired
    private StudentServiceImpl studentServiceimpl;
    //获取 httpServletRequest对象
    @Autowired
    private HttpServletRequest httpServletRequest;

    /**
     * 显示所有学生作品
     * @param model
     * @return
     */
    @GetMapping("/teacher/works")
    //RequestParam
    //PathVariable
    public String worksAll(Model model,@RequestParam("pageNum") int pageNum){
        List<Works> worksList=worksServiceimpl.selectAll();
        //获取总页数
        int page = worksList.size()/pageSize;
        int generalPage = worksList.size()%pageSize==0&&page>0?page:page+1;
        //设置当前页
        GetCurrentPage.getcurrentPage(pageNum,generalPage,model);
        //总页数
        model.addAttribute("generalPage",generalPage);
        //学生姓名
        model.addAttribute("names",studentServiceimpl.selectAllNameAndId());
        //作品列表
        model.addAttribute("works", MyPaging.paging(worksList,pageNum,pageSize));
        //判断当前页是否有值
        model.addAttribute("price",1);
        if(generalPage<pageNum){
            model.addAttribute("price",0);
        }
        //获取当前页

        return "teacher/works";
    }


    /**
     * 根据学生姓名进行作品查询
     * @param username  学生姓名
     * @param model     返回作品集合
     * @return          跳到哪个网页
     */
    @GetMapping("/teacher/work")
    public String workByName(@RequestParam(value = "username")String username,Model model,int pageNum){

        //input数据回填
        model.addAttribute("backfill",username);
        List<Student> studentList = studentServiceimpl.selectIdAndNameByName(username);
        //如果根据条件没有查找到信息返回所有作品,并进行提示
        if(studentList.isEmpty()){

            model.addAttribute("pd",1);
            return "teacher/works";
        }
        List<Works> worksList=new ArrayList<Works>();
        for(Student student : studentList){
            //根据list集合中装的id拿取数据
            worksList.addAll(worksServiceimpl.selectById(student.getId()));
        }
        //根据作品名查询
        List<Works> worknameList= worksServiceimpl.selectByWorkName(username);
        worksList.addAll(worknameList);

        //去除重复值(需要重写equals)
        List<Works> newworksList=new ArrayList<Works>();
        Set set = new HashSet();
        for (Works cd : worksList) {
            if(set.add(cd)){
                newworksList.add(cd);
            }
        }

        //作品列表
        model.addAttribute("works",MyPaging.paging(newworksList,pageNum,pageSize));
        //学生姓名
        model.addAttribute("names",studentServiceimpl.selectAllNameAndId());
        //获取总页数
        int page = worksList.size()/pageSize;
        int generalPage = worksList.size()%pageSize==0&&page>0?page:page+1;
        //添加总页数
        model.addAttribute("generalPage",generalPage);
        //在model中添加当前页
        GetCurrentPage.getcurrentPage(pageNum,generalPage,model);

        //判断当前页是否有数据
        model.addAttribute("price",1);
        if(generalPage<pageNum){
            model.addAttribute("price",0);
        }
        //获取当前页

        return "teacher/works";
    }


    /**
     * 根据作品表id进行删除
     * @param id        作品删除
     * @return
     */
    @DeleteMapping("/teacher/work/{id}")
    @ResponseBody
    public void deleteWork(@PathVariable("id")int id,HttpServletRequest request){
        System.out.println(id);
        worksServiceimpl.deleteById(id,request);
    }


    /**
     * 处理分页按钮请求
     */

    @GetMapping("/teacher/workpage")
    public String pagingRequest(Model model, Page page)  {
        //判断该请求是否存在username参数
        if(page.getUsername().isEmpty()){
            //调用不带username参数的方法
            worksAll(model,page.getPageNum());
        }else {
            workByName(page.getUsername(),model,page.getPageNum());
        }
        return "teacher/works";
    }



    /**
     * 多选删除
     */

    @DeleteMapping("/teacher/worksmulti/{id}")
    @ResponseBody
    public void checkoutDel(@PathVariable("id") String id ,HttpServletRequest request){
        //拆分字符串
        String[] strs=id.split(",");
        for (String s : strs){
            deleteWork(Integer.parseInt(s),request);
        }
    }




    //学生端作品的代码

    @GetMapping(value = "/worklist/{page}")
    public ModelAndView getTeacherLog(Model model, @PathVariable int page, HttpServletRequest request, HttpServletResponse response) {
        //查找该学生所有的作品
        Cookie[] cookies = request.getCookies();
        //3.  循环遍历Cookie 取出用户手机号
        int id = 0;
        String name = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                }
            }
            List<Works> worksList = worksServiceimpl.selectWorks(id);
            //作品数据量
            int total = worksList.size();
            //防止数据库中没有值
            if (total == 0) {
                return new ModelAndView("student/studentWorks");
            }
            //总页数
            int totalPage = PageUtil.getTotalPage(total, PageUtil.count);
            //校对页数正确与否
            page = PageUtil.numberOfPage(page, totalPage);
            //页数集合
            List<Integer> totalPageList = PageUtil.pageUtil(page, totalPage);
            //分好页的作品集合
            List<Works> worksList1 = PageUtil.WorkList(page, totalPage, total, worksList);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalPageList", totalPageList);
            //修改页面显示的编号的作品集合
            model.addAttribute("worksList1", worksList1);
            model.addAttribute("name", name);
            return new ModelAndView("student/studentWorks");
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

    @PostMapping("/searchWorks/{page}")
    public ModelAndView  searchWorks(Model model, @PathVariable int page,String workname,HttpServletRequest request,HttpServletResponse response){
        //获取存在cookie的学生id
        Cookie[] cookies = request.getCookies();
        int student_id = 0;
        String name = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    student_id = Integer.parseInt(cookie.getValue());
                }
                if (cookie.getName().equals("name")) {
                    name = cookie.getValue();
                }
            }
            String workname1 = workname.replaceAll(" ","");
            //获取该学生的作品集合
            List<Works> worksList = worksServiceimpl.likeSelectWorks(workname1, student_id);
            //作品数据量
            int total = worksList.size();
            //防止数据库中没有值
            if (total == 0) {
                return new ModelAndView("student/studentWorks");
            }
            //总页数
            int totalPage = PageUtil.getTotalPage(total, PageUtil.count);
            //校对页数正确与否
            page = PageUtil.numberOfPage(page, totalPage);
            //页数集合
            List<Integer> totalPageList = PageUtil.pageUtil(page, totalPage);
            //分好页的作品集合
            List<Works> worksList1 = PageUtil.WorkList(page, totalPage, total, worksList);
            model.addAttribute("totalPage", totalPage);
            model.addAttribute("totalPageList", totalPageList);
            //修改页面显示的编号的作品集合
            model.addAttribute("worksList1", worksList1);
            model.addAttribute("name", name);
            return new ModelAndView("student/studentWorks");
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


    /**
     *  作品删除
     * @param id 作品id
     * @return 返回视图
     */
    @ResponseBody
    @GetMapping(value ="/selectWorks/{id}")
    public ModelAndView deleteWorks(@PathVariable("id")int id) {

        //删除数据库对应的id
        worksServiceimpl.deleteWorks(id);
        //重定向到主页面
        return new ModelAndView("redirect:/worklist/1");
    }

    /**
     * 修改作品
     * @return
     */
    @PostMapping(value = "/updateWorks")
    public ModelAndView  updateWorks(Integer id, String name, String url){
        int i =  worksServiceimpl.updateWorks(id, name, url);
        ModelAndView modelAndView = new ModelAndView();
        //设置视图名
        modelAndView.setViewName("studentWorks");
        return new ModelAndView("redirect:/worklist/1");
    }

    /**
     * 进入修改界面
     * @return
     */
    @GetMapping(value = "/toUpdateWorks/{id}")
    @ResponseBody
    public ModelAndView toUpdateWorks(@PathVariable("id") int id){
        ModelAndView modelAndView = new ModelAndView();
        Works works = worksServiceimpl.selectWorksById(id);
        //将自增id传到 修改页面的 input
        modelAndView.addObject("works",works);
        //跳转到 修改界面
        modelAndView.setViewName("student/updateWorks");
        return modelAndView;
    }

    /**
     * 作品增加
     * @param id
     * @param name
     * @param url
     * @return
     * @throws ParseException
     */
    @PostMapping(value = "/workList")
    public ModelAndView  insertWorks(int id ,String name,String url,HttpServletRequest request,HttpServletResponse response) throws ParseException {
        //获取当前时间戳
        Date add_time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(add_time);
        int add_time1 =(int) (sdf.parse(format).getTime()/1000);
        //获取存在cookie的学生id
        Cookie[] cookies = request.getCookies();
        int student_id = 0;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    student_id = Integer.parseInt(cookie.getValue());
                }
            }
            List<Works> worksList = worksServiceimpl.selectWorks(student_id);
            //获取学生id 增加该学生的作品
            Works works1 = new Works(id, student_id, name.trim(), url.trim(), add_time1);
            int i = worksServiceimpl.insertWorks(works1);
            return new ModelAndView("redirect:/worklist/1");
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

    /**
     * 进入添加界面
     * @return
     */
    @GetMapping(value = "/toInsertWorks")
    public ModelAndView insertWorks(Model model,HttpServletRequest request,HttpServletResponse response){
        model.addAttribute("worksId",worksServiceimpl.selectWorksId());
        //获取当前学生的信息
        Cookie[] cookies = request.getCookies();
        //3.  循环遍历Cookie 取出用户手机号
        int id = 0;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    id = Integer.parseInt(cookie.getValue());
                }
            }
            //获取当前学生的作品信息
            List<Works> worksList = worksServiceimpl.selectWorks(id);
            //获取当前学生的id
            model.addAttribute("insertWork", id);
            return new ModelAndView("student/insertWorks");
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




}
