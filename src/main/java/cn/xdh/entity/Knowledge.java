package cn.xdh.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="xdh_knowledges_point")
public class Knowledge {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "subject_id")
    private int subject_id;
    @Column(name = "stage_id")
    private int stage_id;
    @Column(name = "title")
    private String title;
    @Column(name = "add_time")
    private Long add_time;

    public Knowledge() {
    }

    public Knowledge(int subject_id,int stage_id,String title,Long add_time) {
        this.subject_id = subject_id;
        this.stage_id = stage_id;
        this.title = title;
        this.add_time = add_time;
    }

    public Knowledge(int id,int subject_id,int stage_id,String title,Long add_time) {
        this.id = id;
        this.subject_id = subject_id;
        this.stage_id = stage_id;
        this.title = title;
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getStage_id() {
        return stage_id;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "Knowledge{" +
                "id=" + id +
                ", subject_id=" + subject_id +
                ", stage_id=" + stage_id +
                ", title='" + title + '\'' +
                ", add_time=" + add_time +
                '}';
    }
}
