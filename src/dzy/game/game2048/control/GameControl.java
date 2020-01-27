package dzy.game.game2048.control;

import dzy.game.game2048.model.DataModel;
import dzy.game.game2048.view.ItemView;
import javafx.application.Platform;
import javafx.scene.input.KeyEvent;

public class GameControl {
    private final Dialog dialog = new Dialog();
    private DataModel model;

    public GameControl(DataModel model, ItemView view) {
        this.model = model;
        view.setOnKeyReleased(this::handleKeyEvent);
    }

    //处理键盘输入事件
    private void handleKeyEvent(KeyEvent e) {
        switch (e.getCode()) {
            case W:
            case UP:
                check(model.moveUp());
                break;
            case S:
            case DOWN:
                check(model.moveDown());
                break;
            case A:
            case LEFT:
                check(model.moveLeft());
                break;
            case D:
            case RIGHT:
                check(model.moveRight());
                break;
            default:
                break;
        }
    }

    private void check(boolean addNum) {
        if (addNum) model.randomAddNumberWithNotify();
        if (model.isGameWin()) showMsg("游戏胜利, 再来一次?");
        if (model.isGameLose()) showMsg("游戏失败, 再来一次?");
    }

    private void showMsg(String msg) {
        if (dialog.showMsg(msg) == Dialog.OK) {
            model.clear();
        } else {
            Platform.exit();
        }
    }
}
