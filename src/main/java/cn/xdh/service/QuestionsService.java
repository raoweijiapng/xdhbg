package cn.xdh.service;

import cn.xdh.entity.Knowledge;
import cn.xdh.entity.Question;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuestionsService {

    //查找所有试题并分页
    public Page<Question> getAllQuestion(int page, int size);

    //根据subject_id和stage_id查询知识点
    public List<Knowledge> findBySubject_idAndStage_id(int subject_id,int stage_id);

    //增加试题
    public Question addQuestion(Question question);

    //根据试题名查询是否存在
    public List<Question> selectQuestionByTitle(String title);

    //根据试题名称查找知识点并分页
    public Page<Question> getAllQuestionBy(int page, int size, String lookname);

    //根据Id查询试题
    public Question getQuestionById(int id);

    //修改选择题
    public int updateChooseQuestion(String title,String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time,int id);

    //修改简答题
    public int updateWriterQuestion(String title,String answer,int score,Long add_time,int id);


    //删除试题
    public int deleteQuestionById(int id);


}
