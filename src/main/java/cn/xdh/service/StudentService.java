package cn.xdh.service;

import cn.xdh.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface StudentService {
    //根据手机号和密码获取学生
    public Student selectByPhoneAndPassword(String mobile, String password);

    //获取所有学生数量
    public int selectAllNumber();

    //根据手机号获得所有的学生
    public Student selectByMobile(String mobile);

    List<Map<String,Object>> getStudentByUndergraduate();

    List<Map<String,Object>> getStudentByGraduate();

    String selectCityById(Integer id);

    int addStudentService(Student student, HttpServletRequest request);

    List<Student> getAllStudentByGraduate(Integer graduate);

    Student getStudentByMobile(String mobile);

    List<Map<String,Object>> getStudentLikeUsername(int is_graduate, String username);

    Student getStudentById(int id);

    void deleteStudent(int id, HttpServletRequest request);

    List<Student> selectAllNameAndId();

    List<City> getProvince();

    List<City> getCityByUpId(int upId);

    List<City> getAreaByUpId(int upId);

    void updateStudent(Student student, HttpServletRequest request);

    Msg batchAddStudent(HttpServletRequest request, String suffixName, MultipartFile excelFile);

    List<Student> selectIdAndNameByName(String username);

    List<XdhClass> selectClassByUndergraduate();

    List<Student> selectStudentByClass(int class_id);

    public List<String> selectUsername();

    public int selectIdByPhone(String mobie);

    public int  selectIdByUsername(String username);

    // getNotices 查询公告
    public List<Notice> getNotices();

    public Student getDatas(int id);

    public String getClassName(int id);

    public List<Map<String,Object>> getUsefulData(int id);

    public void updateData(int id, String password, long birthday, String graduate_school, String stage_id, int province_id, int city_id, int area_id,String address);

    public void updateOther(int id, long birthday, String graduate_school, String stage_id, int province_id, int city_id, int area_id,String address);

    //    三级联动 获取省名

    public List<Map<String,Object>> getProvinceName();

    public List<Map<String,Object>> getCityName(int id);

    public List<Map<String,Object>> getAreaName(int id);

    //    通过省市县id获取名字

    public String getNameByProvinceid(int province_id);

    public String getNameByCityid(int city_id);

    public String getNameByAreaid(int area_id);

    // 通过实际工作地省市县id获取名字

    public String getNameByAimProvinceid(@Param("aim_province_id") int aim_province_id);

    public String getNameByAimCityid(@Param("aim_city_id") int aim_city_id);

    public String getNameByAimAreaid(@Param("aim_area_id") int aim_area_id);

}
