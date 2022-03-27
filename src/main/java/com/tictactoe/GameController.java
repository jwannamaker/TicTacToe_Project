package com.tictactoe;

public class GameController {
    private char startingPlayer;
    private char player;
    private boolean twoPlayer;
    private boolean localGame;

    public GameController() {
        startingPlayer = 'X';
        player = startingPlayer;
        twoPlayer = TitleController.getTwoPlayer();
        localGame = TitleController.getLocalGame();
    }

    public void changePlayer () {
        player = player == 'X' ? 'O' : 'X';
    }

    public void changeStartingPlayer () {
        startingPlayer = startingPlayer == 'X' ? 'O' : 'X';
    }

    public boolean isFullBoard(char [] boardState) {
        return !(boardState[0] == 0) && !(boardState[1] == 0) && !(boardState[2] == 0) &&
                !(boardState[3] == 0) && !(boardState[4] == 0) && !(boardState[5] == 0) &&
                !(boardState[6] == 0) && !(boardState[7] == 0) && !(boardState[8] == 0);
    }

    public boolean isGameOver(char [] boardState) {
        return checkIfGameOver(boardState) != 'N';
    }

    public char checkIfGameOver(char [] boardState) {
        for (int i = 0; i < 3; ++i) {
            if (boardState[3 * i] == boardState[3 * i + 1] && boardState[3 * i] == boardState[3 * i + 2]) {
                if (boardState[3 * i] == 'X') return 'X';
                else if (boardState[3 * i] == 'O') return 'O';
            }
            if (boardState[i] == boardState[3 + i] && boardState[i] == boardState[6 + i]) {
                if (boardState[i] == 'X') return 'X';
                else if (boardState[i] == 'O') return 'O';
            }
        }
        if (boardState[0] == boardState[4] && boardState[0] == boardState[8]) {
            if (boardState[0] == 'X') return 'X';
            else if (boardState[0] == 'O') return 'O';
        }
        if (boardState[6] == boardState[4] && boardState[6] == boardState[2]) {
            if (boardState[6] == 'X') return 'X';
            else if (boardState[6] == 'O') return 'O';
        }
        if (isFullBoard(boardState)) return 'D';
        else return 'N';
    }

    public char getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(char startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public boolean getTwoPlayer() {
        return twoPlayer;
    }

    public boolean getLocalGame() {
        return localGame;
    }
}
