package com.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleController {

    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    @FXML
    public void switchBoardScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(Main.class.getResource("TicTacToeBoard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load(), 400,500);
        stage.setScene(scene);
    }
}
