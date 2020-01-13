package services;

import entities.Direction;
import entities.Node;
import entities.Snake;
import entities.SquarePlayingField;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeService {

    private SpriteService spriteService;
    private SquarePlayingField field;
    private Snake snake;

    public SnakeService(SpriteService spriteService, SquarePlayingField field, Snake snake) {
        this.spriteService = spriteService;
        this.field = field;
        this.snake = snake;
    }

    void updateSnakeSituation(Direction direction) {
        updateSnakeBody(direction);
        eatSpriteIfPossible();
    }

    boolean hasLost() {
        ArrayDeque<Node> tail = new ArrayDeque<>(snake.getSnakeBody());

        tail.removeFirst();
        return tail.contains(snake.getHead());
    }

    private void updateSnakeBody(Direction direction){
        Deque<Node> body = snake.getSnakeBody();

        body.removeLast();
        body.addFirst(getHeadByDirection(direction));
        snake.setHead(getHeadByDirection(direction));
        snake.setSnakeBody(body);
    }

    private void eatSpriteIfPossible() {
        if (snake.getHead().equals(field.getSprite().getPosition())) {
            growSnake();
            spriteService.spawnNewSprite();
        }
    }

    private Node getHeadByDirection(Direction direction){

        Node oldHead = snake.getHead();

        switch (direction){
            case W:
                return new Node(oldHead.getXCoordinate(),
                        (oldHead.getYCoordinate() - 1 + field.getSize()) % field.getSize(),
                        direction);
            case S:
                return new Node(oldHead.getXCoordinate(),
                        (oldHead.getYCoordinate() + 1) % field.getSize(),
                        Direction.S);
            case D:
                return new Node((oldHead.getXCoordinate() + 1) % field.getSize(),
                        oldHead.getYCoordinate(),
                        Direction.D);
            case A:
                return new Node((oldHead.getXCoordinate() - 1 + field.getSize()) % field.getSize(),
                        oldHead.getYCoordinate(),
                        Direction.A);
            default:
                return getHeadByDirection(snake.getHead().getDirection());
        }
    }

    private void growSnake() {

        Node tail = snake.getSnakeBody().getLast();

        switch (tail.getDirection()) {
            case D:
                snake.getSnakeBody()
                        .addLast(new Node((tail.getXCoordinate() - 1 + field.getSize()) % field.getSize(),
                                tail.getYCoordinate(),
                                tail.getDirection()));
                break;
            case S:
                snake.getSnakeBody()
                        .addLast(new Node(tail.getXCoordinate(),
                                (tail.getYCoordinate() - 1 + field.getSize()) % field.getSize(),
                                tail.getDirection()));
                break;
            case A:
                snake.getSnakeBody()
                        .addLast(new Node(tail.getXCoordinate() + 1,
                                tail.getYCoordinate(),
                                tail.getDirection()));
                break;
            case W:
                snake.getSnakeBody()
                        .addLast(new Node(tail.getXCoordinate(),
                                tail.getYCoordinate() + 1,
                                tail.getDirection()));
                break;
        }
    }
}
