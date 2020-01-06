package cn.xdh.dao;

import cn.xdh.entity.City;
import cn.xdh.entity.Student;
import cn.xdh.entity.Notice;
import cn.xdh.entity.XdhClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StudentDao {
    //根据手机号和密码获取学生
    Student selectByPhoneAndPassword(@Param("mobile")String mobile,@Param("password")String password);

    //获取所有学生数量
    int selectAllNumber();

    //根据手机号获得所有的学生
    Student selectByMobile(String mobile);

    List<Map<String,Object>> getStudentByUndergraduate();

    List<Map<String,Object>> getStudentByGraduate();

    String selectCityById(Integer id);

    List<Student> getAllStudentByGraduate(@Param("is_graduate")int graduate);

    List<Student> selectStudentByClass(int class_id);
    
    void addStudent(Student student);

    Student selectStudentByMobile(String mobile);

    List<Map<String,Object>> selectStudentLikeUsername(@Param("is_graduate")int is_graduate,@Param("username")String username);

    Student selectStudentById(int id);

    void deleteStudent(int id);

    List<City> selectProvince();

    List<City> selectCityByUpId(int upId);

    List<City> selectAreaByUpId(int upId);

    void updateStudent(Student student);

    /**
     * 查询所有学生姓名和id
     * @return
     */
    public List<Student> selectAllNameAndId();

    /**
     * 根据姓名查询学生id
     */
    /*@Select(value = "select * from xdh_student where xdh_student.username = #{username}")*/
    List<Student> selectIdAndNameByName(@Param(value = "username") String username);


    // 根据手机号查id
    public int selectIdByPhone(@Param("mobie") String mobie);
    //
    public List<String> selectUsername();

    public int selectIdByUsername(@Param("username")String username);

    //    三级联动 获取省名
    public List<Map<String,Object>> getProvinceName();

    public List<Map<String,Object>> getCityName(@Param("id") int id);

    public List<Map<String,Object>> getAreaName(@Param("id") int id);

    public List<Notice> getNotices();


    public Student getDatas(@Param("id") int id);

    public List<Map<String,Object>> getUsefulData(@Param("id") int id);

    public void updateData(@Param("id") int id,@Param("password")String password,@Param("birthday")long birthday
            ,@Param("graduate_school")String graduate_school
            ,@Param("stage_id")String stage_id
            ,@Param("province_id")int province_id
            ,@Param("city_id")int city_id
            ,@Param("area_id")int area_id
            ,@Param("address")String address);

    public void updateOther(@Param("id") int id,@Param("birthday")long birthday
            ,@Param("graduate_school")String graduate_school
            ,@Param("stage_id")String stage_id
            ,@Param("province_id")int province_id
            ,@Param("city_id")int city_id
            ,@Param("area_id")int area_id
            ,@Param("address")String address);

    // 学生信息页面显示省市区
    public String getNameByProvinceid(@Param("province_id") int province_id);

    public String getNameByCityid(@Param("city_id") int city_id);

    public String getNameByAreaid(@Param("area_id") int area_id);

    // 学生实际工作城市
    public String getNameByAimProvinceid(@Param("aim_province_id") int aim_province_id);

    public String getNameByAimCityid(@Param("aim_city_id") int aim_city_id);

    public String getNameByAimAreaid(@Param("aim_area_id") int aim_area_id);

    List<XdhClass> selectClassByUndergraduate();
}
