package cn.xdh.service.impl;

import cn.xdh.dao.ClassDao;
import cn.xdh.dao.TeacherDao;
import cn.xdh.dao.XdhClassDao;
import cn.xdh.dao.XdhClassRepository;
import cn.xdh.entity.Teacher;
import cn.xdh.entity.TeacherClass;
import cn.xdh.entity.XdhClass;
import cn.xdh.service.ClassService;
import cn.xdh.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassDao classdao;
    //引入班级Dao接口
    @Autowired
    private XdhClassDao xdhClassDao;
    @Autowired
    private XdhClassRepository xdhclassrepository;
    
    //获取所有班级数量
    @Override
    public int selectAllNumber(){
        int number = classdao.selectAllNumber();
        return number;
    }

    //查询班级,并放入到list集合中
    @Override
    public List<XdhClass> selectByXdhClass() {
        List<XdhClass> selectByXdhClass = xdhClassDao.selectByXdhClass();
        return selectByXdhClass;
    }

    //通过id删除班级
    @Override
    public int deleteByXdhClass(int id) {
        int deleteByXdhClass = xdhClassDao.deleteByXdhClass(id);
        return deleteByXdhClass;
    }


    //添加班级
    @Override
    public int insertByXdhClass(XdhClass xdhClass) {
        XdhClass xdhClass1 = xdhClassDao.selectByClassName(xdhClass.getClass_name());
        int insertByXdhClass = 0;
        if (xdhClass1==null){
            insertByXdhClass = xdhClassDao.insertByXdhClass(xdhClass);
        }
        return insertByXdhClass;
    }

    //更新班级
    @Override
    public int updateByXdhClass(XdhClass xdhClass) {
        XdhClass xdhClass1 = xdhClassDao.selectClassByNameAndId(xdhClass);
        int updateByXdhClass = 0;
        if (xdhClass1 == null){
            updateByXdhClass = xdhClassDao.updateByXdhClass(xdhClass);
        }
        return updateByXdhClass;
    }

    @Override
    public Page<TeacherClass> selectAllXdhClass(int page, int size){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TeacherClass> xdhclasspage = xdhclassrepository.getAllTeacherClass(pageable);
        return xdhclasspage;
    }

    @Override
    public Page<TeacherClass> getAllTeacherClassBy(int page, int size, final String lookname){
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        /*final*/ Pageable pageable = PageRequest.of(page, size, sort);
        /*Specification<TeacherClass> queryCondition = new Specification<TeacherClass>() {
            @Override
            public Predicate toPredicate(Root<TeacherClass> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //查找条件
                Predicate p1 = cb.like(root.get("class_name").as(String.class), "%" + lookname + "%");
                Predicate p2 = cb.like(root.get("name").as(String.class), "%" + lookname + "%");
                Predicate p3 = cb.equal(root.get("id").as(Integer.class), root.get("xdh_teacher_id").as(Integer.class));
                return cb.and(p3,cb.or(p1,p2));
            }
        };*/
        Page<TeacherClass> xdhclasspage = xdhclassrepository.getAllTeacherClassBy(lookname,pageable);
        return xdhclasspage;
    }

    @Override
    public XdhClass selectByClassName(String class_name){
        XdhClass xdhclass = xdhClassDao.selectByClassName(class_name);
        return xdhclass;
    }

    @Override
    public XdhClass selectClassById(int id) {
        return classdao.selectClassById(id);
    }

}
