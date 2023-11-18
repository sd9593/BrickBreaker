import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) {

        Label scoreBoard = new Label("Your score: ");
        Text text = new Text();
        HBox hbox = new HBox(scoreBoard, text);

        BoardPane boardPane = new BoardPane();

        boardPane.getScoreProperty().addListener(ov -> {
            text.setText(Integer.toString(boardPane.getScoreProperty().getValue()));
        });

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(boardPane);
        borderPane.setBottom(hbox);

        boardPane.setOnKeyPressed(e -> { // event e is of KeyPressed type
            if (e.getCode() == KeyCode.ENTER) {
                boardPane.play();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                boardPane.movePaddle(e);
            }
        });

        Scene scene = new Scene(borderPane, 1000, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brick Breaker");
        primaryStage.show();
        primaryStage.setResizable(false);

        boardPane.requestFocus();
    }

    public static void main(String[] args) throws Exception {
        Application.launch();
    }
}
