package dzy.game.game2048.launcher;

import dzy.game.game2048.control.GameControl;
import dzy.game.game2048.model.DataModel;
import dzy.game.game2048.view.ItemView;
import dzy.game.game2048.view.ScoreView;
import javafx.fxml.FXML;

public class Controller {
    @FXML
    ScoreView scoreView;
    @FXML
    ItemView itemView;

    @FXML
    private void initialize() {
        DataModel model = new DataModel();
        scoreView.setModel(model);
        itemView.setModel(model);
        itemView.setFocusTraversable(true);
        new GameControl(model, itemView);
    }
}
