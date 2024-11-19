package de.hawhamburg.textgame;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/start")
    public String startPage() {
        return "start";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }

    @PostMapping("/create1")
    public String characterCreation(Model model, @RequestParam("username") String username) {
        model.addAttribute("username", username);
        return "character";
    }

    @GetMapping("/create1")
    public String characterRecreation() {
        return "character";
    }

    @GetMapping("/create2")
    public String characterBody() { return "body"; }

    @GetMapping("/create3")
    public String characterBottom() { return "bottom"; }

    @GetMapping("/profile")
    public String userProfile() { return "profile"; }

    @GetMapping("/quiz")
    public String startGame() {
        return "quiz";
    }

    @GetMapping("/results")
    public String resultScreen() {
        return "results";
    }

    @GetMapping("/play")
    public String playground() {
        return "play";
    }

}
