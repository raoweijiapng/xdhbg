package cn.xdh.service.impl;

import cn.xdh.dao.StudentDao;
import cn.xdh.entity.City;
import cn.xdh.entity.Student;
import cn.xdh.service.StudentService;
import cn.xdh.util.VerifyPhone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public Student selectByMobileAndPassword(String mobile, String password){
        Student student = studentDao.selectByMobileAndPassword(mobile,password);
        return student;
    }

    @Override
    public int addStudentService(Student student) {
        if (student.getUsername().trim().isEmpty() || student.getMobile().trim().isEmpty()){
            return 0;
        }
        //获取要增加的学生的手机号
        String mobile = student.getMobile();
        //验证手机号是否正确
        boolean flag = VerifyPhone.isMobile(mobile);
        if (!flag){
            return 1;
        }
        //查询该学生是否已存在,存在则返回0,不存在则继续添加
        Student student1 = studentDao.selectStudentByMobile(mobile);
        if (student1!=null){
            return 2;
        }

        int newTime = (int) (System.currentTimeMillis()/1000);
        //入学时间join_study_time 默认系统当前时间
        student.setJoin_study_time(newTime);
        //是否毕业is_graduate 默认是0未毕业
        student.setIs_graduate(0);
        //阶段stage_id 默认是1,1阶段
        student.setStage_id(1);
        //密码password 默认是 123
        student.setPassword("123");

        studentDao.addStudent(student);
        return 3;
    }

    @Override
    public List<Student> getAllStudentByGraduate(Integer graduate) {
        return studentDao.getAllStudentByGraduate(graduate);
    }

    @Override
    public Student getStudentByMobile(String mobile) {
        return studentDao.selectStudentByMobile(mobile);
    }

    @Override
    public List<Student> getStudentLikeUsername(String username) {
        if (username.trim().isEmpty()){
            return null;
        }
        return studentDao.selectStudentLikeUsername(username);
    }

    @Override
    public Student getStudentById(int id) {
        return studentDao.selectStudentById(id);
    }

    @Override
    public void deleteStudent(int id) {
        studentDao.deleteStudent(id);
    }

    @Override
    public List<City> getProvince() {
        return studentDao.selectProvince();
    }

    @Override
    public List<City> getCityByUpId(int upId) {
        return studentDao.selectCityByUpId(upId);
    }
}
