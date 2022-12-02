package byte_it.tictactoe;
import byte_it.tictactoe.AI.AlphaBetaPruning;
import byte_it.tictactoe.Game.*;
import byte_it.tictactoe.UI.MenuCenter;
import byte_it.tictactoe.UI.UIConstants;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static byte_it.tictactoe.Game.Mark.*;

public class TicTacToe extends Application {

    private MenuCenter menuCenter;
    private static TileBoard tileBoard;
    private AnimationTimer gameTimer;

    @Override
    public void start(Stage stage) throws IOException {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, UIConstants.APP_WIDTH, UIConstants.APP_HEIGHT);
        scene.getStylesheets().add(getClass().getResource("/byte_it/tictactoe/styles.css").toExternalForm());
        stage.setResizable(false);
        root.getStyleClass().add("paneMenu");
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
        int id = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Tile tile = new Tile(EMPTY, row, col, tileBoard);
                tile.setId(id);
                id++;
                tileBoard.addToTileBoardTile(row, col, tile);
                tileBoard.getPane().add(tile, col, row);
            }
        }
        root.getChildren().add(tileBoard.getPane());
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
                newGame();
                runGame();
            }
        };
    }

    private void newGame()
    {
        menuCenter.updateMessage("Good luck :)");
        tileBoard.initTileBoard();
        tileBoard.setEndOfGame(false);
        tileBoard.setPlayerTurn(X);

    }

    private void runGame()
    {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if(!tileBoard.isEndOfGame()) {
                    if (tileBoard.getPlayerTurn() == X) {
                        playAI();
                    }
                }
                tileBoard.checkForWinner();
            }
        };
        gameTimer.start();
    }

    private static void playAI() {
        int[] move = AlphaBetaPruning.getBestMove(tileBoard);
        int row = move[0];
        int col = move[1];
        tileBoard.setMarkAt(row, col, X);
        return;
    }


    public static void main(String[] args) {
        launch();
    }
}