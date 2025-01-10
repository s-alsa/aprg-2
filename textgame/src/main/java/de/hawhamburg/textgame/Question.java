package de.hawhamburg.textgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("QUESTIONS")
public class Question {

    @Id
    private Long id;

    private String option1;
    private String option2;

    private String option1Id;
    private String option2Id;

    Question(String option1, String option2, String option1Id, String option2Id) {
        this.option1 = option1;
        this.option2 = option2;
        this.option1Id = option1Id;
        this.option2Id = option2Id;
    }



}
