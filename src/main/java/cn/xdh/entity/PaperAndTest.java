package cn.xdh.entity;

public class PaperAndTest {
    private int id;
    private int teacher_id;
    private String name;
    private int class_id;
    //选择题分数
    private int choice_qustion_score;
    //简答题分数
    private int short_answer_score;
    //总分
    private int total_score;
    
    //1未开始 2正在 3已完成,在成绩页面中只需要3
    private int state;
    //考试时间
    private Long test_time;
    //开始时间
    private Long start_time;
    //结束时间
    private Long end_time;
    
    private int student_id;
    //交卷时间
    private Long hand_time;
    //分数
    private int score;
    //试卷id
    private int test_id;
    //批卷时间 0未批阅 >0已批阅,在成绩页面中只需>0
    private long marking_time;

    public PaperAndTest() {
    }

    public PaperAndTest(int id, int teacher_id, String name, int class_id, int choice_qustion_score, int short_answer_score, int total_score, int state, Long test_time, Long start_time, Long end_time, int student_id, Long hand_time, int score, int test_id, long marking_time) {
        this.id = id;
        this.teacher_id = teacher_id;
        this.name = name;
        this.class_id = class_id;
        this.choice_qustion_score = choice_qustion_score;
        this.short_answer_score = short_answer_score;
        this.total_score = total_score;
        this.state = state;
        this.test_time = test_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.student_id = student_id;
        this.hand_time = hand_time;
        this.score = score;
        this.test_id = test_id;
        this.marking_time = marking_time;
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

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getChoice_qustion_score() {
        return choice_qustion_score;
    }

    public void setChoice_qustion_score(int choice_qustion_score) {
        this.choice_qustion_score = choice_qustion_score;
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

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Long getHand_time() {
        return hand_time;
    }

    public void setHand_time(Long hand_time) {
        this.hand_time = hand_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public long getMarking_time() {
        return marking_time;
    }

    public void setMarking_time(long marking_time) {
        this.marking_time = marking_time;
    }

    @Override
    public String toString() {
        return "PaperAndTest{" +
                "id=" + id +
                ", teacher_id=" + teacher_id +
                ", name='" + name + '\'' +
                ", class_id=" + class_id +
                ", choice_qustion_score=" + choice_qustion_score +
                ", short_answer_score=" + short_answer_score +
                ", total_score=" + total_score +
                ", state=" + state +
                ", test_time=" + test_time +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", student_id=" + student_id +
                ", hand_time=" + hand_time +
                ", score=" + score +
                ", test_id=" + test_id +
                ", marking_time=" + marking_time +
                '}';
    }
}
