package cn.xdh.dao;

import cn.xdh.entity.Knowledge;
import cn.xdh.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface QuestionsRepository extends JpaSpecificationExecutor<Question>, JpaRepository<Question, Integer> {

    @Query(value = "select q.id,q.title,q.type_id,q.score,p.title from xdh_questions q,xdh_knowledges_point p where p.id = q.point_id order by q.add_time desc",nativeQuery = true)
    Page<Question> getAllQuestion(Pageable pageable);

    @Query(value = "select id,subject_id,stage_id,title,point_id,type_id,optionA,optionB,optionC,optionD,answer,score,add_time from xdh_questions where title = ?1",nativeQuery = true)
    List<Question> getByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "insert into xdh_questions(subject_id,stage_id,title,point_id,type_id,optionA,optionB,optionC,optionD,answer,score,add_time) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)",nativeQuery = true)
    int addQuestion(int subject_id,int stage_id,String title,int point_id,int type_id,String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time);

    @Query(value = "select id,subject_id,stage_id,title,point_id,type_id,optionA,optionB,optionC,optionD,answer,score,add_time from xdh_questions where id = ?1",nativeQuery = true)
    Question getById(int id);

    @Modifying
    @Transactional
    @Query(value = "update xdh_questions set title=?1,optionA=?2,optionB=?3,optionC=?4,optionD=?5,answer=?6,score=?7,add_time=?8 where id = ?9",nativeQuery = true)
    int updateChooseQuestionById(String title,String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time,int id);

    @Modifying
    @Transactional
    @Query(value = "update xdh_questions set title=?1,answer=?2,score=?3,add_time=?4 where id = ?5",nativeQuery = true)
    int updateWriterQuestionById(String title,String answer,int score,Long add_time,int id);

    @Modifying
    @Transactional
    @Query(value = "delete from xdh_questions where id=?1",nativeQuery = true)
    int deleteQuestionById(int id);

}
