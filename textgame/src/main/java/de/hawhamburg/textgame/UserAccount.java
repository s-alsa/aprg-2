package de.hawhamburg.textgame;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERACCOUNTS")
public class UserAccount {

    @Id
    private Long id;

    private String username;

    UserAccount(String username){
        this.username = username;
    }

}
