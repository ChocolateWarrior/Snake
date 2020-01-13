package services;

import entities.*;

import java.util.Random;

public class SpriteService {

    private Snake snake;
    private Random random;
    private SquarePlayingField field;

    public SpriteService(Random random, SquarePlayingField field, Snake snake) {
        this.random = random;
        this.field = field;
        this.snake = snake;
    }

    void spawnNewSprite() {
        Sprite sprite = randomizeSprite();
        while (snakeContainsSpriteNode(sprite)) {
            sprite = randomizeSprite();
        }
        field.setSprite(sprite);
    }

    private boolean snakeContainsSpriteNode(Sprite sprite) {
        return snake.getSnakeBody().contains(sprite.getPosition());
    }

    private Sprite randomizeSprite() {
        return new Sprite(
                new Node(random.nextInt(field.getSize()),
                        random.nextInt(field.getSize()),
                        Direction.D));
    }


}
