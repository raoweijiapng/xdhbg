package cn.xdh.service.impl;

import cn.xdh.SomeMethods;
import cn.xdh.dao.ExeperienceDao;
import cn.xdh.dao.TeacherDao;
import cn.xdh.entity.Exeperience;
import cn.xdh.entity.Experience;
import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherLog;
import cn.xdh.service.ExeperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TZB
 */
@Service
public class ExeperienceServiceImpl implements ExeperienceService {

    @Autowired
    private ExeperienceDao exeperienceDao;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    //查询所有
    public List<Exeperience> selectAll() {
        return exeperienceDao.selectAll();
    }

    //根据学生id查询
    @Override
    public List<Exeperience> selectByStudent_id(int id) {
        return exeperienceDao.selectByStudent_id(id);
    }
    //根据id删除
    @Override
    public Integer deleteById(int id, HttpServletRequest request) {
        //获取cookie中的老师信息
        String action = "删除心得";
        Cookie[] cookies = request.getCookies();
        String mobile = null;
        String password = null;
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("mobile")){
                mobile = cookie.getValue();
            }
            if (cookie.getName().equals("password")){
                password = cookie.getValue();
            }

        }
        Teacher teacher = teacherDao.selectByPhoneAndPassword(mobile,password);
        TeacherLog teacherLog = new TeacherLog(teacher.getId(),teacher.getName(),action, SomeMethods.getCurrentTime(),SomeMethods.getIp4());
        //将日志实体类添加到日志表中
        teacherDao.addTeacherLog(teacherLog);
        return exeperienceDao.deleteById(id);
    }

    @Override
    public List<Experience> selectExperience() {
        List<Experience> experience = exeperienceDao.selectExperience();
        return experience;
    }

    @Override
    public int deleteExperience(int id) {
        int dd = exeperienceDao.deleteExperience(id);
        return dd;
    }

    @Override
    public int insertExperience(Experience experience) {
        int dd = exeperienceDao.insertExperience(experience);
        return dd;
    }

}
