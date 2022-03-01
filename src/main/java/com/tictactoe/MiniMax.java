package com.tictactoe;
import javafx.scene.control.Label;
import java.util.List;
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

    public void computerPlayerMove(List<Label> box) {
        String[] boardState = new String[9];

        for (int i = 0; i < 9; ++i)
            boardState[i] = box.get(i).getText();

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
        System.out.print("X chooses position: ");
        switch (pos) {
            case 0 -> System.out.println("0,0");
            case 1 -> System.out.println("0,1");
            case 2 -> System.out.println("0,2");
            case 3 -> System.out.println("1,0");
            case 4 -> System.out.println("1,1");
            case 5 -> System.out.println("1,2");
            case 6 -> System.out.println("2,0");
            case 7 -> System.out.println("2,1");
            case 8 -> System.out.println("2,2");
            default -> System.out.println("No Moves Remaining.");
        }
    }
}