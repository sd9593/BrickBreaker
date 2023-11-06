import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class App extends Application {

    public void start(Stage primaryStage) {
        BoardPane boardPane = new BoardPane();

        boardPane.setOnKeyPressed(e -> { // event e is of KeyPressed type
            if (e.getCode() == KeyCode.ENTER) {
                boardPane.play();
            } else if (e.getCode() == KeyCode.LEFT || e.getCode() == KeyCode.RIGHT) {
                boardPane.movePaddle(e);
            }
        });

        Scene scene = new Scene(boardPane, 1000, 700);
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
