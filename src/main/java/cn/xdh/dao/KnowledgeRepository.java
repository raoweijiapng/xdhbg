package cn.xdh.dao;

import cn.xdh.entity.Knowledge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface KnowledgeRepository extends JpaSpecificationExecutor<Knowledge>, JpaRepository<Knowledge, Integer> {

    @Modifying
    @Query(value = "select xdh_knowledges_point.subject_id,xdh_knowledges_point.stage_id,xdh_knowledges_point.title,xdh_knowledges_point.add_time from xdh_knowledges_point where title = ?1",nativeQuery = true)
    List<Knowledge> getByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "insert into xdh_knowledges_point(subject_id,stage_id,title,add_time) values(?1,?2,?3,?4)",nativeQuery = true)
    int addKnowledge(int subject_id,int stage_id,String title,Long add_time);

    @Modifying
    @Transactional
    @Query(value = "update xdh_knowledges_point set subject_id=?1,stage_id=?2,title=?3,add_time=?4 where id=?5",nativeQuery = true)
    int updateKnowledge(int subject_id,int stage_id,String title,Long add_time,int id);

    @Modifying
    @Transactional
    @Query(value = "delete from xdh_knowledges_point where id=?1",nativeQuery = true)
    int deleteKnowledge(int id);

}
