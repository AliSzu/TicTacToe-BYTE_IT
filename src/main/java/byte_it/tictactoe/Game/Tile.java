package byte_it.tictactoe.Game;

import byte_it.tictactoe.Game.Mark;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import lombok.Getter;
import lombok.Setter;

import static byte_it.tictactoe.Game.Mark.EMPTY;

@Getter
@Setter
public class Tile extends Button {
    private Mark mark;
    private int row;
    private int column;

    public Tile(Mark mark, int row, int column, TileBoard tileBoard)
    {
        this.mark = mark;
        this.row = row;
        this.column = column;
        initTile(tileBoard);
    }

    private void initTile(TileBoard tileBoard)
    {
        this.setOnMouseClicked(e -> {
            if(tileBoard.getMarkAt(this.row, this.column) == EMPTY && !tileBoard.isEndOfGame()) {
                tileBoard.setMarkAt(this.row, this.column, tileBoard.getPlayerTurn());

            }
        });
        this.setMinSize(150, 150);
        this.getStyleClass().add("button1");
        this.setFont(Font.font(24));
        this.setText(mark.toString());
    }

    public void setId(int id)
    {
        this.setId(Integer.toString(id));
    }

    public void setMark(Mark mark)
    {
        this.mark = mark;
        this.setText(mark.toString());
    }

    public char getMarkChar()
    {
        return this.mark.getMark();
    }



}
