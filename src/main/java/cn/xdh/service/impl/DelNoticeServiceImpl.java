package cn.xdh.service.impl;

import cn.xdh.SomeMethods;
import cn.xdh.dao.DelNoticeDao;
import cn.xdh.dao.TeacherDao;
import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherLog;
import cn.xdh.service.DelNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static cn.xdh.SomeMethods.*;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.service.impl
 * @created 2019-12-30 14:33
 * @function ""
 */
@Service
public class DelNoticeServiceImpl implements DelNoticeService {
    @Autowired
    private DelNoticeDao delNoticeDao;
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public Integer delNoticeService(Integer id, HttpServletRequest request) {
        String action = "删除公告";
        //获取cookie中的老师信息
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
        TeacherLog teacherLog = new TeacherLog(teacher.getId(),teacher.getName(),action,SomeMethods.getCurrentTime(),SomeMethods.getIp4());
        //将日志实体类添加到日志表中
        teacherDao.addTeacherLog(teacherLog);
        return delNoticeDao.delNoticeDao(id);
    }
}
