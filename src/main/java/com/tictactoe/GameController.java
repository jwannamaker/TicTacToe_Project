package com.tictactoe;

public class GameController {
    public char changePlayer (char player) {
        return player == 'X' ? 'O' : 'X';
    }

    public char changeStartingPlayer (char startingPlayer) {
        return startingPlayer == 'X' ? 'O' : 'X';
    }

    public boolean isFullBoard(char [] boardState) {
        return !(boardState[0] == 0) && !(boardState[1] == 0) && !(boardState[2] == 0) &&
               !(boardState[3] == 0) && !(boardState[4] == 0) && !(boardState[5] == 0) &&
               !(boardState[6] == 0) && !(boardState[7] == 0) && !(boardState[8] == 0);
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

    public void clearBoard(char [] board) {
        for (int i = 0; i < 9; ++i) board[i] = 0;
    }



}
