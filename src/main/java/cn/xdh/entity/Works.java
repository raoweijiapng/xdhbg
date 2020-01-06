package cn.xdh.entity;



import java.io.Serializable;
import java.util.Objects;

/**
 * @author TZB
 */
public class Works implements Serializable {
    private int id;
    private int student_id;
    private String name;
    private String url;
    private long add_time;


    public Works() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(long add_time) {
        this.add_time = add_time;
    }

    public Works(int id, int student_id, String name, String url, int add_time) {
        this.id = id;
        this.student_id = student_id;
        this.name = name;
        this.url = url;
        this.add_time = add_time;
    }
    public Works(int id, int student_id, String name, String url, int add_time, String time) {
        this.id = id;
        this.student_id = student_id;
        this.name = name;
        this.url = url;
        this.add_time = add_time;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Works)) {return false;}
        Works works = (Works) o;
        return getId() == works.getId() &&
                getStudent_id() == works.getStudent_id() &&
                getAdd_time() == works.getAdd_time() &&
                getName().equals(works.getName()) &&
                getUrl().equals(works.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStudent_id(), getName(), getUrl(), getAdd_time());
    }
}
