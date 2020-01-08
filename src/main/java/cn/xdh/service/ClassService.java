package cn.xdh.service;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import cn.xdh.entity.XdhClass;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClassService {
    //获取所有班级数量
    public int selectAllNumber();

    List<XdhClass> selectByXdhClass();

    int deleteByXdhClass(int id);

    int insertByXdhClass(XdhClass xdhClass);

    int updateByXdhClass(XdhClass xdhClass);

    Page<TeacherClass> selectAllXdhClass(int page, int size);

    Page<TeacherClass> getAllTeacherClassBy(int page, int size, String lookname);

    XdhClass selectByClassName(String class_name);

    XdhClass selectClassById(int id);

    XdhClass selectClassNameById(int id);
}
