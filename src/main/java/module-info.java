module byte_it.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens byte_it.tictactoe to javafx.fxml;
    exports byte_it.tictactoe;
    exports byte_it.tictactoe.Game;
    opens byte_it.tictactoe.Game to javafx.fxml;
    exports byte_it.tictactoe.UI;
    opens byte_it.tictactoe.UI to javafx.fxml;
}