package services;

import entities.Direction;
import entities.Node;
import entities.Snake;
import entities.SquarePlayingField;
import views.Display;

import java.util.NoSuchElementException;

public class RenderService {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";

//    private static final String TRIANGLE_RIGHT = " \u25BA ";
//    private static final String TRIANGLE_DOWN = " \u25BC ";
//    private static final String TRIANGLE_LEFT = " \u25C4 ";
//    private static final String TRIANGLE_UP = " \u25B2 ";
    private static final String TRIANGLE_RIGHT = " > ";
    private static final String TRIANGLE_DOWN = " v ";
    private static final String TRIANGLE_LEFT = " < ";
    private static final String TRIANGLE_UP = " ^ ";

    private static final String CIRCLE = " o ";
    private static final String SPACE = "   ";

    private SquarePlayingField field;
    private Snake snake;
    private Display display;

    public RenderService(SquarePlayingField field, Snake snake, Display display) {
        this.field = field;
        this.snake = snake;
        this.display = display;
    }

    void render() {
        for (int i = 0; i < field.getSize(); i++) {
            for (int j = 0; j < field.getSize(); j++) {
                Node currentNode = new Node(j, i, Direction.D);

                if (isBodyPart(currentNode)) {
                    if (isHead(currentNode)) {
                        displayHead(snake.getHead());
                    } else {
                        display.view(ANSI_RED + CIRCLE + ANSI_RESET);
                    }
                } else if (isSprite(currentNode)) {
                    display.view(ANSI_BLUE + CIRCLE + ANSI_RESET);
                } else {
                    display.view(SPACE);
                }
            }
            display.viewEmpty();
        }
    }

    private boolean isSprite(Node currentNode) {
        return field.getSprite().getPosition().equals(currentNode);
    }

    private boolean isHead(Node currentNode) {
        return snake.getSnakeBody().stream()
                .filter(e -> e.equals(currentNode))
                .findFirst()
                .orElseThrow(NoSuchElementException::new)
                .equals(snake.getHead());
    }

    private boolean isBodyPart(Node currentNode) {
        return snake.getSnakeBody().contains(currentNode);
    }

    private void displayHead(Node head) {
        switch (head.getDirection()) {
            case D:
                display.view(ANSI_RED + TRIANGLE_RIGHT + ANSI_RESET);
                break;
            case S:
                display.view(ANSI_RED + TRIANGLE_DOWN + ANSI_RESET);
                break;
            case A:
                display.view(ANSI_RED + TRIANGLE_LEFT + ANSI_RESET);
                break;
            case W:
                display.view(ANSI_RED + TRIANGLE_UP + ANSI_RESET);
                break;
        }
    }

}
