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
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class BoardController extends MiniMax {
    @FXML
    private Button startButton;
    @FXML
    private Label gameLabel, ScoreBoardO, ScoreBoardX, ScoreBoardDraw;
    @FXML
    private Label box1, box2, box3, box4, box5, box6, box7, box8, box9;
    @FXML
    private StackPane tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9;
    @FXML
    private Line winningLine;
    private List<StackPane> tiles;
    private List<Label> box;
    private int xWin, oWin, draw;
    private char playerTurn = 'X';

    @FXML
    public void initialize() {
        box = new ArrayList<>(Arrays.asList(box1,box2,box3,box4,box5,box6,box7,box8,box9));
        tiles = new ArrayList<>(Arrays.asList(tile1,tile2,tile3,tile4,tile5,tile6,tile7,tile8,tile9));

        tiles.forEach(stackPane -> stackPane.setMaxSize(100,100));
        tiles.forEach(stackPane -> stackPane.setDisable(true));

        int i = 0;
        for(int row = 0; row < 3; row++) {
            for(int col = 0; col < 3; col++) {
                tiles.get(i).setTranslateX((col * 100) - 100);
                tiles.get(i).setTranslateY((row * 100) - 100);
                i++;
            }
        }
        winningLine.setVisible(false);

        for(int index = 0; index < 9; index++) {
            Rectangle r = (Rectangle) tiles.get(index).getChildren().get(0);
            r.setFill(Color.TRANSPARENT);
        }
    }

    @FXML
    void startingGame(ActionEvent event) {
        startButton.setVisible(false);
        tiles.forEach(stackPane -> stackPane.setDisable(false));
        box.forEach(label -> label.setText(""));
        winningLine.setVisible(false);
        gameLabel.setText("Player " + playerTurn + "'s turn");
        if (playerTurn == 'X') setComputerMove();
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
        if (playerTurn == 'X') {
            label.setText("X");
            playerTurn = 'O';
        } else {
            label.setText("O");
            playerTurn = 'X';
            if (!fullBoard()) setComputerMove();
        }
        gameLabel.setText("Player " + playerTurn + "'s turn");
    }

    public boolean fullBoard() {
        return !Objects.equals(box1.getText(), "") &&
               !Objects.equals(box2.getText(), "") &&
               !Objects.equals(box3.getText(), "") &&
               !Objects.equals(box4.getText(), "") &&
               !Objects.equals(box5.getText(), "") &&
               !Objects.equals(box6.getText(), "") &&
               !Objects.equals(box7.getText(), "") &&
               !Objects.equals(box8.getText(), "") &&
               !Objects.equals(box9.getText(), "");
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
                ScoreBoardX.setText("X-Wins: " + ++xWin);
                gameEnd(winningLabels);
                a=7;
            }
            //O winner
            else if (line.equals("OOO")) {
                gameLabel.setText("O won!");
                ScoreBoardO.setText("O-Wins: " + ++oWin);
                gameEnd(winningLabels);
                a=7;
            }
            // Draw
            else if(fullBoard() && a == 7) {
                gameLabel.setText("There is no winner");
                ScoreBoardDraw.setText("Draws: " + ++draw);
                gameEnd(null);
            }
        }
    }

    @FXML
    public void switchMenuScene(ActionEvent event) throws IOException {
        FXMLLoader root = new FXMLLoader(Main.class.getResource("TitleScreen.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root.load(), 600,500);
        stage.setScene(scene);
    }

    @FXML
    public void resetBoard(ActionEvent actionEvent) {
        gameLabel.setText("Tic-Tac-Toe");
        tiles.forEach(stackPane -> stackPane.setDisable(false));
        box.forEach(label -> label.setText(""));
        winningLine.setVisible(false);
        gameEnd(null);
    }

    @FXML
    public void resetScore(ActionEvent actionEvent) {
        ScoreBoardO.setText("O-Wins: 0");
        ScoreBoardX.setText("X-Wins: 0");
        ScoreBoardDraw.setText("Draws: 0");
        oWin = xWin = draw = 0;
    }

    public void setComputerMove() {
        String[] boardState = new String[9];
        int move;

        for (int i = 0; i < 9; ++i)
            boardState[i] = box.get(i).getText();

        move = getComputerMove(boardState);

        setPlayerSymbol(box.get(move));
        tiles.get(move).setDisable(true);
        checkIfGameIsOver();
    }
}
