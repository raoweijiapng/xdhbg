package cn.xdh.util;


import cn.xdh.entity.TeacherLog;
import cn.xdh.entity.Works;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageUtil {
    public static final int count = 10;
    @Autowired
    static Map<String, Object> map;

    /**
     * 计算得到总页数
     *
     * @return
     */
    public static int getTotalPage(int total,int count) {
        int totalPage;
        // 假设总数是50，是能够被10整除的，那么就有5页
        if (0 == total % count)
            totalPage = total / count;
            // 假设总数是51，不能够被10整除的，那么就有6页
        else
            totalPage = total / count + 1;

        if (0 == totalPage)
            totalPage = 1;
        return totalPage;
    }

    //分页器
    public static List<Integer> pageUtil(int page,int totalPage){
        List<Integer> totalPageList = new ArrayList<Integer>();
        if (totalPage<=5){
            for (int i = 1;i<=totalPage;i++){
                totalPageList.add(i);
            }
        }else {
            if (page>3 && page<totalPage-2){
                for (int i = page-2;i<=page+2;i++){
                    totalPageList.add(i);
                }
            }else if(page>=totalPage-2){
                for (int i = totalPage-4;i<=totalPage;i++){
                    totalPageList.add(i);
                }
            }else if (page<=3){
                for (int i = 1;i<=5;i++){
                    totalPageList.add(i);
                }
            }
        }
        return totalPageList;
    }

    //返回未毕业的分好页的学生
    public static List<Map<String, Object>> undergraduateStudentList(int page,int totalPage,int total,List<Map<String, Object>> undergraduateStudentTotal){
        //数据集合
        List<Map<String, Object>> undergraduateStudentList = new ArrayList<Map<String, Object>>();
        map = new HashMap<String, Object>();
        //发送数据器
        int a = total%count;
        if(page == totalPage && a!=0){
            for(int i=total-a;i<total;i++){
                map = undergraduateStudentTotal.get(i);
                undergraduateStudentList.add(map);
            }
        }else{
            for(int i=(page-1)*count;i<page*count;i++){
                map = undergraduateStudentTotal.get(i);
                undergraduateStudentList.add(map);
            }
        }
        return undergraduateStudentList;
    }
    //返回毕业的分好页的学生
    public static List<Map<String, Object>> graduateStudentList(int page,int totalPage,int total,List<Map<String, Object>> graduateStudentTotal){
        //数据集合
        List<Map<String, Object>> graduateStudentList = new ArrayList<Map<String, Object>>();
        map = new HashMap<String, Object>();
        //发送数据器
        int a = total%count;
        if(page == totalPage && a!=0){
            for(int i=total-a;i<total;i++){
                map = graduateStudentTotal.get(i);
                graduateStudentList.add(map);
            }
        }else{
            for(int i=(page-1)*count;i<page*count;i++){
                map = graduateStudentTotal.get(i);
                graduateStudentList.add(map);
            }
        }
        return graduateStudentList;
    }

    //返回分好页的教师日志
    public static List<TeacherLog> teacherLogList(int page,int totalPage,int total,List<TeacherLog> teacherLogs){
        //数据集合
        List<TeacherLog> teacherLogList = new ArrayList<TeacherLog>();
        //发送数据器
        int a = total%count;
        if(page == totalPage && a!=0){
            for(int i=total-a;i<total;i++){
                TeacherLog teacherLog = teacherLogs.get(i);
                teacherLogList.add(teacherLog);
            }
        }else{
            for(int i=(page-1)*count;i<page*count;i++){
                TeacherLog teacherLog = teacherLogs.get(i);
                teacherLogList.add(teacherLog);
            }
        }
        return teacherLogList;
    }

    //校对页数正确与否
    public static int numberOfPage(int page,int totalPage){
        //校对页数正确与否
        if (page>totalPage){
            page=totalPage;
        }else if (page<1){
            page = 1;
        }
        return page;
    }

    //返回分好页的教师日志
    public static List<Works> WorkList(int page, int totalPage, int total, List<Works> teacherLogs){
        //数据集合
        List<Works> worksList = new ArrayList<Works>();
        //发送数据器
        int a = total%count;
        if(page == totalPage && a!=0){
            for(int i=total-a;i<total;i++){
                Works works = teacherLogs.get(i);
                worksList.add(works);
            }
        }else{
            for(int i=(page-1)*count;i<page*count;i++){
                Works works = teacherLogs.get(i);
                worksList.add(works);
            }
        }
        return worksList;
    }
}

