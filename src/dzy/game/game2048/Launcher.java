package dzy.game.game2048;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/dzy/game/game2048/launcher/2048.fxml"));

        Scene scene = new Scene(root);
        scene.getStylesheets().add("dzy/game/game2048/launcher/2048.css");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("2048");
        primaryStage.show();
    }
}