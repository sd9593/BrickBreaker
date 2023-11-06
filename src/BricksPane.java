import java.util.ArrayList;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BricksPane extends Pane {
    ArrayList<Rectangle> bricks = new ArrayList<>();
    double rows = 3, cols = 8;
    double brickX = 150, brickY = 100, brickWidth = 80, brickHeight = 30, offset = 10;

    public BricksPane() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Rectangle brick = new Rectangle((i * (brickWidth + offset)) + brickX,
                        (j * (brickHeight + offset)) + brickY, brickWidth, brickHeight);
                brick.setStroke(Color.WHITE);
                getChildren().add(brick);
                bricks.add(brick);
            }
        }
    }

}
