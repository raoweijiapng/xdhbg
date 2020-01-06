package cn.xdh.web;

import cn.xdh.SomeMethods;
import cn.xdh.entity.*;
import cn.xdh.service.ClassService;
import cn.xdh.service.StudentService;
import cn.xdh.service.impl.StudentServiceImpl;
import cn.xdh.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class StudentController {
    @Autowired
    private StudentServiceImpl studentservice;
    @Autowired
    private ClassService classService;

    @GetMapping("/student/Undergraduate/{page}")
    public String UndergraduateStudentList(Model model,@PathVariable int page) {
        List<Map<String, Object>> undergraduateStudentTotal = studentservice.getStudentByUndergraduate();
        //System.out.println(undergraduateStudentTotal);
        //数据量
        int total = undergraduateStudentTotal.size();
        //防止数据库中没有值
//        undergraduateStudentTotal = PageUtil.preventionNull(total,undergraduateStudentTotal);
        if (total==0){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("username","没有数据");
            map.put("join_study_time",(long)0);
            undergraduateStudentTotal.add(map);
            total = undergraduateStudentTotal.size();
        }
        //总页数
        int totalPage = PageUtil.getTotalPage(total,PageUtil.count);
        //校对页数正确与否
        page=PageUtil.numberOfPage(page,totalPage);
        //页数集合
        List<Integer> totalPageList = PageUtil.pageUtil(page,totalPage);
        //分好页的未毕业学生集合
        List<Map<String,Object>> undergraduateStudentList = PageUtil.undergraduateStudentList(page,totalPage,total,undergraduateStudentTotal);
        //System.out.println(undergraduateStudentList);
        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalPageList",totalPageList);
        model.addAttribute("StudentList", undergraduateStudentList);
        model.addAttribute("likeName",0);
        model.addAttribute("p",page);
        return "teacher/UndergraduateStudentList";
    }

    @GetMapping("/student/Graduate/{page}")
    public String GraduateStudentList(Model model,@PathVariable int page) {
        List<Map<String, Object>> graduateStudentTotal = studentservice.getStudentByGraduate();
        //数据量
        int total = graduateStudentTotal.size();
        //防止数据库中没有值
        if (total==0){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("username","没有数据");
            map.put("graduate_time",(long)0);
            graduateStudentTotal.add(map);
            total = graduateStudentTotal.size();
        }
        //总页数
        int totalPage = PageUtil.getTotalPage(total,PageUtil.count);
        //校对页数正确与否
        page=PageUtil.numberOfPage(page,totalPage);
        //页数集合
        List<Integer> totalPageList = PageUtil.pageUtil(page,totalPage);
        //分好页的毕业学生集合
        List<Map<String,Object>> graduateStudentList = PageUtil.graduateStudentList(page,totalPage,total,graduateStudentTotal);

        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalPageList",totalPageList);
        model.addAttribute("StudentList", graduateStudentList);
        model.addAttribute("likeName",0);
        model.addAttribute("p",page);
        return "teacher/GraduateStudentList";
    }

    //跳转到添加学生页面
    @GetMapping("/student/add/view")
    public String addStudentView(Model model) {
        List<XdhClass> classList = studentservice.selectClassByUndergraduate();
        model.addAttribute("classList",classList);
        return "teacher/StudentAdd";
    }

    //接受添加学生页面发来的参数,并封装成学生对象
    @PostMapping("/student/add")
    @ResponseBody
    public Msg addStudent(Student student, HttpServletRequest request) {
        int a = studentservice.addStudentService(student, request);
        Msg msg = new Msg();
        if (a == 1) {
            //1是添加失败,手机号不正确
            msg.setMsg("添加失败,请输入正确的手机号");
            return msg;
        } else if (a == 2) {
            //2是添加失败,已存在该账户
            msg.setMsg("添加失败,该手机号已被注册");
            return msg;
        }
        //3是添加成功
        msg.setMsg("添加成功");
        return msg;
    }

    //毕业学员的模糊查询/student/like/0/1/
    @GetMapping("/student/like/{is_graduate}/{page}/{username}")
    public String undergraduateStudentList(Model model, @PathVariable String username, @PathVariable int is_graduate,@PathVariable int page) {
        List<Map<String,Object>> StudentTotalByLikeName = studentservice.getStudentLikeUsername(is_graduate,username);
        //数据量
        int total = StudentTotalByLikeName.size();
        //防止数据库中没有值
        if (total==0){
            Map<String, Object> map = new HashMap<String,Object>();
            map.put("username","没有数据");
            map.put("graduate_time",(long)0);
            map.put("join_study_time",(long)0);
            StudentTotalByLikeName.add(map);
            total = StudentTotalByLikeName.size();
        }
        //总页数
        int totalPage = PageUtil.getTotalPage(total,PageUtil.count);
        //校对页数正确与否
        page=PageUtil.numberOfPage(page,totalPage);
        //页数集合
        List<Integer> totalPageList = PageUtil.pageUtil(page,totalPage);
        //分好页的毕业学生集合
        List<Map<String,Object>> StudentList = PageUtil.graduateStudentList(page,totalPage,total,StudentTotalByLikeName);

        model.addAttribute("totalPage",totalPage);
        model.addAttribute("totalPageList",totalPageList);
        model.addAttribute("StudentList", StudentList);
        model.addAttribute("likeName",1);
        model.addAttribute("name",username);
        model.addAttribute("p",page);
        if (is_graduate==0){
            return "teacher/UndergraduateStudentList";
        }else{
            return "teacher/GraduateStudentList";
        }
    }

    //跳转到修改学生信息页面
    @GetMapping("/student/edit/view/{id}")
    public String editStudentView(@PathVariable int id, Model model) {
        List<XdhClass> xdhClass = classService.selectByXdhClass();

        Student student = studentservice.getStudentById(id);

        List<City> cityList = studentservice.getProvince();

        XdhClass xdhClass1 = classService.selectClassById(student.getClass_id());
        model.addAttribute("xdhClassName",xdhClass1);
        model.addAttribute("xdhClass",xdhClass);
        model.addAttribute("cityList", cityList);
        model.addAttribute("student", student);
        return "teacher/StudentEdit";
    }

    //接收参数修改学生信息
    @PutMapping("/student/edit/{id}")
    public String editStudent(Student student, HttpServletRequest request) {
        studentservice.updateStudent(student, request);
        return "teacher/UndergraduateStudentList";
    }

    //删除学生
    @DeleteMapping("/student/delete/{id}")
    @ResponseBody
    public Object deleteStudent(@PathVariable int id, HttpServletRequest request) {
        studentservice.deleteStudent(id, request);
        return id;
    }

    //批量添加学生
    @PostMapping("/student/add/batch/{suffixName}")
    @ResponseBody
    public Msg addAllStudent(@RequestParam("ExcelFile") MultipartFile excelFile,HttpServletRequest request,@PathVariable String suffixName) throws Exception {
        return studentservice.batchAddStudent(request,suffixName,excelFile);
    }


    //学生端的代码

    @GetMapping("/student.index")
    public ModelAndView toIndex(){
        ModelAndView mav = new ModelAndView();
        List<Notice> notices = studentservice.getNotices();
        mav.addObject("msgs",notices);
        mav.setViewName("student/index");
        return mav;
    }

    /**
     * 学生信息页面根据id显示信息
     * @return
     */
    @GetMapping(value = "/list")
    public ModelAndView getUsefulData(HttpServletResponse response,HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        int student_id = 0;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("id")) {
                    //将字符串形式的id转换成int类型
                    student_id = Integer.parseInt(cookie.getValue());
//                    System.out.println(student_id);
                }
            }
            List<Map<String, Object>> usefulDataList = studentservice.getUsefulData(student_id);
            //System.out.println(usefulDataList);
            ModelAndView mav = new ModelAndView();
            //所在城市省市县
            int province_id = 1;
            int city_id = 37;
            int area_id = 567;
            if(usefulDataList.get(0).get("province_id") == null ){


            }else{
                province_id = (Integer) usefulDataList.get(0).get("province_id");
                city_id = (Integer) usefulDataList.get(0).get("city_id");
                area_id = (Integer) usefulDataList.get(0).get("area_id");
            }


            //实际工作省市县

            int aim_province_id = 1;
            int aim_city_id = 37;
            int aim_area_id = 567;
            if(usefulDataList.get(0).get("aim_province_id")==null){

            }else{
                aim_province_id = (Integer) usefulDataList.get(0).get("aim_province_id");
                aim_city_id = (Integer) usefulDataList.get(0).get("aim_city_id");
                aim_area_id = (Integer) usefulDataList.get(0).get("aim_area_id");

            }

            //所在城市省市县
            String province = studentservice.getNameByProvinceid(province_id);
            String city = studentservice.getNameByCityid(city_id);
            String area = studentservice.getNameByAreaid(area_id);
            //实际工作省市县
            String aimProvince = studentservice.getNameByAimProvinceid(aim_province_id);
            String aimCity = studentservice.getNameByAimCityid(aim_city_id);
            String aimArea = studentservice.getNameByAimAreaid(aim_area_id);
            usefulDataList.get(0).put("province", province);
            usefulDataList.get(0).put("city", city);
            usefulDataList.get(0).put("area", area);
            usefulDataList.get(0).put("aimProvince", aimProvince);
            usefulDataList.get(0).put("aimCity", aimCity);
            usefulDataList.get(0).put("aimArea", aimArea);
            mav.addObject("dats", usefulDataList);
            mav.setViewName("student/studentDatas");
            return mav;
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
     * 学生信息修改页面显示未修改前的信息,查出当前员工信息,在页面回显
     * @param id
     * @return
     */
    @GetMapping("/edit/{id}")
    public ModelAndView datasedit(@PathVariable("id") int id){
        List<Map<String, Object>> usefulDataList = studentservice.getUsefulData(id);
        ModelAndView mav = new ModelAndView();
        //实际工作省市县
        int aim_province_id = 1;
        int aim_city_id = 37;
        int aim_area_id = 567;
        if(usefulDataList.get(0).get("aim_province_id")==null){

        }else {
            aim_province_id = (Integer)usefulDataList.get(0).get("aim_province_id");
            aim_city_id = (Integer)usefulDataList.get(0).get("aim_city_id");
            aim_area_id = (Integer) usefulDataList.get(0).get("aim_area_id");
        }
        String aimProvince = studentservice.getNameByAimProvinceid(aim_province_id);
        String aimCity = studentservice.getNameByAimCityid(aim_city_id);
        String aimArea = studentservice.getNameByAimAreaid(aim_area_id);
        usefulDataList.get(0).put("aimProvince",aimProvince);
        usefulDataList.get(0).put("aimCity",aimCity);
        usefulDataList.get(0).put("aimArea",aimArea);
        //System.out.println(usefulDataList);
        mav.addObject("edits",usefulDataList.get(0));
        List<Map<String, Object>> provinceName = studentservice.getProvinceName();
        mav.addObject("provinceList",provinceName);
        mav.setViewName("student/datasedit");
        return mav;
    }

    @PostMapping("/updatestudent/")
    public ModelAndView updataData(@RequestParam("id")int id,
                                   @RequestParam("password")String password,
                                   @RequestParam("birth")String birth,
                                   @RequestParam("graduate_school")String graduate_school,
                                   @RequestParam("stage_id")String stage_id,
                                   @RequestParam("province_id")int province_id,
                                   @RequestParam("city_id")int city_id,
                                   @RequestParam("area_id")int area_id,
                                   @RequestParam("address")String address) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(password);
        long birthday =  sdf.parse(birth).getTime()/1000;
        if (password == ""){
            studentservice.updateOther(id,birthday, graduate_school, stage_id, province_id, city_id, area_id, address);
        }else {
            String md5password = SomeMethods.md5(password);
            studentservice.updateData(id, md5password, birthday, graduate_school, stage_id, province_id, city_id, area_id, address);
        }
        //List<Map<String, Object>> usefulData2 = studentservice.getUsefulData(id);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("redirect:/list");
        return mav;
    }

}
