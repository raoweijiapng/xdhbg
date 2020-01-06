package cn.xdh.dao;

import cn.xdh.entity.XdhClass;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface XdhClassDao {

    List<XdhClass> selectByXdhClass();

    int deleteByXdhClass(int id);

    int insertByXdhClass(XdhClass xdhClass);

    int updateByXdhClass(XdhClass xdhClass);

    XdhClass selectByClassName(String class_name);

    XdhClass selectClassByNameAndId(XdhClass xdhClass);
}
