package cn.xdh.dao;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {
    //根据手机号和密码获取教师
    public Teacher selectByPhoneAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    //获取所有教师数量
    public int selectAllNumber();

    List<Teacher> selectAllTeacher();

    int deleteByTeacher(@Param("id") int id);

    int insertByTeacher(Teacher teacher);

    int updateByTeacher(Teacher teacher);

    List<TeacherClass> selectTeacherClass();

    Teacher selectTeacherMobile(String mobile);

    Teacher selectByTeacher(int id);

}