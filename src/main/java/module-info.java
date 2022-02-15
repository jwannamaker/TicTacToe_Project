module com.tictactoe.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.tictactoe.tictactoe to javafx.fxml;
    exports com.tictactoe.tictactoe;
}