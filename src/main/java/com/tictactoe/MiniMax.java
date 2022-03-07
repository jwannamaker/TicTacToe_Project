package com.tictactoe;

import java.util.Objects;

public class MiniMax {
    Boolean isMovesLeft(String[] boardState) {
        for (int i = 0; i < 9; ++i)
            if (Objects.equals(boardState[i], ""))
                return true;
        return false;
    }

    public int evaluate(String[] boardState) {
        for (int i = 0; i < 3; ++i) {
            if (Objects.equals(boardState[3 * i], boardState[3 * i + 1]) &&
                    Objects.equals(boardState[3 * i], boardState[3 * i + 2])) {
                if (Objects.equals(boardState[3 * i], "X"))
                    return 10;
                else if (Objects.equals(boardState[3 * i], "O"))
                    return -10;
            }
            if (Objects.equals(boardState[i], boardState[3 + i]) &&
                    Objects.equals(boardState[i], boardState[6 + i])) {
                if (Objects.equals(boardState[i], "X"))
                    return 10;
                else if (Objects.equals(boardState[i], "O"))
                    return -10;
            }
        }
        if (Objects.equals(boardState[0], boardState[4]) &&
                Objects.equals(boardState[0], boardState[8])) {
            if (Objects.equals(boardState[0], "X"))
                return 10;
            else if (Objects.equals(boardState[0], "O"))
                return -10;
        }
        if (Objects.equals(boardState[6], boardState[4]) &&
                Objects.equals(boardState[6], boardState[2])) {
            if (Objects.equals(boardState[6], "X"))
                return 10;
            else if (Objects.equals(boardState[6], "O"))
                return -10;
        }
        return 0;
    }

    int miniMax(String[] boardState, Boolean isMax, int alpha, int beta) {
        int score = evaluate(boardState);

        if (score != 0 || !isMovesLeft(boardState))
            return score;

        if (isMax) {
            int maxEval = -999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(boardState[i], "")) {
                    boardState[i] = "X";
                    maxEval = Math.max(maxEval, miniMax(boardState, false, alpha, beta));
                    alpha = Math.max(alpha, maxEval);
                    boardState[i] = "";

                    if (beta <= alpha) break;
                }
            }
            return maxEval;
        } else {
            int minEval = 999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(boardState[i], "")) {
                    boardState[i] = "O";
                    minEval = Math.min(minEval, miniMax(boardState, true, alpha, beta));
                    beta = Math.min(beta, minEval);
                    boardState[i] = "";

                    if (beta <= alpha) break;
                }
            }
            return minEval;
        }
    }

    public int getComputerMove(String[] boardState) {
        int maxEval = -999;
        int pos = -1;

        for (int i = 0; i < 9; ++i) {
            if (Objects.equals(boardState[i], "")) {
                boardState[i] = "X";
                int moveEval = miniMax(boardState, false, -999, 999);
                boardState[i] = "";

                if (moveEval > maxEval) {
                    pos = i;
                    maxEval = moveEval;
                }
            }
        }
        return pos;
    }
}