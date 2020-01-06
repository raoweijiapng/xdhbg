package cn.xdh.entity;

import java.io.Serializable;

/**
 * @author victor
 * @site https://victorfengming.github.io/
 * @company XDL
 * @project xdh
 * @package cn.xdh.entity
 * @created 2019-12-27 15:30
 * @function ""
 */
public class NoticeData implements Serializable {
    //公告内容
    private String notice;
    //添加时间
    private Long add_time;
    //id
    private Integer notice_id;

    public void setId(Integer notice_id) {
        this.notice_id = notice_id;
    }

    public Integer getId() {
        return notice_id;
    }

    public NoticeData() {
    }

    public NoticeData(String notice, Long add_time, Integer notice_id) {
        this.notice = notice;
        this.add_time = add_time;
        this.notice_id = notice_id;
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
}
