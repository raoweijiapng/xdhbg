package cn.xdh.interceptor;

import cn.xdh.SomeMethods;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AdminInterceptor implements HandlerInterceptor {
    /*
     * 视图渲染之后的操作（Controller方法调用之前）
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //System.out.println("-----preHandle------");
        response.setContentType("text/html; charset=utf-8");
        String result = SomeMethods.checkCookieAndSession(response,request);
        //System.out.println(result);
        if (result.equals("admin")){
            return true;
        }
        response.getWriter().append("<script>alert('请先进行管理员登录！');window.location.href='/';</script>");

        return false;
    }

    /*
     * 处理请求完成后视图渲染之前的处理操作（Controller方法调用之后）
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
        //System.out.println("-----postHandle------");
    }

    /*
     * 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
        //System.out.println("-----afterCompletion------");
    }
}
