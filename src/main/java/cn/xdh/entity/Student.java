package cn.xdh.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private int id;
    private String username;
    private String mobile;
    private String password;

    private long birthday;
    //入学时间
    private long join_study_time;
    //毕业时间
    private long graduate_time;
    private String graduate_school;
    private int province_id;
    private int city_id;
    private int area_id;
    private String address;

    private int class_id;
    private String progress;
    //毕业状态1毕业,0未毕业
    private int is_graduate;
    private String company;
    private Double salary;

    private int aim_province_id;
    private int aim_city_id;
    private int aim_area_id;
    private String aim_address;


    //0为女,1为男,-1未知,默认-1
    private int sex;
    private int age;

    private int stage_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public long getBirthday() {
        return birthday;
    }

    public void setBirthday(long birthday) {
        this.birthday = birthday;
    }

    public long getJoin_study_time() {
        return join_study_time;
    }

    public void setJoin_study_time(long join_study_time) {
        this.join_study_time = join_study_time;
    }

    public long getGraduate_time() {
        return graduate_time;
    }

    public void setGraduate_time(long graduate_time) {
        this.graduate_time = graduate_time;
    }

    public String getGraduate_school() {
        return graduate_school;
    }

    public void setGraduate_school(String graduate_school) {
        this.graduate_school = graduate_school;
    }

    public int getProvince_id() {
        return province_id;
    }

    public void setProvince_id(int province_id) {
        this.province_id = province_id;
    }

    public int getCity_id() {
        return city_id;
    }

    public void setCity_id(int city_id) {
        this.city_id = city_id;
    }

    public int getArea_id() {
        return area_id;
    }

    public void setArea_id(int area_id) {
        this.area_id = area_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public int getIs_graduate() {
        return is_graduate;
    }

    public void setIs_graduate(int is_graduate) {
        this.is_graduate = is_graduate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public int getAim_province_id() {
        return aim_province_id;
    }

    public void setAim_province_id(int aim_province_id) {
        this.aim_province_id = aim_province_id;
    }

    public int getAim_city_id() {
        return aim_city_id;
    }

    public void setAim_city_id(int aim_city_id) {
        this.aim_city_id = aim_city_id;
    }

    public int getAim_area_id() {
        return aim_area_id;
    }

    public void setAim_area_id(int aim_area_id) {
        this.aim_area_id = aim_area_id;
    }

    public String getAim_address() {
        return aim_address;
    }

    public void setAim_address(String aim_address) {
        this.aim_address = aim_address;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getStage_id() {
        return stage_id;
    }

    public void setStage_id(int stage_id) {
        this.stage_id = stage_id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", mobile='" + mobile + '\'' +
                ", password='" + password + '\'' +
                ", birthday=" + birthday +
                ", join_study_time=" + join_study_time +
                ", graduate_time=" + graduate_time +
                ", graduate_school='" + graduate_school + '\'' +
                ", province_id=" + province_id +
                ", city_id=" + city_id +
                ", area_id=" + area_id +
                ", address='" + address + '\'' +
                ", class_id=" + class_id +
                ", progress='" + progress + '\'' +
                ", is_graduate='" + is_graduate + '\'' +
                ", company='" + company + '\'' +
                ", salary=" + salary +
                ", aim_province_id=" + aim_province_id +
                ", aim_city_id=" + aim_city_id +
                ", aim_area_id=" + aim_area_id +
                ", aim_address='" + aim_address + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", stage_id=" + stage_id +
                '}';
    }
}
