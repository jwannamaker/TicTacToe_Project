package com.tictactoe;

import java.util.ArrayList;

public class BoardState {
    private char[] board;
    private char startingPlayer;
    private char player;
    private boolean twoPlayer;
    private boolean localGame;
    private ArrayList<BoardUI> boardUIs;
    private GameController controller;
    private MiniMax computerPlayer;

    public BoardState() {
        board = new char[9];
        startingPlayer = 'X';
        player = startingPlayer;
        twoPlayer = TitleController.getTwoPlayer();
        localGame = TitleController.getLocalGame();
        boardUIs = new ArrayList<>();
        controller = new GameController();
        computerPlayer = new MiniMax();
    }

    public char[] getBoard() {
        return board;
    }

    public void setBoard(char[] board) {
        System.arraycopy(board, 0, this.board, 0, 9);
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

    public void register(BoardUI boardUI) {
        boardUIs.add(boardUI);
    }

    public void unregister(BoardUI boardUI) {
        boardUIs.remove(boardUI);
    }

    public void notifyObservers(int i, char state) {
        for (BoardUI board: boardUIs) board.update(i, state);
    }

    public void getUpdate(int move, int arg) {
        if (arg == 0) { // starting game arg
            if (player == 'X' && !twoPlayer) {
                move = computerPlayer.getMove(board);
                board[move] = 'X';
                char state = controller.checkIfGameOver(board);
                player = controller.changePlayer(player);
                notifyObservers(move, state);
            }
        } else if (arg == 1) { // player move arg
            if (player == 'X' && !twoPlayer) {
                move = computerPlayer.getMove(board);
                board[move] = 'X';
                char state = controller.checkIfGameOver(board);
                player = controller.changePlayer(player);
                notifyObservers(move, state);
            } else {
                board[move] = player;
                char state = controller.checkIfGameOver(board);
                player = controller.changePlayer(player);
                notifyObservers(move, state);
                if (!controller.isFullBoard(board) && !twoPlayer) getUpdate(0,1);
            }
        } else if (arg == 2) { // reset board arg
            controller.clearBoard(board);
        } else if (arg == 3) { // game end arg
            controller.clearBoard(board);
            startingPlayer = controller.changeStartingPlayer(startingPlayer);
            player = startingPlayer;
        }
    }
}
