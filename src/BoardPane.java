
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BoardPane extends Pane {
    private double radius = 20;
    private double ballX = 200, ballY = 400; // starting value doesn't matter since animation will change it
    private Circle ball = new Circle(250, 450, radius);
    private Rectangle paddle = new Rectangle(200, 475, 100, 20);

    private double dx = 1, dy = 1;

    Timeline animation;

    public BoardPane() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                getChildren().add(new Rectangle(i * 60 + 10, j * 20 + 10, 50, 10));
            }
        }
        ball.setFill(Color.GREEN);
        paddle.setFill(Color.BLUE);
        getChildren().addAll(ball, paddle);
        animation = new Timeline(new KeyFrame(Duration.millis(10), e -> moveBall()));
        animation.setCycleCount(Timeline.INDEFINITE); // animation runs forever
    }

    public void startBall() {
        animation.play();
    }

    protected void moveBall() {
        // top left of screen is (0,0)
        // y coordinate of ball plus radius should be less than paneHeight (otherwise
        // bounce)
        if (ballY + radius > getHeight() || ballY < radius) { // getHeight finds height of pane, y < radius find is ball
                                                              // is at
            // top of pane
            dy *= -1; // change direction of y
        }
        if (ballX + radius > getWidth() || ballX < radius) {
            dx *= -1;
        }
        ballX += dx;
        ballY += dy;
        ball.setCenterX(ballX);
        ball.setCenterY(ballY);
    }

    protected void movePaddleLeft() {
        if (paddle.getX() < 0) {
            dx *= -1;
        } else {
            paddle.setX(paddle.getX() - 5);
        }
    }

    protected void movePaddleRight() {
        if (paddle.getX() + paddle.getWidth() > getWidth()) {
            dx *= -1;
        } else {
            paddle.setX(paddle.getX() + 5);
        }
    }
}
