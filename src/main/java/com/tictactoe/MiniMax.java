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

    int miniMax(String[] boardState, int depth, Boolean isMax) {
        int score = evaluate(boardState);

        if (score == 10) return score;
        if (score == -10) return score;
        if (!isMovesLeft(boardState)) return 0;

        if (isMax) {
            int maxEval = -999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(boardState[i], "")) {
                    boardState[i] = "X";
                    maxEval = Math.max(maxEval, miniMax(boardState, depth + 1, false));
                    boardState[i] = "";
                }
            }
            return maxEval;
        } else {
            int minEval = 999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(boardState[i], "")) {
                    boardState[i] = "O";
                    minEval = Math.min(minEval, miniMax(boardState, depth + 1, true));
                    boardState[i] = "";
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
                int moveEval = Math.max(maxEval, miniMax(boardState, 0, false));
                boardState[i] = "";

                if (moveEval > maxEval)
                    pos = i;
                    maxEval = moveEval;
            }
        }
        return pos;
    }
}