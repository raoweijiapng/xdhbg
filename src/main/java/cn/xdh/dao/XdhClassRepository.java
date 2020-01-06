package cn.xdh.dao;

import cn.xdh.entity.TeacherClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface XdhClassRepository extends JpaSpecificationExecutor<TeacherClass>, JpaRepository<TeacherClass, Integer> {
    
   
    @Query(nativeQuery = true, value = "select xdh_class.class_name ,xdh_teacher.name,xdh_class.add_time,xdh_class.teacher_id,xdh_class.id,xdh_class.is_graduate from xdh_teacher,xdh_class where xdh_class.teacher_id = xdh_teacher.id")
    Page<TeacherClass> getAllTeacherClass(Pageable pageable);

    @Query(nativeQuery = true, value = "select t.* from (select xdh_class.class_name ,xdh_teacher.name,xdh_class.add_time,xdh_class.teacher_id,xdh_class.id,xdh_class.is_graduate from xdh_teacher,xdh_class where xdh_class.teacher_id = xdh_teacher.id) as t where t.class_name like %?1% or t.name like %?1%")
    Page<TeacherClass> getAllTeacherClassBy(String lookname,Pageable pageable);
}
