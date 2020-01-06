package cn.xdh.dao;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import cn.xdh.entity.TeacherLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherDao {
    //根据手机号和密码获取教师
    Teacher selectByPhoneAndPassword(@Param("mobile") String mobile, @Param("password") String password);

    //获取所有教师数量
    int selectAllNumber();

    List<Teacher> selectAllTeacher();

    int deleteByTeacher(@Param("id") int id);

    int insertByTeacher(Teacher teacher);

    int updateByTeacher(Teacher teacher);

    List<TeacherClass> selectTeacherClass();

    Teacher selectTeacherMobile(String mobile);

    Teacher selectTeacherByMobileAndId(Teacher teacher);

    Teacher selectByTeacher(int id);

    //增加教师操作日志
    void addTeacherLog(TeacherLog teacherLog);

    //查看教师操作日志列表
    List<TeacherLog> selectTeacherLog(int teacher_id);

    //模糊查看教师操作日志列表
    List<TeacherLog> selectTeacherLogLikeAction(@Param("teacher_id") int teacher_id,@Param("action")String action);


}
