
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class BoardPane extends Pane {
    private double radius = 20;
    private double ballX = 200, ballY = 400; // starting value doesn't matter since animation will change it
    private Circle ball = new Circle(ballX, ballY, radius);
    private double dx = 1, dy = 1;
    private double speed = 20;
    private double paddleX = 350, paddleY = 600, paddleWidth = 150, paddleHeight = 20;
    private Rectangle paddle = new Rectangle(paddleX, paddleY, paddleWidth, paddleHeight);

    Timeline ballAnimation;

    public BoardPane() {
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                getChildren().add(new Rectangle(i * 110 + 10, j * 50 + 10, 100, 20));
            }
        }
        ball.setFill(Color.GREEN);
        paddle.setFill(Color.BLUE);
        getChildren().addAll(ball, paddle);
        ballAnimation = new Timeline(new KeyFrame(new Duration(5), e -> moveBall()));
        ballAnimation.setCycleCount(Timeline.INDEFINITE); // animation runs forever
    }

    public void play() {
        ballAnimation.play();
    }

    protected void moveBall() {
        // top left of screen is (0,0)
        // y coordinate of ball plus radius should be less than paneHeight (otherwise
        // bounce)
        if (ballY + radius > getHeight()) {
            ballAnimation.stop();
        } else if (ballY < radius) {
            dy *= -1; // change direction of y
        } else if (paddle.intersects(ball.getBoundsInLocal())) {
            dy *= -1;
        } else if (ballX + radius > getWidth() || ballX < radius) {
            dx *= -1;
        }

        ballX += dx;
        ballY += dy;
        ball.setCenterX(ballX);
        ball.setCenterY(ballY);
    }

    protected void movePaddle(KeyEvent e) {
        if (e.getCode() == KeyCode.LEFT) {
            if (paddleX >= 0) {
                paddleX -= speed;
            }
        } else if (e.getCode() == KeyCode.RIGHT) {
            if (paddleX + paddleWidth < getWidth()) {
                paddleX += speed;
            }
        }
        paddle.setX(paddleX);
    }
}
