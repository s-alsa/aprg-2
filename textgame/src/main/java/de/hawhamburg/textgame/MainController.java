package de.hawhamburg.textgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @GetMapping("/wouldyourather")
    public String wouldYouRather() { return "wouldyourather"; }

    @PostMapping("/wouldyourather")
    public String postLogin(Model model, @RequestParam("username2") String username2) {
        UserAccount newUser = new UserAccount(username2);
        usernameRepository.save(newUser);
        return "wouldyourather";
    }

    @PostMapping("/create1")
    public String characterCreation(Model model, @RequestParam("username") String username) {
        model.addAttribute("username", username);
        return "character";
    }

    @GetMapping("/create1")
    public String characterRecreation() { return "character"; }

    @GetMapping("/create2")
    public String characterBody() { return "body"; }

    @GetMapping("/create3")
    public String characterBottom() { return "bottom"; }

    @GetMapping("/profile")
    public String userProfile() { return "profile"; }

    @GetMapping("/results")
    public String resultScreen() {
        return "results";
    }

}
