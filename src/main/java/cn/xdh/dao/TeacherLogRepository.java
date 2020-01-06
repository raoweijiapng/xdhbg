package cn.xdh.dao;

import cn.xdh.entity.AdminLog;
import cn.xdh.entity.TeacherLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TeacherLogRepository extends JpaSpecificationExecutor<TeacherLog>,JpaRepository<TeacherLog, Integer> {


}
