import java.util.ArrayList;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BricksPane extends Pane {
    ArrayList<Rectangle> bricks = new ArrayList<>();
    double rows = 3, cols = 8;
    double brickX = 200, brickY = 100, brickWidth = 80, brickHeight = 30;

    IntegerProperty scoreProperty = new SimpleIntegerProperty();

    public BricksPane() {
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                Rectangle brick = new Rectangle((i * brickWidth + brickX),
                        (j * brickHeight + brickY), brickWidth, brickHeight);
                brick.setStroke(Color.WHITE);
                getChildren().add(brick);
                bricks.add(brick);
            }
        }
    }

    public IntegerProperty getScoreProperty() {
        return scoreProperty;
    }

    // Ball hitting brick should deflect in different direction depending on
    // direction it hits from
    // Top, bottom: invert y
    // Left, right: invert x
    // Use different quadrants for each impact to determine which is current

    // 1. Check if ball intersecting brick (check which brick)
    // 2. If intersecting, check which quadrant it is intersecting
    // 3. Change direction according to quadrant

    public String intersects(Circle ball) {
        String result = "";
        for (Rectangle brick : bricks) {
            if (brick.intersects(ball.getBoundsInLocal())) {
                scoreProperty.setValue(scoreProperty.getValue() + 2);
                // could have combined for top and bottom hit by removing last condition
                // could have combined for left and right hit by removing last condition
                if (ball.getCenterX() >= brick.getX() && ball.getCenterX() <= brick.getX() +
                        brickWidth && ball.getCenterY() + ball.getRadius() >= brick.getY()) { // check for hit on top
                    result = "hit along y direction";
                } else if (ball.getCenterX() >= brick.getX() && ball.getCenterX() <= brick.getX() +
                        brickWidth && ball.getCenterY() - ball.getRadius() <= brick.getY() + brickHeight) { // check for
                                                                                                            // hit on
                                                                                                            // bottom
                    result = "hit along y direction";
                } else if (ball.getCenterY() >= brick.getY() && ball.getCenterY() <= brick.getY() +
                        brickHeight && ball.getCenterX() + ball.getRadius() >= brick.getX()) { // check for hit on right
                    result = "hit along x direction";
                    System.out.println(("right"));
                } else if (ball.getCenterY() >= brick.getY() && ball.getCenterY() <= brick.getY() + brickHeight
                        && ball.getCenterX() - ball.getRadius() <= brick.getX() + brickWidth) { // check for hit on
                                                                                                // left
                    result = "hit along x direction";
                    System.out.println("left");
                } else { // if ball hits corner
                    result = "hit along y direction";
                }
                getChildren().remove(brick); // remove brick from pane
                bricks.remove(brick); // remove brick from array
                return result;
            }
        }
        return result;
    }
}
