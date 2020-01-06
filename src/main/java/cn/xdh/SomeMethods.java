package cn.xdh;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherLog;
import cn.xdh.service.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Id;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.InetAddress;
import java.security.MessageDigest;
import java.util.Enumeration;

public class SomeMethods {
    //获取ip地址
    public static String getIp4(){
        InetAddress ia=null;
        try {
            ia=ia.getLocalHost();
            String localip=ia.getHostAddress();
            return localip;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    // 获取当前时间戳
    public static Long getCurrentTime(){
        Long startTs = System.currentTimeMillis()/1000;
        return startTs;
    }

    //md5加密
    public static String md5(String s) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(s.getBytes("utf-8"));
            //System.out.println(new String(bytes));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private static String toHex(byte[] bytes) {

        final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
        StringBuilder ret = new StringBuilder(bytes.length * 2);
        for (int i=0; i<bytes.length; i++) {
            ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
            ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
        }
        return ret.toString();
    }

    //设置cookie和session
    public static void setCookieAndSession(String type,int id, String name, String password,String mobile, HttpServletResponse response, HttpServletRequest request) {
        //1.  创建Cookie
        Cookie cookie = new Cookie("name", name);
        Cookie cookie1 = new Cookie("password", password);
        Cookie cookie2 = new Cookie("mobile", mobile);
        String cookieid = Integer.toString(id);
        Cookie cookie3 = new Cookie("id", cookieid);
        Cookie cookie4 = new Cookie("type", type);
        //    设置存活时长为1年
        cookie.setMaxAge(365*24*60*60);
        cookie1.setMaxAge(365*24*60*60);
        cookie2.setMaxAge(365*24*60*60);
        cookie3.setMaxAge(365*24*60*60);
        cookie4.setMaxAge(365*24*60*60);

        //    设置cookie存储路径为/
        cookie.setPath("/");
        cookie1.setPath("/");
        cookie2.setPath("/");
        cookie3.setPath("/");
        cookie4.setPath("/");
        //2.  将Cookie 添加到响应头部
        response.addCookie(cookie);
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        response.addCookie(cookie3);
        response.addCookie(cookie4);

        //获得session
        HttpSession session =  request.getSession();
        //把用户名存入session
        session.setAttribute("uname", name);
        session.setAttribute("password", password);
        session.setAttribute("mobile", mobile);
        session.setAttribute("id", id);
        session.setAttribute("type", type);

    }

    //查看cookie和session中是否有用户名
    public static String checkCookieAndSession(HttpServletResponse response, HttpServletRequest request) throws IOException {
        Cookie[] cookies = request.getCookies();
        //3.  循环遍历Cookie 取出用户手机号
        String type = null;
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("type")) {
                    type = cookie.getValue();
                    if(type == null) {
                        HttpSession session  = request.getSession();
                        type = (String) session.getAttribute("type");
                        if (type == null){
                            return type;
                        }
                        return type;
                    }
                    return type;
                }
            }
        }
        return type;
    }

    //删除cookie和session
    public static void deleteCookieAndSession(HttpServletResponse response, HttpServletRequest request){
        /*//删除名称为name的Cookie
        Cookie newCookie=new Cookie("name",null);
        //立即删除型
        newCookie.setMaxAge(0);
        //项目所有目录均有效，这句很关键，否则不敢保证删除
        newCookie.setPath("/");
        //重新写入，将覆盖之前的
        response.addCookie(newCookie);*/

        //删除所有Cookie
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie : cookies){
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }

        //获得session
        HttpSession session =  request.getSession();
        /*//清除SESSION里的name属性
        session.removeAttribute("name");*/

        //得到session中所有的属性名
        Enumeration em = request.getSession().getAttributeNames();
        while (em.hasMoreElements()) {
            //遍历删除session中的值
            session.removeAttribute(em.nextElement().toString());
        }
    }



}
