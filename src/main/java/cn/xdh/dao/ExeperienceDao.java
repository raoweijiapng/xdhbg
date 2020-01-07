package cn.xdh.dao;

import cn.xdh.entity.Exeperience;
import cn.xdh.entity.Experience;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author TZB
 */
@Repository
public interface ExeperienceDao {
    //查询所有数据
    List<Exeperience> selectAll();
    //教师根据id查看详细心得
    Exeperience selectById(int id);

    //根据学生id查找
    List<Exeperience> selectByStudent_id(int id);
    //根据id删除
    Integer deleteById(int id);

    // 查所有心得
    List<Experience> selectExperience();
    // 删除某个心得
    int deleteExperience(@Param("id") int id);
    // 增加某个心得
    int insertExperience(Experience experience);
}
