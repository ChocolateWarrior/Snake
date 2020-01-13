package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
public class Node {

    private int xCoordinate;
    private int yCoordinate;
    private Direction direction;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return xCoordinate == node.xCoordinate &&
                yCoordinate == node.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                ", direction=" + direction;
    }
}
