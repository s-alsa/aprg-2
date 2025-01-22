package de.hawhamburg.textgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UsernameRepository usernameRepository;
    @Autowired
    private QuestionRepository questionRepository;

    MainController(UsernameRepository usernameRepository, QuestionRepository questionRepository) {
        this.usernameRepository = usernameRepository;
        this.questionRepository = questionRepository;
    }

    @GetMapping("/")
    public String startPage() {
        return "start";
    }

    @GetMapping("/welcome")
    public String welcomePage() {
        return "welcome";
    }

    @GetMapping("/wouldyourather")
    public String displayQuestions(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "wouldyourather";
    }

    @PostMapping("/wouldyouratherPost")
    public String postLogin(Model model, @RequestParam("username") String username) {
        QuizUser newUser = new QuizUser(username);
        usernameRepository.save(newUser);

        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);

        return "wouldyourather";
    }

    @GetMapping("/results")
    public String resultScreen() {
        return "results";
    }

}
