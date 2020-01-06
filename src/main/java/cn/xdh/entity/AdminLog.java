package cn.xdh.entity;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="xdh_admin_log")
public class AdminLog implements Serializable {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="admin_id")
    private int admin_id;
    @Column(name="admin_name")
    private String admin_name;
    @Column(name="content")
    private String content;
    @Column(name="add_time")
    private Long add_time;
    @Column(name="add_ip")
    private String add_ip;

    public AdminLog() {
    }



    public AdminLog(int admin_id, String admin_name, String content, Long add_time, String add_ip) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.content = content;
        this.add_time = add_time;
        this.add_ip = add_ip;
    }

    public AdminLog(int id, int admin_id, String admin_name, String content, Long add_time, String add_ip) {
        this.id = id;
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.content = content;
        this.add_time = add_time;
        this.add_ip = add_ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
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

    public String getAdd_ip() {
        return add_ip;
    }

    public void setAdd_ip(String add_ip) {
        this.add_ip = add_ip;
    }
}
