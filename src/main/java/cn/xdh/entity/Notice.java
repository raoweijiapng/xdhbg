package cn.xdh.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Notice {
    private int id;
    private String notice;
    //    @JsonFormat(pattern="yyyy-MM-dd")
    private Long add_time;

    public Notice() {
    }


    public Notice(int id, String notice, Long add_time) {
        this.id = id;
        this.notice = notice;
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }


    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public Date getDateTime(){
        Long timeDateStamp = getAdd_time()*1000;
        return new Date(timeDateStamp);
    }

}
