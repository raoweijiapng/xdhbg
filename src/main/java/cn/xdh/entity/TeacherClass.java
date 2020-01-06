package cn.xdh.entity;

import javax.persistence.*;

// 通过教师和班级表来实现连个表相连的数据
@Entity
@Table(name="xdh_class")
@SecondaryTable(name="xdh_teacher")
public class TeacherClass {
    @Id
    @Column(table = "xdh_class", name = "id",insertable=false,updatable=false)
    private int id;
    @Column(table = "xdh_class",name="class_name")
    private String class_name;
    @Column(table = "xdh_class",name="add_time")
    private Long add_time;
    @Column(table = "xdh_class", name = "teacher_id")
    private int teacher_id;
    @Column(table = "xdh_class", name = "is_graduate")
    private int is_graduate;
    @Column(table = "xdh_teacher", name = "id",insertable=false,updatable=false)
    private int xdh_teacher_id;
    @Column(table = "xdh_teacher",name="name")
    private String name;

    public TeacherClass(int id,String class_name,Long add_time,int teacher_id,int is_graduate,int xdh_teacher_id,String name) {
        this.id = id;
        this.class_name = class_name;
        this.add_time = add_time;
        this.teacher_id = teacher_id;
        this.is_graduate = is_graduate;
        this.xdh_teacher_id = xdh_teacher_id;
        this.name = name;
    }

    public TeacherClass() {
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getXdh_teacher_id() {
        return xdh_teacher_id;
    }

    public void setXdh_teacher_id(int xdh_teacher_id) {
        this.xdh_teacher_id = xdh_teacher_id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public int getIs_graduate() {
        return is_graduate;
    }

    public void setIs_graduate(int is_graduate) {
        this.is_graduate = is_graduate;
    }

    @Override
    public String toString() {
        return "TeacherClass{" +
                "id=" + id +
                ", class_name='" + class_name + '\'' +
                ", add_time=" + add_time +
                ", teacher_id=" + teacher_id +
                ", is_graduate=" + is_graduate +
                ", xdh_teacher_id=" + xdh_teacher_id +
                ", name='" + name + '\'' +
                '}';
    }
}