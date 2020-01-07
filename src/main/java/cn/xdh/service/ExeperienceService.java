package cn.xdh.service;

import cn.xdh.dao.ExeperienceDao;
import cn.xdh.entity.Exeperience;
import cn.xdh.entity.Experience;
import cn.xdh.entity.Works;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TZB
 */
public interface ExeperienceService {

    //获取所有数据
    List<Exeperience> selectAll();
    //根据学生id查找
    List<Exeperience> selectByStudent_id(int id);
    //根据id删除
    Integer deleteById(int id, HttpServletRequest request);
    //根据id查看心得
    Exeperience selectById(int id);


    // 遍历所有心得
    List<Experience> selectExperience();

    // 删除某个心得
    int deleteExperience(int id);

    // 增加某个心得
    int insertExperience(Experience experience);


}
