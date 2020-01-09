package cn.xdh.entity;

import javax.persistence.*;

@Entity
@Table(name="xdh_questions")
@SecondaryTable(name="xdh_knowledges_point")
public class Question {
    @Id
    @Column(table = "xdh_questions",name = "id",insertable=false,updatable=false)
    private int id;
    @Column(table = "xdh_questions",name = "title")
    private String title;
    @Column(table = "xdh_questions",name = "subject_id")
    private int subject_id;
    @Column(table = "xdh_questions",name = "stage_id")
    private int stage_id;
    @Column(table = "xdh_questions",name = "point_id")
    private int point_id;
    @Column(table = "xdh_questions",name = "type_id")
    private int type_id;
    @Column(table = "xdh_questions",name = "optionA")
    private String optionA;
    @Column(table = "xdh_questions",name = "optionB")
    private String optionB;
    @Column(table = "xdh_questions",name = "optionC")
    private String optionC;
    @Column(table = "xdh_questions",name = "optionD")
    private String optionD;
    @Column(table = "xdh_questions",name = "answer")
    private String answer;
    @Column(table = "xdh_questions",name = "score")
    private int score;
    @Column(table = "xdh_questions",name = "add_time")
    private Long add_time;

    @Column(table = "xdh_knowledges_point",name = "id",insertable=false,updatable=false)
    private Integer xdh_knowledges_point_id;
    @Column(table = "xdh_knowledges_point",name = "title")
    private String xdh_knowledges_point_title;

    public Question() {
    }

    public Question(int id, String title, int type_id, int score, String xdh_knowledges_point_title) {
        this.id = id;
        this.title = title;
        this.type_id = type_id;
        this.score = score;
        this.xdh_knowledges_point_title = xdh_knowledges_point_title;
    }



    public Question(int id, int subject_id, int stage_id, int point_id, int type_id, String optionA, String optionB, String optionC, String optionD, String answer, int score, Long add_time) {
        this.id = id;
        this.subject_id = subject_id;
        this.stage_id = stage_id;
        this.point_id = point_id;
        this.type_id = type_id;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
        this.score = score;
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

    public int getPoint_id() {
        return point_id;
    }

    public void setPoint_id(int point_id) {
        this.point_id = point_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getOptionA() {
        return optionA;
    }

    public void setOptionA(String optionA) {
        this.optionA = optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public void setOptionB(String optionB) {
        this.optionB = optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public void setOptionC(String optionC) {
        this.optionC = optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public void setOptionD(String optionD) {
        this.optionD = optionD;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    public int getXdh_knowledges_point_id() {
        return xdh_knowledges_point_id;
    }

    public void setXdh_knowledges_point_id(int xdh_knowledges_point_id) {
        this.xdh_knowledges_point_id = xdh_knowledges_point_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getXdh_knowledges_point_title() {
        return xdh_knowledges_point_title;
    }

    public void setXdh_knowledges_point_title(String xdh_knowledges_point_title) {
        this.xdh_knowledges_point_title = xdh_knowledges_point_title;
    }

}
