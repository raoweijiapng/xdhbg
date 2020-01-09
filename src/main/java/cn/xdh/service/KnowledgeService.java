package cn.xdh.service;

import cn.xdh.entity.Admin;
import cn.xdh.entity.AdminLog;
import cn.xdh.entity.Knowledge;
import cn.xdh.entity.TeacherLog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface KnowledgeService {

    //查找所有知识点并分页
    public Page<Knowledge> selectAllKnowledge(int page, int size);

    //根据知识点名称查找知识点并分页
    public Page<Knowledge> getAllKnowledgeBy(int page, int size, String lookname);

    //根据知识点查询是否存在
    public List<Knowledge> selectknowledgeByTitle(String title);

    //增加知识点
    public Knowledge addKnowledge(Knowledge knowledge);

    //修改知识点
    public int updateKnowledge(int subject_id,int stage_id,String title,Long add_time,int id);

    //删除知识点
    public int deleteKnowledge(int id);

    //根据id查询知识点
    public Knowledge getKnowledgeById(int id);
}
