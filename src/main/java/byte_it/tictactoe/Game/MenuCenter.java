package byte_it.tictactoe.Game;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import lombok.SneakyThrows;

import java.io.FileInputStream;

public class MenuCenter {
    private StackPane pane;
    private Label message;
    private Button startGameButton;

    @SneakyThrows
    public MenuCenter()
    {
        pane = new StackPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.MENU_HEIGHT);
        pane.setTranslateX(UIConstants.APP_WIDTH/2);
        pane.setTranslateY(UIConstants.MENU_HEIGHT/2);

        message = new Label("Tic-Tac-Toe");
        message.setMinSize(UIConstants.APP_WIDTH, UIConstants.MENU_HEIGHT);
        message.setFont(Font.font(34));
        message.setAlignment(Pos.CENTER);
        message.setTranslateY(-20);
        message.getStyleClass().add("paneText");
        pane.getChildren().add(message);

        Image image = new Image(new FileInputStream("src/main/resources/byte_it/tictactoe/logo.png"));
        ImageView imageView = new ImageView(image);

        imageView.setFitHeight(80);
        imageView.setFitWidth(80);
        imageView.setTranslateX(150);
        imageView.setTranslateY(-20);
        pane.getChildren().add(imageView);

        startGameButton = new Button("Start New Game");
        startGameButton.setMinSize(150,40);
        startGameButton.setTranslateY(30);
        pane.getChildren().add(startGameButton);

    }

    public StackPane getStackPane()
    {
        return this.pane;
    }

    public void updateMessage(String message)
    {
        this.message.setText(message);
    }

    public void showStartButton()
    {
        this.startGameButton.setVisible(true);
    }

    public void hideStartButton()
    {
        this.startGameButton.setVisible(false);
    }

    public void setStartGameButtonOnAction(EventHandler<ActionEvent> onAction)
    {
        this.startGameButton.setOnAction(onAction);
    }
}
