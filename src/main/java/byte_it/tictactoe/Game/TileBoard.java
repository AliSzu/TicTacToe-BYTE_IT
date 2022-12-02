package byte_it.tictactoe.Game;

import javafx.scene.layout.GridPane;
import lombok.Getter;
import lombok.Setter;

import static byte_it.tictactoe.Game.Mark.*;

@Getter
@Setter
public class TileBoard {
    private MenuCenter menuCenter;
    private GridPane pane;
    private Tile[][] tiles = new Tile[3][3];
    private Mark playerTurn;
    private boolean endOfGame = true;
    private Tile tile;
    private int XWin = X.getMark() * 3;
    private int OWin = O.getMark() * 3;


    //TileBoard creation and initialization

    public TileBoard(MenuCenter menuCenter) {
        this.menuCenter = menuCenter;
        pane = new GridPane();
        pane.setMinSize(UIConstants.APP_WIDTH, UIConstants.TITLE_BOARD_HEIGHT);
        pane.setTranslateY(UIConstants.MENU_HEIGHT);
    }

    public void initTileBoard() {
        for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
            for (int col = 0; col < UIConstants.BOARD_WIDTH; col++) {
                tiles[row][col].setMark(EMPTY);
            }
        }
        endOfGame = false;
        setPlayerTurn(X);
    }


    public void changePlayerTurn() {
        if (playerTurn == O || playerTurn == EMPTY) {
            playerTurn = X;
        } else {
            playerTurn = O;
        }
    }

    //Main Game Logic

    public void checkForWinner() {
        if (!endOfGame) {
            //Checking rows
            int sum = 0;
            for (int row = 0; row < UIConstants.BOARD_WIDTH; row++) {
                sum = tiles[row][0].getMarkChar() + tiles[row][1].getMarkChar() + tiles[row][2].getMarkChar();
                checkWinner(sum);

            }

            //Checking columns
            sum = 0;
            for (int column = 0; column < UIConstants.BOARD_WIDTH; column++) {
                sum = tiles[0][column].getMarkChar() + tiles[1][column].getMarkChar() + tiles[2][column].getMarkChar();
                checkWinner(sum);
            }

            //Diagonal Left to Right
            sum = 0;
            for (int i = 0; i < UIConstants.BOARD_WIDTH; i++) {
                sum = sum + tiles[i][i].getMarkChar();
                checkWinner(sum);
            }

            //Diagonal Right To Left
            sum = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if ((i + j) == 2) {
                        sum = sum + tiles[i][j].getMarkChar();
                        checkWinner(sum);
                    }
                }
            }

            //Stalemate
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (tiles[row][column].getMark() == EMPTY) {
                        return;
                    }
                }
            }
            endOfGame = true;
            menuCenter.updateMessage("Stalemate...");
            menuCenter.showStartButton();
        }
    }

    private void checkWinner(int winSum)
    {
        String winner = "";
        if (XWin == winSum) {
            winner = X.toString();
        } else if (OWin == winSum) {
            winner = O.toString();
        }
        else
        {
            return;
        }
        EndGame(winner);
    }

    public void EndGame(String winner) {
        endOfGame = true;
        menuCenter.showStartButton();
        menuCenter.updateMessage("Player " + winner + " wins!");
    }

    // Other necessary functions


    public boolean anyMovesAvailable() {
        if (!endOfGame) {
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (tiles[row][column].getMark() == EMPTY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isTileMarked(int row, int column)
    {
        if(tiles[row][column].getMark() == EMPTY)
        {
            return false;
        }
        return true;
    }

    public void addToTileBoardTile(int row, int column, Tile tile)
    {
        tiles[row][column] = tile;
    }

    //Additional Getters and Setters

    public Mark getMarkAt(int row, int column) {
        return tiles[row][column].getMark();

    }

    public void setMarkAt(int row, int column, Mark mark){
        tiles[row][column].setMark(mark);
        if(!endOfGame) {
            changePlayerTurn();
        }
    }


    public Tile getTileAt(int row, int column){
        return tiles[row][column];
    }


}

