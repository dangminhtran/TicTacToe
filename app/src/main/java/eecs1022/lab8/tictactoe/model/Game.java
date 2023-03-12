package eecs1022.lab8.tictactoe.model;

public class Game {
    private String player1;
    private String player2;
    private String currentPlayer;
    private String status = "";
    private char[][] board = {
            {'_', '_', '_'},
            {'_', '_', '_'},
            {'_', '_', '_'}
    };

    public Game(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        setWhoPlaysFirst('x');
    }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public String getStatus() {
        return status;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setWhoPlaysFirst(char c) {
        if (c == 'o') {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        this.status = currentPlayer + "'s turn to play...";
    }

    public void swap() {
        if (getCurrentPlayer().equals(player1)) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
        status = currentPlayer + "'s turn to play...";
    }

    public boolean checkRow(char c) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            if (board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][1] == board[i][2] && board[i][0] == c && board[i][0] != '_') {
                result = true;
            }
        }
        return result;
    }

    public boolean checkColumn(char c) {
        boolean result = false;
        for (int i = 0; i < board.length; i++) {
            if (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[1][i] == board[2][i] && board[0][i] == c && board[0][i] != '_') {
                result = true;
            }
        }
        return result;
    }

    public boolean checkDiagonal(char c) {
        boolean result = false;
        if (board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == c && board[0][0] != '_') {
            result = true;
        } else if (board[2][0] == board[1][1] && board[2][0] == board[0][2] && board[2][0] == c && board[2][0] != '_') {
            result = true;
        }
        return result;
    }

    public boolean checkWin(char c) {
        boolean result = false;
        if (checkRow(c)) {
            result = true;
        } else if (checkColumn(c)) {
            result = true;
        } else if (checkDiagonal(c)) {
            result = true;
        }
        return result;
    }

    public boolean endGame() {
        boolean result = false;
        if (checkWin('x') || checkWin('o')) {
            result = true;
        }
        return result;
    }

    public boolean draw() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == '_') {
                    return false;
                }
            }
        }
        return true;
    }

    public void move(int row, int column) {
        if (endGame()) {
            this.status = "Error: game is already over with a winner.";
        } else if (draw()) {
            this.status = "Error: game is already over with a tie.";
        } else if (row < 1 || row > 3) {
            this.status = "Error: row " + row + " is invalid.";
        } else if (column < 1 || column > 3) {
            this.status = "Error: col " + column + " is invalid.";
        } else if (board[row - 1][column - 1] != '_') {
            this.status = "Error: slot @ (" + row + ", " + column + ") is already occupied.";
        } else {
            if (getCurrentPlayer().equals(player1)) {
                board[row - 1][column - 1] = 'x';
            } else if (getCurrentPlayer().equals(player2)) {
                board[row - 1][column - 1] = 'o';
            }
            if (checkWin('x')) {
                currentPlayer = null;
                this.status = "Game is over with " + player1 + " being the winner.";
            } else if (checkWin('o')) {
                currentPlayer = null;
                this.status = "Game is over with " + player2 + " being the winner.";
            } else if (draw()) {
                currentPlayer = null;
                this.status = "Game is over with a tie between " + player1 + " and " + player2 + ".";
            } else {
                swap();
            }
        }
    }
}
