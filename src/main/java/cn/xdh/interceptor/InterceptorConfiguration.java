package cn.xdh.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private TeacherInterceptor teacherinterceptor;
    @Autowired
    private AdminInterceptor admininterceptor;
    @Autowired
    private StudentInterceptor studentinterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //教师的拦截地址
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/teacher.index");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/teacher/**");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/aNoticeManage");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/noticeManage");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/selectNotice/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/delManage/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/province/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/city/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/**");
/*        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/Undergraduate/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/Graduate/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/add/**");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/like/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/edit/**");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/add/batch/");
        registry.addInterceptor(teacherinterceptor).addPathPatterns("/student/delete/");*/

        //管理员的拦截地址
        registry.addInterceptor(admininterceptor).addPathPatterns("/admin.**");
        registry.addInterceptor(admininterceptor).addPathPatterns("/admin**");

        //学生的拦截地址
        registry.addInterceptor(studentinterceptor).addPathPatterns("/stucity/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/stuprovince/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/selectExperience/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/insertExperience");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/toEditor");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/student.index");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/list");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/updatestudent/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/edit/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/selectWorks/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/worklist/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/searchWorks/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/updateWorks");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/toUpdateWorks/");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/workList");
        registry.addInterceptor(studentinterceptor).addPathPatterns("/toInsertWorks");


    }
}
