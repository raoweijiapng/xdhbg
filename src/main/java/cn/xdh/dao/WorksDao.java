package cn.xdh.dao;

import cn.xdh.entity.Works;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * @author TZB
 */
@Repository
public interface WorksDao {
    //根据学生id查找
    List<Works> selectById(int id);
    //查找所有数据
    List<Works> selectAll();
    //根据照作品名查找
    List<Works> selectByWorkName(@Param("name")String name);
    //根据id删除
    Integer deleteById(int id);

    /**
     * 查询学生作品
     * @return 返回一个作品集合
     */
    public List<Works> selectWorks(@Param("student_id") int student_id);

    /**
     * 根据name 模糊查询作品集合
     * @param name
     * @return
     */
    public List<Works> likeSelectWorks(@Param("name") String name, @Param("student_id") int student_id);

    /**
     *  根据id找作品
     * @param id
     * @return
     */
    public Works selectWorksById(@Param("id") int id);

    /**
     * 查询所有id
     * @return
     */
    public List<Integer> selectWorksId();

    /**
     * 删除学生作品
     * @param id 根据作品id删除
     * @return 返回大于0 删除成功 否则失败
     */
    public int deleteWorks(@Param("id") int id);

    /**
     * 修改作品信息
     * @return 返回值大于0 修改成功 否则失败
     */
    public int updateWorks(@Param("id") int id, @Param("name") String name, @Param("url") String url);

    /**
     * 增加学生作品
     * @param works 作品
     * @return 返回值大于0 增加成功 否则失败
     */
    public int insertWorks(Works works);

}
