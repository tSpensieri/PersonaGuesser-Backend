package dev.thomas.personaggioguesser.service;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class OpenAiIntegrationService {

    private final OpenAiChatModel chatModel;
    private final List<String> characters = List.of(
            "Alan Turing"
    );
    private String currentCharacter;
    private int step;

    @Autowired
    public OpenAiIntegrationService(OpenAiChatModel chatModel) {
        this.chatModel = chatModel;
        startNewGame();
    }

    public void startNewGame() {
        Random random = new Random();
        currentCharacter = characters.get(random.nextInt(characters.size()));
        step = 1;
    }

    public String getHint() {
        String promptText = "Fornisci un indizio per indovinare il seguente personaggio famoso: " + currentCharacter + ". Questo è l'indizio numero " + step + ".";
        Prompt prompt = new Prompt(promptText);
        step++;
        return chatModel.call(prompt).getResults().get(0).getOutput().getContent();
    }

    public String getCurrentCharacter() {
        return currentCharacter;
    }
}
