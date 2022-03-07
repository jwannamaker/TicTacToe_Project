package com.tictactoe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class TitleController {

    @FXML
    public Button easyButton, moderateButton, impossibleButton, backButton, localButton, onlineButton, onePlayerButton, twoPlayerButton, quitButton;
    public Label titleScreenLabel;
    private Stage stage;
    private Scene scene;
    private FXMLLoader root;

    @FXML
    public void switchBoardScene(ActionEvent event) throws IOException {
        root = new FXMLLoader(Main.class.getResource("TicTacToeBoard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load(), 400,500);
        stage.setScene(scene);
        BoardController.twoPlayer=false;
    }

    @FXML
    public void switchBoardScene2(ActionEvent event) throws IOException {
        root = new FXMLLoader(Main.class.getResource("TicTacToeBoard.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root.load(), 400,500);
        stage.setScene(scene);
        BoardController.twoPlayer=true;
    }

    public void backButtonClicked(ActionEvent actionEvent) {
        onePlayerButton.setVisible(true);
        twoPlayerButton.setVisible(true);
        quitButton.setVisible(true);
        easyButton.setVisible(false);
        moderateButton.setVisible(false);
        impossibleButton.setVisible(false);
        backButton.setVisible(false);
        localButton.setVisible(false);
        onlineButton.setVisible(false);
        titleScreenLabel.setText("Welcome to Tic-Tac-Toe");
    }

    public void onePlayerClicked(ActionEvent actionEvent) {
        onePlayerButton.setVisible(false);
        twoPlayerButton.setVisible(false);
        quitButton.setVisible(false);
        easyButton.setVisible(true);
        moderateButton.setVisible(true);
        impossibleButton.setVisible(true);
        backButton.setVisible(true);
        titleScreenLabel.setText("Select a Difficulty");
    }

    public void twoPlayerClicked(ActionEvent actionEvent) {
        onePlayerButton.setVisible(false);
        twoPlayerButton.setVisible(false);
        quitButton.setVisible(false);
        localButton.setVisible(true);
        onlineButton.setVisible(true);
        backButton.setVisible(true);
        titleScreenLabel.setText("Select a Connection Method");
    }

    public void quitButtonClicked(ActionEvent actionEvent) {
        Platform.exit();
        System.exit(0);
    }
}


