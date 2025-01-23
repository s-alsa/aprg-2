package de.hawhamburg.textgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERNAMES")
public class QuizUser {

    @Id
    private Long user_id;

    private String username;

    QuizUser(String username){
        this.username = username;
    }

}

