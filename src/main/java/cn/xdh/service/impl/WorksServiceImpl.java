package cn.xdh.service.impl;

import cn.xdh.SomeMethods;
import cn.xdh.dao.StudentDao;
import cn.xdh.dao.TeacherDao;
import cn.xdh.dao.WorksDao;
import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherLog;
import cn.xdh.entity.Works;
import cn.xdh.service.WorksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TZB
 */
@Service
public class WorksServiceImpl implements WorksService {
    @Autowired
    private WorksDao worksDao;
    @Autowired
    private TeacherLog teacherLog;
    @Autowired
    private TeacherDao teacherDao;

    @Override
    //根据学生id查询作品
    public List<Works> selectById(int id) {
        return worksDao.selectById(id);
    }

    @Override
    //根据作品名查找
    public List<Works> selectByWorkName(String name) {
        return worksDao.selectByWorkName(name);
    }

    @Override
    //查找所有作品
    public List<Works> selectAll() {
        return worksDao.selectAll();
    }
    @Override
    public Integer deleteById(int id, HttpServletRequest request) {
        //获取cookie中的老师信息
        String action = "删除作品";
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
        return worksDao.deleteById(id);
    }

    //获取所有作品
    public List<Works> selectWorks(int student_id) {

        List<Works> list = worksDao.selectWorks(student_id);
        return list;
    }

    public Works selectWorksById(int id) {
        Works works = worksDao.selectWorksById(id);
        return works;
    }

    public List<Works> likeSelectWorks(String name,int student_id) {
        List<Works> worksList = worksDao.likeSelectWorks(name,student_id);
        return worksList;
    }

    //获取所有学生id
    public List<Integer> selectWorksId(){

        List<Integer> list = worksDao.selectWorksId();
        return list;
    }

    //删除作品
    public int deleteWorks(int id){

        int rows = worksDao.deleteWorks(id);
        return rows;
    }

    //更新作品名称或者网址
    public int updateWorks(int id, String name, String url){

        int rows = worksDao.updateWorks(id, name, url);
        return rows;
    }

    //增加作品
    public int insertWorks(Works works){

        int rows = worksDao.insertWorks(works);
        return rows;
    }



}
