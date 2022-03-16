package com.tictactoe;

public class MiniMax {
    private GameController gameController = new GameController();

    private Boolean isMovesLeft(char[] boardState) {
        return !gameController.isFullBoard(boardState);
    }

    private int evaluate(char[] boardState) {
        return switch (gameController.checkIfGameOver(boardState)) {
            case 'X' -> 10;
            case 'O' -> -10;
            default -> 0;
        };
    }

    private int miniMax(char[] boardState, Boolean isMax, int alpha, int beta) {
        int score = evaluate(boardState);
        if (score != 0) return score;
        if (!isMovesLeft(boardState)) return score;

        if (isMax) {
            int maxEval = -999;
            for (int i = 0; i < 9; ++i) {
                if (boardState[i] == 0) {
                    boardState[i] = 'X';
                    maxEval = Math.max(maxEval, miniMax(boardState, false, alpha, beta));
                    alpha = Math.max(alpha, maxEval);
                    boardState[i] = 0;
                    if (beta <= alpha) break;
                }
            } return maxEval;
        } else {
            int minEval = 999;
            for (int i = 0; i < 9; ++i) {
                if (boardState[i] == 0) {
                    boardState[i] = 'O';
                    minEval = Math.min(minEval, miniMax(boardState, true, alpha, beta));
                    beta = Math.min(beta, minEval);
                    boardState[i] = 0;
                    if (beta <= alpha) break;
                }
            } return minEval;
        }
    }

    public int getMove(char[] boardState) {
        int maxEval = -999;
        int pos = -1;

        for (int i = 0; i < 9; ++i) {
            if (boardState[i] == 0) {
                boardState[i] = 'X';
                int moveEval = miniMax(boardState, false, -999, 999);
                boardState[i] = 0;

                if (moveEval > maxEval) {
                    pos = i;
                    maxEval = moveEval;
                }
            }
        }
        return pos;
    }
}