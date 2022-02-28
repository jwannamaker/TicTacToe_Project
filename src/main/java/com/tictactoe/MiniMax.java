package com.tictactoe;

import javafx.scene.control.Label;

import java.util.List;
import java.util.Objects;

public class MiniMax {
    Boolean movesLeft(List<Label> box) {
        for (int i = 0; i < 9; ++i)
            if (Objects.equals(box.get(i).getText(), ""))
                return true;
        return false;
    }

    public int evaluate(List<Label> box) {
        for (int i = 0; i < 3; ++i) {
            if (Objects.equals(box.get(3 * i).getText(), box.get(3 * i + 1).getText()) &&
                    Objects.equals(box.get(3 * i).getText(), box.get(3 * i + 2).getText())) {
                if (Objects.equals(box.get(3 * i).getText(), "X"))
                    return 10;
                else if (Objects.equals(box.get(3 * i).getText(), "O"))
                    return -10;
            }
            if (Objects.equals(box.get(i).getText(), box.get(3 + i).getText()) &&
                    Objects.equals(box.get(i).getText(), box.get(6 + i).getText())) {
                if (Objects.equals(box.get(i).getText(), "X"))
                    return 10;
                else if (Objects.equals(box.get(i).getText(), "O"))
                    return -10;
            }
        }
        if (Objects.equals(box.get(0).getText(), box.get(4).getText()) &&
                Objects.equals(box.get(0).getText(), box.get(8).getText())) {
            if (Objects.equals(box.get(0).getText(), "X"))
                return 10;
            else if (Objects.equals(box.get(0).getText(), "O"))
                return -10;
        }
        if (Objects.equals(box.get(6).getText(), box.get(4).getText()) &&
                Objects.equals(box.get(6).getText(), box.get(2).getText())) {
            if (Objects.equals(box.get(6).getText(), "X"))
                return 10;
            else if (Objects.equals(box.get(6).getText(), "O"))
                return -10;
        }
        return 0;
    }


    int miniMax(List<Label> box, int depth, Boolean isMax) {
        int score = evaluate(box);

        if (score == 10) return score;
        if (score == -10) return score;
        if (!movesLeft(box)) return 0;

        if (isMax) {
            int maxEval = -999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(box.get(i).getText(), "")) {
                    box.get(i).setText("X");
                    maxEval = Math.max(maxEval, miniMax(box, depth + 1, false));
                    box.get(i).setText("");
                }
            }
            return maxEval;
        } else {
            int minEval = 999;
            for (int i = 0; i < 9; ++i) {
                if (Objects.equals(box.get(i).getText(), "")) {
                    box.get(i).setText("O");
                    minEval = Math.min(minEval, miniMax(box, depth + 1, true));
                    box.get(i).setText("");
                }
            }
            return minEval;
        }
    }

    public void computerPlayerMove(List<Label> box) {
        int maxEval = -999;
        int pos = -1;

        for (int i = 0; i < 9; ++i) {
            if (Objects.equals(box.get(i).getText(), "")) {
                box.get(i).setText("X");
                int moveEval = Math.max(maxEval, miniMax(box, 0, false));
                box.get(i).setText("");

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