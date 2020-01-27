package dzy.game.game2048.view;

import dzy.game.game2048.model.DataModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.TilePane;

import java.util.stream.IntStream;

import static dzy.game.game2048.model.DataModel.COLUMN;
import static dzy.game.game2048.model.DataModel.ROW;

public class ItemView extends TilePane {
    private DataModel model;
    private final IntegerProperty[] values = new SimpleIntegerProperty[ROW * COLUMN];

    public ItemView() {
        setPrefColumns(COLUMN);
        getStyleClass().add("item-pane");

        IntStream.range(0, ROW * COLUMN)
                .mapToObj(i -> values[i] = new SimpleIntegerProperty())
                .map(Item::new)
                .forEach(getChildren()::add);
    }

    public void setModel(DataModel model){
        this.model = model;
        repaint();
        model.addPropertyChangeListener("data", l -> repaint());
    }

    //int[][] -> IntegerProperty[][]
    private void repaint() {
        int[][] boxes = model.getData();
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COLUMN; j++)
                values[i * COLUMN + j].set(boxes[i][j]);
    }
}
