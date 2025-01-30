package de.hawhamburg.textgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("QUESTIONS")
public class Question {

    @Id
    private Long question_id;

    private String option1;
    private String option2;

    private String option1_id;
    private String option2_id;

    private int answerCount1;
    private int answerCount2;

    Question(String option1, String option2, String option1_id, String option2_id) {
        this.option1 = option1;
        this.option2 = option2;
        this.option1_id = option1_id;
        this.option2_id = option2_id;
        this.answerCount1 = answerCount1;
        this.answerCount2 = answerCount2;
    }

    public Long getQuestionId() {
        return question_id;
    }

    public String getOption1Id() {
        return option1_id;
    }

    public String getOption2Id() {
        return option2_id;
    }

    public int getAnswerCount1() {
        return answerCount1;
    }

    public int getAnswerCount2() {
        return answerCount2;
    }

    public void setAnswerCount1(int answerCount1) {
        this.answerCount1 = answerCount1;
    }

    public void setAnswerCount2(int answerCount2) {
        this.answerCount2 = answerCount2;
    }

    public int getMaxAnswerCount(int ac1, int ac2) {
        int maxAnswerCount = Math.max(ac1, ac2);
        return maxAnswerCount;
    }

}