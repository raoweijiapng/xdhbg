package cn.xdh.util;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author victor
 * @site https://ttk1907.github.io/
 * @company XDL
 * @project xdhbg
 * @package cn.xdh.util
 * @created 2019-12-27 18:56
 * @function ""
 */
public class CookieTestUtil {
    public void cookieTest(HttpServletResponse response,int teacher_id,String teacher_name,String add_ip) {
//        登录时添加cookie,测试用
        Cookie name = new Cookie("name", teacher_name);
        Cookie teacherId = new Cookie("teacherId", teacher_id+"");
        Cookie teacherIp = new Cookie("teacherIp", add_ip);
//        设置cookie存在时间
        name.setMaxAge(60 * 60 * 24*365);
        teacherId.setMaxAge(60 * 60 * 24*365);
        teacherIp.setMaxAge(60 * 60 * 24*365);
//        设置cookie存在路径
        name.setPath("/");
        teacherId.setPath("/");
        teacherIp.setPath("/");
        //发送cookie
        response.addCookie(name);
        response.addCookie(teacherId);
        response.addCookie(teacherIp);
    }
}
