package entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SquarePlayingField {

    private int size;
    private List<Node> nodeField;
    private Sprite sprite;

    public SquarePlayingField(int size) {
        this.size = size;
        nodeField = new ArrayList<>(size);
    }


}
