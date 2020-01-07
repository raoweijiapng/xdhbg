package cn.xdh.entity;
//试卷表
public class StudentTestPaper {
    private int id;
    private int teacher_id;
    private String name;
    //关联学科
    private int subject_id;
    private int class_id;
    //选择题分数
    private int choice_question_score;
    //简答题分数
    private int short_answer_score;
    //总分
    private int total_score;
    //试卷状态 1未开始 2正在 3已完成
    private int state;
    //考试时间
    private Long test_time;
    //开始时间
    private Long start_time;
    //结束时间
    private Long end_time;
    //发布状态 0未发布 1已发布
    private int is_publish;
    //创建时间
    private Long add_time;

    public StudentTestPaper() {
    }

    public StudentTestPaper(int id, int teacher_id, String name, int subject_id, int class_id, int choice_question_score, int short_answer_score, int total_score, int state, Long test_time, Long start_time, Long end_time, int is_publish, Long add_time) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.name = name;
        this.subject_id = subject_id;
        this.class_id = class_id;
        this.choice_question_score = choice_question_score;
        this.short_answer_score = short_answer_score;
        this.total_score = total_score;
        this.state = state;
        this.test_time = test_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.is_publish = is_publish;
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacher_id() {
        return teacher_id;
    }

    public void setTeacher_id(int teacher_id) {
        this.teacher_id = teacher_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getChoice_question_score() {
        return choice_question_score;
    }

    public void setChoice_question_score(int choice_question_score) {
        this.choice_question_score = choice_question_score;
    }

    public int getShort_answer_score() {
        return short_answer_score;
    }

    public void setShort_answer_score(int short_answer_score) {
        this.short_answer_score = short_answer_score;
    }

    public int getTotal_score() {
        return total_score;
    }

    public void setTotal_score(int total_score) {
        this.total_score = total_score;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Long getTest_time() {
        return test_time;
    }

    public void setTest_time(Long test_time) {
        this.test_time = test_time;
    }

    public Long getStart_time() {
        return start_time;
    }

    public void setStart_time(Long start_time) {
        this.start_time = start_time;
    }

    public Long getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Long end_time) {
        this.end_time = end_time;
    }

    public int getIs_publish() {
        return is_publish;
    }

    public void setIs_publish(int is_publish) {
        this.is_publish = is_publish;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "TestPaper{" +
                "id=" + id +
                ", teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", subject_id=" + subject_id +
                ", class_id=" + class_id +
                ", choice_question_score=" + choice_question_score +
                ", short_answer_score=" + short_answer_score +
                ", total_score=" + total_score +
                ", state=" + state +
                ", test_time=" + test_time +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", is_publish=" + is_publish +
                ", add_time=" + add_time +
                '}';
    }
    
}
