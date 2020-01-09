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

    @Query(value = "select xdh_questions.id,xdh_questions.subject_id,xdh_questions.stage_id,xdh_questions.title title,xdh_questions.point_id,xdh_questions.type_id,xdh_questions.score,xdh_questions.add_time,xdh_questions.optionA,xdh_questions.optionB,xdh_questions.optionC,xdh_questions.optionD,xdh_questions.answer,xdh_knowledges_point.title xdh_knowledges_point_title from xdh_questions,xdh_knowledges_point where xdh_knowledges_point.id = xdh_questions.point_id order by xdh_questions.add_time desc",nativeQuery = true)
    Page<Question> getAllBy(Pageable pageable);


    @Query(value = "select xdh_questions.id,xdh_questions.subject_id,xdh_questions.stage_id,xdh_questions.title title,xdh_questions.point_id,xdh_questions.type_id,xdh_questions.score,xdh_questions.add_time,xdh_questions.optionA,xdh_questions.optionB,xdh_questions.optionC,xdh_questions.optionD,xdh_questions.answer,xdh_knowledges_point.title xdh_knowledges_point_title from xdh_questions,xdh_knowledges_point where xdh_knowledges_point.id = xdh_questions.point_id and xdh_questions.title like %?1% order by xdh_questions.add_time desc",nativeQuery = true)
    Page<Question> getAllByTitle(String title,Pageable pageable);

    @Query(value = "select xdh_questions.id,xdh_questions.subject_id,xdh_questions.stage_id,xdh_questions.title title,xdh_questions.point_id,xdh_questions.type_id,xdh_questions.score,xdh_questions.add_time,xdh_questions.optionA,xdh_questions.optionB,xdh_questions.optionC,xdh_questions.optionD,xdh_questions.answer,xdh_knowledges_point.title xdh_knowledges_point_title from xdh_questions,xdh_knowledges_point where xdh_knowledges_point.id = xdh_questions.point_id and xdh_questions.title = ?1",nativeQuery = true)
    List<Question> getByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "insert into xdh_questions(subject_id,stage_id,title,point_id,type_id,optionA,optionB,optionC,optionD,answer,score,add_time) values(?1,?2,?3,?4,?5,?6,?7,?8,?9,?10,?11,?12)",nativeQuery = true)
    int addQuestion(int subject_id,int stage_id,String title,int point_id,int type_id,String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time);

    @Query(value = "select xdh_questions.id,xdh_questions.subject_id,xdh_questions.stage_id,xdh_questions.title title,xdh_questions.point_id,xdh_questions.type_id,xdh_questions.score,xdh_questions.add_time,xdh_questions.optionA,xdh_questions.optionB,xdh_questions.optionC,xdh_questions.optionD,xdh_questions.answer,xdh_knowledges_point.title xdh_knowledges_point_title from xdh_questions,xdh_knowledges_point where xdh_knowledges_point.id = xdh_questions.point_id and xdh_questions.id = ?1",nativeQuery = true)
    Question getById(int id);

    @Modifying
    @Transactional
    @Query(value = "update xdh_questions set optionA=?1,optionB=?2,optionC=?3,optionD=?4,answer=?5,score=?6,add_time=?7 where id = ?8",nativeQuery = true)
    int updateChooseQuestionById(String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time,int id);

    @Modifying
    @Transactional
    @Query(value = "update xdh_questions set answer=?1,score=?2,add_time=?3 where id = ?4",nativeQuery = true)
    int updateWriterQuestionById(String answer,int score,Long add_time,int id);

    @Modifying
    @Transactional
    @Query(value = "delete from xdh_questions where id=?1",nativeQuery = true)
    int deleteQuestionById(int id);

}
