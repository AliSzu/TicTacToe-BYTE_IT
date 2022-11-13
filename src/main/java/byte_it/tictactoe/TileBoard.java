package byte_it.tictactoe;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class TileBoard {
    private MenuCenter menuCenter;
    private GridPane pane;
    private Tile[][] tiles = new Tile[3][3];
    private char playerTurn = 'X';
    private boolean isEndOfGame = false;


    public TileBoard(MenuCenter menuCenter)
    {
        this.menuCenter = menuCenter;
        pane = new GridPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TITLE_BOARD_HEIGHT);
        pane.setTranslateY(UIConstants.MENU_HEIGHT);

        addAllTiles2();

    }

    private void addAllTiles()
    {
        for ( int row = 0 ; row < 3; row++)
        {
            for (int column = 0; column < 3; column ++)
            {
                Tile tile = new Tile();
                tile.getStackPane().setTranslateX((column * 150) - 150);
                tile.getStackPane().setTranslateY((row * 150) - 150);
                pane.getChildren().add(tile.getStackPane());
                tiles[row][column] = tile;
            }
        }
    }

    private void addAllTiles2()
    {
        for ( int row = 0 ; row < 3; row++)
        {
            for (int column = 0; column < 3; column ++)
            {
                Button button = new Button();
                button.setMinSize(150,150);
                button.getStyleClass().add("button1");

                button.setOnMouseClicked(event ->
                {
                    if (button.getText().isEmpty() && !isEndOfGame)
                    {
                        button.setText(getPlayerTurn());
                        changePlayerTurn();
                    }
                });
                pane.add(button,row, column );
            }
        }
    }



    public void changePlayerTurn()
    {
        if (playerTurn == 'X')
        {
            playerTurn = 'O';
        }
        else
        {
            playerTurn = 'X';
        }
        menuCenter.updateMessage("Player " + playerTurn  + "'s turn");
    }

    public String getPlayerTurn()
    {
        return String.valueOf(playerTurn);
    }

    public GridPane getStackPane()
    {
        return this.pane;
    }

    private class Tile
    {
        private GridPane pane;
        private Label label;
        public Tile()
        {
            pane = new GridPane();
            pane.setMinSize(150,150);

            Rectangle border = new Rectangle();
            border.setHeight(150);
            border.setWidth(150);
            border.setFill(Color.TRANSPARENT);
            border.setStroke(Color.BLACK);
            pane.getChildren().add(border);

            label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setFont(Font.font(24));
            pane.getChildren().add(label);

            pane.setOnMouseClicked(event ->
            {
                if (label.getText().isEmpty() && !isEndOfGame)
                {
                    label.setText(getPlayerTurn());
                    changePlayerTurn();
                }
            });
        }

        public String getValue() {
            return this.label.getText();
        }

        public GridPane getStackPane() {
            return this.pane;
        }

        public void setValue(String value)
        {
            this.label.setText(value);
        }
    }

}
