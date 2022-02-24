package com.tictactoe;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BoardController {

    @FXML
    private Button startButton;
    @FXML
    private Label gameLabel;

    @FXML
    private Label box1;
    @FXML
    private Label box2;
    @FXML
    private Label box3;
    @FXML
    private Label box4;
    @FXML
    private Label box5;
    @FXML
    private Label box6;
    @FXML
    private Label box7;
    @FXML
    private Label box8;
    @FXML
    private Label box9;

    @FXML
    private StackPane tile1;
    @FXML
    private StackPane tile2;
    @FXML
    private StackPane tile3;
    @FXML
    private StackPane tile4;
    @FXML
    private StackPane tile5;
    @FXML
    private StackPane tile6;
    @FXML
    private StackPane tile7;
    @FXML
    private StackPane tile8;
    @FXML
    private StackPane tile9;

    @FXML
    private Line winningLine;

    private List<StackPane> tiles;

    private List<Label> box;

    private char playerTurn = 'X';

    @FXML
    public void initialize() {
        box = new ArrayList<>(Arrays.asList(box1,box2,box3,box4,box5,box6,box7,box8,box9));
        tiles = new ArrayList<>(Arrays.asList(tile1,tile2,tile3,tile4,tile5,tile6,tile7,tile8,tile9));

        tiles.forEach(stackPane -> stackPane.setMaxSize(100,100));
        tiles.forEach(stackPane -> stackPane.setDisable(true));
        Rectangle e = new Rectangle();

        int i = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                tiles.get(i).setTranslateX((col * 100) - 100);
                tiles.get(i).setTranslateY((row * 100) - 100);
                i++;
            }
        };
        winningLine.setVisible(false);
    }

    @FXML
    void startingGame(ActionEvent event) {
        startButton.setVisible(false);
        tiles.forEach(stackPane -> stackPane.setDisable(false));
        box.forEach(label -> label.setText(""));
        winningLine.setVisible(false);
    }

    @FXML
    public void playerAction(MouseEvent e) {
        for (int i = 0; i < 9; i++) {
            if(e.getSource().equals(tiles.get(i))) {
                setPlayerSymbol(box.get(i));
                tiles.get(i).setDisable(true);
                checkIfGameIsOver();

            }
        }
    }

    public void setPlayerSymbol(Label label){
        if(playerTurn == 'X'){
            label.setText("X");
            playerTurn = 'O';
        } else{
            label.setText("O");
            playerTurn = 'X';
        }
        gameLabel.setText("Player " + playerTurn + "'s turn");
    }

    public boolean fullBoard() {
        boolean isFull =    box1.getText() != "" &&
                box2.getText() != "" &&
                box3.getText() != "" &&
                box4.getText() != "" &&
                box5.getText() != "" &&
                box6.getText() != "" &&
                box7.getText() != "" &&
                box8.getText() != "" &&
                box9.getText() != "";

        return isFull;
    }

    public void gameEnd(List<StackPane> winningLabels) {
        tiles.forEach(stackPane -> stackPane.setDisable(true));
        if(winningLabels != null) {
            drawWinningLine(winningLabels);
            winningLine.setVisible(true);
        }
        startButton.setVisible(true);
    }

    private void drawWinningLine(List<StackPane> winningLabels) {
        winningLine.setStartX(winningLabels.get(0).getTranslateX());
        winningLine.setStartY(winningLabels.get(0).getTranslateY());
        winningLine.setEndX(winningLabels.get(2).getTranslateX());
        winningLine.setEndY(winningLabels.get(2).getTranslateY());
        winningLine.setTranslateX(winningLabels.get(1).getTranslateX());
        winningLine.setTranslateY(winningLabels.get(1).getTranslateY());
    }

    public void checkIfGameIsOver(){

        for (int a = 0; a < 8; a++) {
            String line = switch (a) {
                case 0 -> box1.getText() + box2.getText() + box3.getText();
                case 1 -> box4.getText() + box5.getText() + box6.getText();
                case 2 -> box7.getText() + box8.getText() + box9.getText();
                case 3 -> box1.getText() + box5.getText() + box9.getText();
                case 4 -> box3.getText() + box5.getText() + box7.getText();
                case 5 -> box1.getText() + box4.getText() + box7.getText();
                case 6 -> box2.getText() + box5.getText() + box8.getText();
                case 7 -> box3.getText() + box6.getText() + box9.getText();
                default -> null;
            };

            List<StackPane> winningLabels = new ArrayList<>();
            switch (a) {
                case 0 -> { winningLabels.add(tile1); winningLabels.add(tile2); winningLabels.add(tile3);}
                case 1 -> { winningLabels.add(tile4); winningLabels.add(tile5); winningLabels.add(tile6);}
                case 2 -> { winningLabels.add(tile7); winningLabels.add(tile8); winningLabels.add(tile9);}
                case 3 -> { winningLabels.add(tile1); winningLabels.add(tile5); winningLabels.add(tile9);}
                case 4 -> { winningLabels.add(tile3); winningLabels.add(tile5); winningLabels.add(tile7);}
                case 5 -> { winningLabels.add(tile1); winningLabels.add(tile4); winningLabels.add(tile7);}
                case 6 -> { winningLabels.add(tile2); winningLabels.add(tile5); winningLabels.add(tile8);}
                case 7 -> { winningLabels.add(tile3); winningLabels.add(tile6); winningLabels.add(tile9);}

            }

            //X winner
            if (line.equals("XXX")) {
                gameLabel.setText("X won!");
                gameEnd(winningLabels);
                a=7;
            }
            //O winner
            else if (line.equals("OOO")) {
                gameLabel.setText("O won!");
                gameEnd(winningLabels);
                a=7;
            }
            else if(fullBoard() && a == 7) {
                gameLabel.setText("There is no winner");
                gameEnd(null);
            }
        }
    }

}