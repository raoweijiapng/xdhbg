package cn.xdh.dao;

import cn.xdh.entity.XdhClass;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassDao {
    //获取所有班级数量
     int selectAllNumber();

    XdhClass selectClassById(int id);
}
