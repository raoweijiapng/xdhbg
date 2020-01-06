package cn.xdh.entity;

public class Admin {
    private int id;
    private String mobile;
    private String username;
    private String password;
    //状态,0为激活,1为未激活
    private int state;
    private int add_time;
    private String add_ip;

    public Admin() {
    }

    public Admin(int id, String mobile, String username, String password, int state, int add_time, String add_ip) {
        this.id = id;
        this.mobile = mobile;
        this.username = username;
        this.password = password;
        this.state = state;
        this.add_time = add_time;
        this.add_ip = add_ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getAdd_time() {
        return add_time;
    }

    public void setAdd_time(int add_time) {
        this.add_time = add_time;
    }

    public String getAdd_ip() {
        return add_ip;
    }

    public void setAdd_ip(String add_ip) {
        this.add_ip = add_ip;
    }
}
