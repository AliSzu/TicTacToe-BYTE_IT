package byte_it.tictactoe;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private MenuCenter menuCenter;
    private TileBoard tileBoard;
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        // typ Layouty
        Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/byte_it/tictactoe/styles.css").toExternalForm());
        initLayout(root);
        stage.setScene(scene);
        stage.show();
    }

    private void initLayout(BorderPane root)
    {
        initMenuCenter(root);
        initTileBoard(root);
    }

    private void initTileBoard(BorderPane root) {
        tileBoard = new TileBoard(menuCenter);
        root.getChildren().add(tileBoard.getStackPane());

    }

    private void initMenuCenter(BorderPane root) {
        menuCenter = new MenuCenter();
        menuCenter.setStartGameButtonOnAction(startNewGame());
        root.getChildren().add(menuCenter.getStackPane());
    }

    private EventHandler<ActionEvent> startNewGame()
    {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                menuCenter.hideStartButton();
                menuCenter.updateMessage("Your Turn");
                System.out.println("startaaaaaa");
            }
        };
    }

    public static void main(String[] args) {
        launch();
    }
}