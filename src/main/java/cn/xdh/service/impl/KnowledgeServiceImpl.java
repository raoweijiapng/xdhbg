package cn.xdh.service.impl;


import cn.xdh.dao.KnowledgeRepository;
import cn.xdh.entity.*;
import cn.xdh.service.KnowledgeService;
import cn.xdh.util.ExcelToObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
public class KnowledgeServiceImpl implements KnowledgeService {

    @Autowired
    private KnowledgeRepository knowledgerepository;

    //查找所有的知识点并分页
    @Override
    public Page<Knowledge> selectAllKnowledge(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Knowledge> knowledges = knowledgerepository.findAll(pageable);
        return  knowledges;
    }

    //根据知识点名称查找知识点并分页
    @Override
    public Page<Knowledge> getAllKnowledgeBy(int page, int size, final String lookname) {
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        final Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Knowledge> queryCondition = new Specification<Knowledge>() {
            @Override
            public Predicate toPredicate(Root<Knowledge> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查找条件
                Predicate p1 = cb.like(root.get("title").as(String.class), "%" + lookname + "%");
                return p1;
            }
        };
        Page<Knowledge> knowledges = knowledgerepository.findAll(queryCondition,pageable);
        return  knowledges;
    }

    //根据知识点查询是否存在
    @Override
    public List<Knowledge> selectknowledgeByTitle(String title){
        return knowledgerepository.getByTitle(title);
    }

    //增加知识点
    @Override
    public Knowledge addKnowledge(Knowledge knowledge){
        return  knowledgerepository.save(knowledge);
    }

    //批量增加知识点
    public Msg batchAddKnowledge(HttpServletRequest request, String suffixName, MultipartFile excelFile)  {
        List<Knowledge> list = null;
        Msg msg = new Msg();
        try {
            //从xml读取需要的数据
            if (".xls".equals(suffixName)){
                list = ExcelToObjectUtil.readKnowledge(excelFile);
            }else{
                list = ExcelToObjectUtil.readKnowledgeXlsx(excelFile);
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
            List<Knowledge> Knowledgelist = knowledgerepository.getByTitle(list.get(i).getTitle());
            //System.out.println(Knowledgelist);
            if (!Knowledgelist.isEmpty()) {
                msg.setMsg("知识点\"" + list.get(i).getTitle() + "\"已存在,添加失败,在他之前的已添加成功,请勿重复添加");
                return msg;
            } else {
                knowledgerepository.addKnowledge(list.get(i).getSubject_id(), list.get(i).getStage_id(), list.get(i).getTitle(), list.get(i).getAdd_time());
            }
            //System.out.println(list.get(i));
        }
        msg.setMsg("添加所有知识点成功");
        return msg;
    }


    //修改知识点
    @Override
    public int updateKnowledge(int subject_id,int stage_id,String title,Long add_time,int id){
        return  knowledgerepository.updateKnowledge(subject_id,stage_id,title,add_time,id);
    }

    //删除知识点
    @Override
    public int deleteKnowledge(int id) {
        return  knowledgerepository.deleteKnowledge(id);
    }



}
