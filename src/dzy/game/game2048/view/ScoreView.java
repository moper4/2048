package dzy.game.game2048.view;

import dzy.game.game2048.model.DataModel;
import javafx.scene.control.Label;


public class ScoreView extends Label {

    public ScoreView() {
        super("0");
        getStyleClass().add("score-view");
    }

    public void setModel(DataModel model) {
        model.addPropertyChangeListener("score", l -> setText(Integer.toString(model.getScore())));
    }
}
