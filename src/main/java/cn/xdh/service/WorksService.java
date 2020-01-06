package cn.xdh.service;

import cn.xdh.entity.Works;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author TZB
 */
public interface WorksService {
    /**
     * 通过姓名查找作品
     * @return
     */
    List<Works> selectById(int id);
    //查询所有作品
    List<Works> selectAll();
    //根据作品名查找
    List<Works> selectByWorkName(String name);
    //根据id删除作品
    Integer deleteById(int id, HttpServletRequest request);

    //根据作品id查询作品返回一个集合
    public List<Works> selectWorks(int Student_id);

    //根据学生id找作品返回一个作品
    public Works selectWorksById(int id);

    //模糊查询
    public List<Works> likeSelectWorks(String name, int student_id);

    //查询作品id
    public List<Integer> selectWorksId();

    //根据作品id删除作品
    public int deleteWorks(int id);

    //根据作品id修改作品
    public int updateWorks(int id, String name, String url);

    //添加作品
    public int insertWorks(Works works);


}
