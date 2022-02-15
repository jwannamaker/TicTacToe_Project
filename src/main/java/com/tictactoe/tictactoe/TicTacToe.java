package com.tictactoe.tictactoe;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TicTacToe extends Application {

    private List<WinCondition> winConditions = new ArrayList<>();
    private boolean playable = true;
    private Tile[][] board = new Tile[3][3];
    private Pane pane = new Pane();

    private Parent Board() {
        pane.setPrefSize(600,600);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 200);
                tile.setTranslateY(i * 200);

                pane.getChildren().add(tile);
                board[j][i] = tile;
            }
        }

        for (int i = 0; i < 3; ++i) {
            winConditions.add(new WinCondition(board[0][i], board[1][i], board[2][i]));
            winConditions.add(new WinCondition(board[i][0], board[i][1], board[i][2]));
        }
        winConditions.add(new WinCondition(board[0][0], board[1][1], board[2][2]));
        winConditions.add(new WinCondition(board[2][0], board[1][1], board[0][2]));

        return pane;
    }

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Tic-Tac-Toe Game");
        stage.setScene(new Scene(Board()));
        stage.show();
    }

    private void checkState() {
        for (WinCondition winCondition : winConditions) {
            if (winCondition.isComplete()) {
                playable = false;
                showGameResult(winCondition);
                break;
            }
        }
    }

    private void showGameResult(WinCondition winCondition) {
        Line line = new Line();
        line.setStrokeWidth(5);
        line.setStartX(winCondition.tiles[0].getCenterX());
        line.setStartY(winCondition.tiles[0].getCenterY());
        line.setEndX(winCondition.tiles[0].getCenterX());
        line.setEndY(winCondition.tiles[0].getCenterY());

        pane.getChildren().add(line);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1),
                new KeyValue(line.endXProperty(), winCondition.tiles[2].getCenterX()),
                new KeyValue(line.endYProperty(), winCondition.tiles[2].getCenterY())));
        timeline.play();
    }

    private class WinCondition {
        private Tile[] tiles;
        public WinCondition(Tile... tiles) {
            this.tiles = tiles;
        }

        public boolean isComplete() {
            if (tiles[0].getValue().isEmpty()) return false;
            return tiles[0].getValue().equals(tiles[1].getValue())
                    && tiles[0].getValue().equals(tiles[2].getValue());
        }
    }

    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(200,200);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(125));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event-> {
                if (playable) {
                    if (move >= 9) {
                        playable = false;
                        return;
                    }
                    if (move % 2 == 0) drawX();
                    else drawO();
                    ++move;
                    checkState();
                }
            });
        }

        public double getCenterX() {
            return getTranslateX() + 100;
        }
        public double getCenterY() {
            return getTranslateY() + 100;
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() { text.setText("X"); }
        private void drawO() { text.setText("O"); }
        private static int move = 0;
    }

    public static void main(String[] args) {
        launch(args);
    }
}