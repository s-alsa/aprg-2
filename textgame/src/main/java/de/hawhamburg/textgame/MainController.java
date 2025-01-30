package de.hawhamburg.textgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private QuestionRepository questionRepository;

    MainController(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @GetMapping("/")
    public String startPage() {
        return "start";
    }

    @GetMapping("/wouldyourather")
    public String displayQuestions(Model model) {
        List<Question> questions = questionRepository.findAll();
        model.addAttribute("questions", questions);
        return "wouldyourather";
    }

    @PostMapping("/results")
    public String resultScreen(Model model, @RequestParam Map<String, String> selectedAnswers) {

        double mainstreamAnswers = 0;

        for (Question q : questionRepository.findAll()) {

            String selectedAnswer = selectedAnswers.get(q.getQuestionId() + "-answer");

            int maxAnswerCount = Math.max(q.getAnswerCount1(), q.getAnswerCount2());
            int selectedAnswerCount = 0;

            if (selectedAnswer != null) {
                if (selectedAnswer.equals(q.getOption1Id())) {
                    selectedAnswerCount = q.getAnswerCount1();
                    q.setAnswerCount1(q.getAnswerCount1() + 1);
                } else if (selectedAnswer.equals(q.getOption2Id())) {
                    selectedAnswerCount = q.getAnswerCount2();
                    q.setAnswerCount2(q.getAnswerCount2() + 1);
                }

                if (selectedAnswerCount == maxAnswerCount) {
                    mainstreamAnswers += 1;
                }
            }
            questionRepository.save(q);
        }

        int totalQuestions = questionRepository.findAll().size();
        double mainstreamMeter = (mainstreamAnswers / totalQuestions) * 100;

        int mainstreamInt = (int) mainstreamMeter;
        model.addAttribute("mainstreamMeter", mainstreamInt);

        return "results";
    }

    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    @GetMapping("/secretgame")
    public String secretgamePage() {
        return "secretgame";
    }

}
