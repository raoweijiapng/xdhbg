package cn.xdh.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @ProjectName: xdhbg
 * @Package: cn.xdh.entity
 * @ClassName: Experience
 * @Author: 81394
 * @Description: 心得
 * @Date: 2019/12/27 21:51
 * @Version: 1.0
 */
public class Experience {
    private int id;
    private int Student_id;
    private String content;
    private Long add_time;

    public Experience() {
    }

    public Experience(int id, int student_id, String content, Long add_time) {
        this.id = id;
        Student_id = student_id;
        this.content = content;
        this.add_time = add_time;
    }

    public Experience(int student_id, String content, Long add_time) {
        Student_id = student_id;
        this.content = content;
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return Student_id;
    }

    public void setStudent_id(int student_id) {
        Student_id = student_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public Date getDateTime(){
        Long l = add_time*1000;
        return new Date(l);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return id == that.id &&
                Student_id == that.Student_id &&
                Objects.equals(content, that.content) &&
                Objects.equals(add_time, that.add_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, Student_id, content, add_time);
    }
}
