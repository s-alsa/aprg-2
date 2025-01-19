package de.hawhamburg.textgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("QUESTIONS")
public class Question {

    @Id
    private Long id;

    private String option1;
    private String option2;

    private String option1_id;
    private String option2_id;

    Question(String option1, String option2, String option1_id, String option2_id) {
        this.option1 = option1;
        this.option2 = option2;
        this.option1_id = option1_id;
        this.option2_id = option2_id;
    }




}
