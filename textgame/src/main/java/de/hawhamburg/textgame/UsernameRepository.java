package de.hawhamburg.textgame;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsernameRepository extends ListCrudRepository<QuizUser, Long> {



}
