import controllers.GameController;
import entities.Snake;
import entities.SquarePlayingField;
import services.GameService;
import services.RenderService;
import services.SnakeService;
import services.SpriteService;
import views.Display;

import java.io.IOException;
import java.util.Random;

public class Game {

    public static void main(String[] args) throws IOException {

        Random random = new Random();
        Snake snake = new Snake();
        SquarePlayingField field = new SquarePlayingField(6);
        Display display = new Display();

        SpriteService spriteService = new SpriteService(random, field, snake);
        SnakeService snakeService = new SnakeService(spriteService, field, snake);
        RenderService renderService = new RenderService(field, snake, display);
        GameService gameService = new GameService(display, renderService, snakeService, spriteService);

        GameController gameController = new GameController(gameService);
        gameController.play();

    }

}
