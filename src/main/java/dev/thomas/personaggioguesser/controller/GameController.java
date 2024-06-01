package dev.thomas.personaggioguesser.controller;

import dev.thomas.personaggioguesser.service.OpenAiIntegrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    private OpenAiIntegrationService openAiIntegrationService;

    @GetMapping("/hint")
    public Map<String, String> getHint() {
        String hint = openAiIntegrationService.getHint();
        Map<String, String> response = new HashMap<>();
        response.put("hint", hint);
        return response;
    }

    @PostMapping("/guess")
    public Map<String, String> checkGuess(@RequestParam String guess) {
        Map<String, String> response = new HashMap<>();
        if (guess.equalsIgnoreCase(openAiIntegrationService.getCurrentCharacter())) {
            response.put("message", "Corretto hai indovinato");
            openAiIntegrationService.startNewGame();
        } else {
            String hint = openAiIntegrationService.getHint();
            response.put("message", "Errato! Ecco un nuovo inidizio: " + hint);
        }
        return response;
    }
}
