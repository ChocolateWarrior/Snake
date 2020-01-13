package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayDeque;
import java.util.Deque;

@Getter
@Setter
public class Snake {

    private int size;
    private Node head;
    private Deque<Node> snakeBody;

    public Snake() {
        size = 3;
        head = new Node(0, 0, Direction.W);
        snakeBody = new ArrayDeque<>(size);
        snakeBody.add(head);
        snakeBody.add(new Node(0, 1, Direction.W));
        snakeBody.add(new Node(0, 2, Direction.W));

    }

}
