package cn.xdh.entity;

import java.util.Date;
import java.util.Objects;

/**
 * @author TZB
 */
public class Exeperience {
    private int id;
    private int student_id;
    private String content;
    private long add_time;
    private String username;
    private String class_name;

    public Exeperience(int id, int student_id, String content, long add_time, String username, String class_name) {
        this.id = id;
        this.student_id = student_id;
        this.content = content;
        this.add_time = add_time;
        this.username = username;
        this.class_name = class_name;
    }

    public Exeperience() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Exeperience that = (Exeperience) o;
        return id == that.id &&
                student_id == that.student_id &&
                add_time == that.add_time &&
                Objects.equals(content, that.content) &&
                Objects.equals(username, that.username) &&
                Objects.equals(class_name, that.class_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student_id, content, add_time, username, class_name);
    }

    public Date getDateTime(){
        Long l = getAdd_time()*1000;
        return new Date(l);
    }
}
