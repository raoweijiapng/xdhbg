package cn.xdh.entity;
//参加考试表
public class AttendTest {
    private int id;
    private int student_id;
    private int class_id;
    //考试时间
    private Long test_time;
    //交卷时间
    private Long hand_time;
    //选择题分数
    private int choice_question_score;
    //简答题分数
    private int short_question_score;
    //分数
    private int score;
    //存储秒数
    private Long used_time;
    //试卷id
    private int test_id;
    //批卷时间 0未批阅 >0已批阅
    private Long marking_time;
    //添加时间
    private Long add_time;

    public AttendTest() {
    }

    public AttendTest(int id, int student_id, int class_id, Long test_time, Long hand_time, int choice_question_score, int short_question_score, int score, Long used_time, int test_id, Long marking_time, Long add_time) {
        this.id = id;
        this.student_id = student_id;
        this.class_id = class_id;
        this.test_time = test_time;
        this.hand_time = hand_time;
        this.choice_question_score = choice_question_score;
        this.short_question_score = short_question_score;
        this.score = score;
        this.used_time = used_time;
        this.test_id = test_id;
        this.marking_time = marking_time;
        this.add_time = add_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public Long getTest_time() {
        return test_time;
    }

    public void setTest_time(Long test_time) {
        this.test_time = test_time;
    }

    public Long getHand_time() {
        return hand_time;
    }

    public void setHand_time(Long hand_time) {
        this.hand_time = hand_time;
    }

    public int getChoice_question_score() {
        return choice_question_score;
    }

    public void setChoice_question_score(int choice_question_score) {
        this.choice_question_score = choice_question_score;
    }

    public int getShort_question_score() {
        return short_question_score;
    }

    public void setShort_question_score(int short_question_score) {
        this.short_question_score = short_question_score;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Long getUsed_time() {
        return used_time;
    }

    public void setUsed_time(Long used_time) {
        this.used_time = used_time;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    public Long getMarking_time() {
        return marking_time;
    }

    public void setMarking_time(Long marking_time) {
        this.marking_time = marking_time;
    }

    public Long getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Long add_time) {
        this.add_time = add_time;
    }

    @Override
    public String toString() {
        return "AttendTest{" +
                "id=" + id +
                ", student_id=" + student_id +
                ", class_id=" + class_id +
                ", test_time=" + test_time +
                ", hand_time=" + hand_time +
                ", choice_question_score=" + choice_question_score +
                ", short_question_score=" + short_question_score +
                ", score=" + score +
                ", used_time=" + used_time +
                ", test_id=" + test_id +
                ", marking_time=" + marking_time +
                ", add_time=" + add_time +
                '}';
    }
}
