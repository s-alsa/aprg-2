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


    @PostMapping("/results")
    public String resultScreen(Model model, @RequestParam Map<String, String> selectedAnswers) {

        double mainstreamAnswers = 0;

        // Durchlaufe alle Fragen aus der Datenbank
        for (Question q : questionRepository.findAll()) {
            // Hole die Antworten des Nutzers
            String option1Value = selectedAnswers.get(q.getQuestionId() + "-opt1");
            String option2Value = selectedAnswers.get(q.getQuestionId() + "-opt2");

            // Erhöhe die Antwortzählung, basierend auf der Auswahl des Nutzers
            if (option1Value != null) {
                q.setAnswerCount1(q.getAnswerCount1() + 1);
            } else if (option2Value != null) {
                q.setAnswerCount2(q.getAnswerCount2() + 1);
            }

            // Speichere die aktualisierte Frage
            questionRepository.save(q);

            // Bestimme die populärste Antwort (maximale Antwortzahl)
            int maxAnswerCount = Math.max(q.getAnswerCount1(), q.getAnswerCount2());

            // Hole die vom Nutzer gewählte Antwort für diese Frage
            String selectedAnswer = selectedAnswers.get(q.getQuestionId().toString());
            int selectedAnswerCount = 0;

            // Bestimme, welche Antwort der Nutzer gewählt hat
            if (selectedAnswer != null) {
                if (selectedAnswer.equals(q.getOption1Id())) {
                    selectedAnswerCount = q.getAnswerCount1();
                } else if (selectedAnswer.equals(q.getOption2Id())) {
                    selectedAnswerCount = q.getAnswerCount2();
                }
            }

            // Überprüfe, ob die vom Nutzer gewählte Antwort mit der populärsten Antwort übereinstimmt
            if (selectedAnswerCount == maxAnswerCount) {
                mainstreamAnswers++; // Erhöhe den Zähler für populäre Antworten
            }
        }

        // Berechne den Prozentsatz
        int totalQuestions = questionRepository.findAll().size();
        double mainstreamMeter = 0;

        if (totalQuestions > 0) {
            mainstreamMeter = (mainstreamAnswers / totalQuestions) * 100;
        }

        // Füge den berechneten Prozentsatz zum Modell hinzu
        model.addAttribute("mainstreamMeter", mainstreamMeter);

        return "results";
    }

}
