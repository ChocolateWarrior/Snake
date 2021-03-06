package services;

import entities.Direction;
import views.Display;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameService {

    //    private static final String UPPERCASE_LINE = " ‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾‾";
    private static final String UPPERCASE_LINE = " ________________";
    private static final String UNDERSCORE_LINE = " ________________";
    private static final String TYPE_TO_EXIT = " Type \"q\" to exit";

    private static final String LOST_MESSAGE = "You have lost!";
    private static final String EXIT_CHARACTER = "q";

    private Display display;
    private RenderService renderService;
    private SnakeService snakeService;
    private SpriteService spriteService;
    private BufferedReader bufferedReader;

    public GameService(Display display,
                       RenderService renderService,
                       SnakeService snakeService,
                       SpriteService spriteService) {
        this.display = display;
        this.renderService = renderService;
        this.snakeService = snakeService;
        this.spriteService = spriteService;
        this.bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    public void play() throws IOException{
        showInitialGameState();
        playWhileNotInterrupted(bufferedReader.readLine());
    }

    private void playWhileNotInterrupted(String userInput) throws IOException {

        while (!userInput.equals(EXIT_CHARACTER)) {
            update(Direction.valueOf(userInput.toUpperCase()));
            showInterfaceBottom();
            if (snakeService.hasLost()){
                display.viewAsNewLine(LOST_MESSAGE);
                return;
            }
            userInput = bufferedReader.readLine();
        }
    }

    private void showInterfaceBottom() {
        display.viewAsNewLine(UPPERCASE_LINE);
        display.viewAsNewLine(TYPE_TO_EXIT);
    }

    private void showInitialGameState(){
        display.flush();
        spriteService.spawnNewSprite();
        renderService.render();
        display.viewAsNewLine(UNDERSCORE_LINE);
    }

    private void update(Direction direction) {
        snakeService.updateSnakeSituation(direction);
        display.viewAsNewLine(UNDERSCORE_LINE);
        display.flush();
        renderService.render();
    }

}
