module byte_it.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens byte_it.tictactoe to javafx.fxml;
    exports byte_it.tictactoe;
}