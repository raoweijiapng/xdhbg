package cn.xdh.service.impl;

import cn.xdh.SomeMethods;
import cn.xdh.dao.TeacherDao;
import cn.xdh.dao.TeacherRepository;
import cn.xdh.entity.AdminLog;
import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import cn.xdh.entity.TeacherLog;
import cn.xdh.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherdao;
    @Autowired
    private TeacherRepository teacherrepository;


    //根据手机号和密码获取教师
    @Override
    public Teacher selectByPhoneAndPassword(String mobile, String password){
        Teacher teacher = teacherdao.selectByPhoneAndPassword(mobile,password);
        return teacher;
    }

    //获取所有教师数量
    @Override
    public int selectAllNumber(){
        int number = teacherdao.selectAllNumber();
        return number;
    }

    //查询全部老师表,放到一个list集合中
    @Override
    public List<Teacher> selectAllTeacher() {
        List<Teacher> selectByTeacher = teacherdao.selectAllTeacher();
        return selectByTeacher;
    }
    @Override
    public Teacher selectByTeacher(int id) {
        Teacher selectByTeacher = teacherdao.selectByTeacher(id);
        return selectByTeacher;
    }
    //通过id来删除老师
    @Override
    public int deleteByTeacher(int id) {
        int deleteByTeacher = teacherdao.deleteByTeacher(id);
        return deleteByTeacher;
    }
    //添加老师
    @Override
    public int insertByTeacher(Teacher teacher) {
        //获取当前操作的时间
        long addTime = SomeMethods.getCurrentTime();
        teacher.setAdd_time(addTime);
        //获取当前操作的ip
        String addIp = SomeMethods.getIp4();
        teacher.setAdd_ip(addIp);

        int insertByTeacher = teacherdao.insertByTeacher(teacher);
        return insertByTeacher;
    }

    //更新老师信息
    @Override
    public int updateByTeacher(Teacher teacher) {
        Teacher teacher1 = teacherdao.selectTeacherByMobileAndId(teacher);
        int updateByTeacher = 0;
        if (teacher1 == null){
            updateByTeacher = teacherdao.updateByTeacher(teacher);
        }
        return updateByTeacher;
    }

    //获取老师与班级的各字段放入list集合中
    @Override
    public List<TeacherClass> selectTeacherClass() {
        List<TeacherClass> selectTeacherClass = teacherdao.selectTeacherClass();
        return selectTeacherClass;
    }

    @Override
    public Page<Teacher> selectAllTeacher(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Teacher> teacherpage = teacherrepository.findAll(pageable);
        return teacherpage;
    }

    @Override
    public Page<Teacher> getAllTeacherBy(int page, int size, final String lookname){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        final Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Teacher> queryCondition = new Specification<Teacher>() {
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查找条件
                Predicate p1 = cb.like(root.get("name").as(String.class), "%" + lookname + "%");
                Predicate p2 = cb.like(root.get("mobile").as(String.class), "%" + lookname + "%");
                return cb.or(p1,p2);
            }
        };
        Page<Teacher> teacherpage = teacherrepository.findAll(queryCondition,pageable);
        return teacherpage;
    }

    //根据手机号码查看老师信息
    @Override
    public Teacher selectTeacherMobile(String mobile){
        Teacher teacher = teacherdao.selectTeacherMobile(mobile);
        return teacher;
    }

    //查看老师操作日志列表
    @Override
    public List<TeacherLog> selectTeacherLog(int id) {
        return teacherdao.selectTeacherLog(id);
    }

    //增加老师操作日志
    @Override
    public void addTeacherLog(TeacherLog teacherLog) {
        teacherdao.addTeacherLog(teacherLog);
    }

    //模糊查看老师操作日志列表
    @Override
    public List<TeacherLog> selectTeacherLogLikeAction(int teacher_id,String action){
        return teacherdao.selectTeacherLogLikeAction(teacher_id,action);
    }
}
