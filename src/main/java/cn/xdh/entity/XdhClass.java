package cn.xdh.entity;

import java.io.Serializable;
import java.util.Date;

public class XdhClass implements Serializable {
    private int id;
    private String class_name;
    // 开班时间
    private Long add_time;
    private int teacher_id;
    // 判断是否毕业 1.毕业 0.未毕业
    private int is_graduate;

    public XdhClass() {
    }

    public XdhClass(int id, String class_name, Long add_time, int teacher_id, int is_graduate) {
        this.id = id;
        this.class_name = class_name;
        this.add_time = add_time;
        this.teacher_id = teacher_id;
        this.is_graduate = is_graduate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
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
        return "XdhClass{" +
                "id=" + id +
                ", class_name='" + class_name + '\'' +
                ", add_time=" + add_time +
                ", teacher_id=" + teacher_id +
                ", is_graduate=" + is_graduate +
                '}';
    }
}
