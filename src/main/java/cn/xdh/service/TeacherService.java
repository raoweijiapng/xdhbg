package cn.xdh.service;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import cn.xdh.entity.TeacherLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    //根据手机号和密码获取教师
    Teacher selectByPhoneAndPassword(String mobile, String password);

    //获取所有教师数量
    int selectAllNumber();

    List<Teacher> selectAllTeacher();

    Teacher selectByTeacher(int id);

    int deleteByTeacher(int id);

    int insertByTeacher(Teacher teacher);

    int updateByTeacher(Teacher teacher);

    List<TeacherClass> selectTeacherClass();

    Page<Teacher> selectAllTeacher(int page, int size);

    Page<Teacher> getAllTeacherBy(int page, int size,String lookname);

    //通过手机号查看教师信息
    Teacher selectTeacherMobile(String mobile);

    //增加教师操作日志
    void addTeacherLog(TeacherLog teacherLog);

    //查看教师操作日志列表
    List<TeacherLog> selectTeacherLog(int teacher_id);

    //模糊查看教师操作日志列表
    List<TeacherLog> selectTeacherLogLikeAction(int teacher_id,String action);
}
