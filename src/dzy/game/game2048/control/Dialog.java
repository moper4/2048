package dzy.game.game2048.control;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

class Dialog extends Stage {
  private static final int CLOSE = 0;
  private static final int NO = 1;
  static final int OK = 2;

  private Label headerLabel;
  private int state = 0;

  Dialog() {
    headerLabel = new Label();
    headerLabel.setPadding(new Insets(10));

    DialogPane root = new DialogPane();
    root.setPrefSize(120, 70);
    root.setHeader(headerLabel);
    root.getButtonTypes().addAll(ButtonType.NO, ButtonType.OK);

    Button noBtn = (Button) root.lookupButton(ButtonType.NO);
    noBtn.setOnAction(
        e -> {
          state = NO;
          hide();
        });

    Button okBtn = (Button) root.lookupButton(ButtonType.OK);
    okBtn.setOnAction(
        e -> {
          state = OK;
          hide();
        });

    setTitle("2048");
    setScene(new Scene(root));
    initModality(Modality.APPLICATION_MODAL);
  }

  int showMsg(String msg) {
    headerLabel.setText(msg);
    state = CLOSE;
    showAndWait();
    return state;
  }
}
