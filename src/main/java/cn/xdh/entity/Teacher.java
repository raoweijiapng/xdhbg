package cn.xdh.entity;

import javax.persistence.Column;
import javax.persistence.*;

@Entity
@Table(name="xdh_teacher")
public class Teacher {
    @Id
    @Column(name="id")
    private Integer id;
    @Column(name="name")
    private String name;
    @Column(name="mobile")
    private String mobile;
    @Column(name="password")
    private String password;
    @Column(name="add_time")
    private Long add_time;
    @Column(name="add_ip")
    private String add_ip;

    public Teacher() {
    }

    public Teacher(int id, String name, String mobile, String password, Long add_time, String add_ip) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.password = password;
        this.add_time = add_time;
        this.add_ip = add_ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", add_time=" + add_time +
                ", add_ip='" + add_ip + '\'' +
                '}';
    }
}
