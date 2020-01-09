package cn.xdh.service.impl;

import cn.xdh.dao.KnowledgeRepository;
import cn.xdh.dao.QuestionsRepository;
import cn.xdh.entity.Knowledge;
import cn.xdh.entity.Msg;
import cn.xdh.entity.Question;
import cn.xdh.service.QuestionsService;
import cn.xdh.util.ExcelToObjectUtil;
import com.mysql.cj.xdevapi.Session;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.hibernate.HikariConnectionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@Service
public class QuestionsServiceImpl implements QuestionsService {
    @Autowired
    private QuestionsRepository questionsrepository;
    @Autowired
    private KnowledgeRepository knowledgerepository;

    //查找所有的试题并分页
    @Override
    public Page<Question> getAllQuestion(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Question> questions = questionsrepository.findAll(pageable);
        return  questions;
    }

    //根据subject_id和stage_id查询知识点
    @Override
    public List<Knowledge> findBySubject_idAndStage_id(int subject_id,int stage_id){
        return knowledgerepository.getAllBySubject_idAndStage_id(subject_id,stage_id);
    }

    //增加试题
    @Override
    public Question addQuestion(Question question){
        return  questionsrepository.saveAndFlush(question);
    }

    //根据试题名查询是否存在
    @Override
    public List<Question> selectQuestionByTitle(String title){
        return questionsrepository.getByTitle(title);
    }

    //批量增加试题
    public Msg batchAddQuestion(HttpServletRequest request, String suffixName, MultipartFile excelFile)  {
        List<Question> list = null;
        Msg msg = new Msg();
        try {
            //从xml读取需要的数据
            if (".xls".equals(suffixName)){
                list = ExcelToObjectUtil.readQuestion(excelFile);
            }else{
                list = ExcelToObjectUtil.readQuestionXlsx(excelFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (list.size()==0 ){
            msg.setMsg("数据为空");
            return msg;
        }

        //System.out.println(list);
        for (int i=0; i < list.size(); i++) {
            List<Question> Questionlist = questionsrepository.getByTitle(list.get(i).getTitle());
            //System.out.println(Questionlist);
            if (!Questionlist.isEmpty()) {
                msg.setMsg("试题\"" + list.get(i).getTitle() + "\"已存在,添加失败,在他之前的已添加成功,请勿重复添加");
                return msg;
            }
            questionsrepository.addQuestion(
                    list.get(i).getSubject_id(),list.get(i).getStage_id(),list.get(i).getTitle(),
                    list.get(i).getPoint_id(),list.get(i).getType_id(),list.get(i).getOptionA(),list.get(i).getOptionB(),
                    list.get(i).getOptionC(),list.get(i).getOptionD(),list.get(i).getAnswer(),list.get(i).getScore(),list.get(i).getAdd_time());
        }
        msg.setMsg("添加所有试题成功");
        return msg;
    }

    //根据知识点名称查找知识点并分页
    @Override
    public Page<Question> getAllQuestionBy(int page, int size, final String lookname) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        final Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Question> queryCondition = new Specification<Question>() {
            @Override
            public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查找条件
                Predicate p1 = cb.like(root.get("title").as(String.class), "%" + lookname + "%");
                return p1;
            }
        };
        Page<Question> questions = questionsrepository.findAll(queryCondition,pageable);
        return  questions;
    }

    //根据Id查询试题
    public Question getQuestionById(int id){
        return  questionsrepository.getById(id);
    }


    //修改试题
    @Override
    public int updateChooseQuestion(String title,String optionA,String optionB,String optionC,String optionD,String answer,int score,Long add_time,int id){
        return  questionsrepository.updateChooseQuestionById(title,optionA,optionB,optionC,optionD,answer,score,add_time,id);
    }

    //修改简答题
    @Override
    public int updateWriterQuestion(String title,String answer,int score,Long add_time,int id){
        return  questionsrepository.updateWriterQuestionById(title,answer,score,add_time,id);
    }

    //删除试题
    @Override
    public int deleteQuestionById(int id){
        return  questionsrepository.deleteQuestionById(id);
    }


}
