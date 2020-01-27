package dzy.game.game2048.view;

import javafx.beans.property.IntegerProperty;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import static javafx.scene.paint.Color.rgb;

/**
 * Created by moper3 on 2017/11/28.
 * 数字的对应Node
 */
class Item extends StackPane {
    private static Color[] bg = {
            rgb(204, 192, 178),
            rgb(238, 228, 218),
            rgb(236, 224, 200),
            rgb(242, 177, 121),
            rgb(245, 149, 99),
            rgb(245, 124, 95),
            rgb(246, 93, 59),
            rgb(237, 206, 113),
            rgb(237, 206, 113),
            rgb(236, 200, 80),
            rgb(237, 197, 63),
            rgb(237, 197, 63)};

    private static Color[] fg = {
            rgb(119, 110, 101),
            rgb(119, 110, 101),
            rgb(119, 110, 101),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242),
            rgb(242, 242, 242)};

    Item(IntegerProperty value) {
        setPrefSize(105, 105);
        getStyleClass().add("item");

        Text text = new Text();
        text.setFill(fg[value.get() % fg.length]);
        text.setId("num");

        Rectangle rectangle = new Rectangle(105, 105);
        rectangle.setFill(bg[0]);
        rectangle.setId("bg");

        getChildren().addAll(rectangle, text);

        value.addListener((o, ov, nv) -> {
            int intValue = nv.intValue();
            text.setText(intValue == 0 ? null : Integer.toString((int) Math.pow(2, intValue)));
            text.setFill(fg[intValue % fg.length]);
            rectangle.setFill(bg[intValue % bg.length]);
        });
    }
}
