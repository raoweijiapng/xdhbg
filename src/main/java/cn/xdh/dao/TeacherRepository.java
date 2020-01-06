package cn.xdh.dao;

import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaSpecificationExecutor<Teacher>, JpaRepository<Teacher, Integer> {


}