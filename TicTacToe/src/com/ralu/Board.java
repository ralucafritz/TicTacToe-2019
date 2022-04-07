package com.ralu;

public class Board {
    
    private final int rows;
    private static final int empty = -1;
    private static final int o = 0;
    private static final int x = 1;
    private int numberTurns = 0;
    private int val = 0;
    private int[][] boardState;
    
    public Board(int number) throws Exception {
        if (number >= 3 && number < 10) {
            this.rows = number;
            this.boardState = new int[rows][rows];
            reset();
        } else {
            throw new Exception("Invalid size!");
            
        }
    }
    
    public boolean add(int number) {
        // number can only be 11 / 12 / 13 / 21 / 22 / 23 / 31 / 32 / 33
        int coordX = number / 10 - 1; // first digit
        int coordY = number % 10 - 1; // second digit
        
        if (coordX < boardState.length && coordX >= 0 && coordY >= 0 && coordY < boardState.length)  // check value is valid & position exists
        {
            if (boardState[coordX][coordY] == Board.empty) {
                boardState[coordX][coordY] = val;
                if (val == 0) {
                    val++;
                } else {
                    val--;
                }
                numberTurns++;
                if (checkDiag() || checkRows() || checkCols()) {
                    return true;
                }
            } else {
                if (val == 0) {
                    
                    System.out.println("Cannot add O to that position");
                } else {
                    System.out.println("Cannot add X to that position");
                }
            }
        } else {
            System.out.println("Invalid position");
        }
        
        return false;
    }
    
    public boolean checkFull() {
        return numberTurns == boardState.length * boardState.length;
    }
    
    public int checkTurns() {
        return numberTurns % 2;
    }
    
    public boolean checkDiag() {
        int countDiag1 = 0;
        int countDiag2 = 0;
        int max = boardState.length;
        
        if (boardState[0][0] != Board.empty) {
            for (int i = 1, j = 1; i < max && j < max; i++, j++) {
                if (boardState[i][j] == boardState[0][0]) {
                    countDiag1++;
                } else {
                    break;
                }
            }
            if (countDiag1 == max - 1) {
                return true;
            }
        }
        
        if (boardState[max - 1][0] != Board.empty) {
            for (int i = max - 2, j = 1; i >= 0 && j < max; i--, j++) {
                if (boardState[i][j] == boardState[max - 1][0]) {
                    countDiag2++;
                } else {
                    break;
                }
            }
            
            return countDiag2 == max - 1;
        }
        
        return false;
        
    }
    
    public boolean checkRows() {
        for (int i = 0; i < boardState.length; i++) {
            int count = 0;
            if (boardState[i][0] > Board.empty) {
                for (int j = 1; j < boardState.length; j++) {
                    if (boardState[i][j] == boardState[i][0]) {
                            count++;
                    } else {
                        break;
                    }
                }
            }
            if (count == boardState.length - 1) {
                return true;
            }
        }
        return false;
    }
    
    public boolean checkCols() {
        for (int i = 0; i < boardState.length; i++) {
            int count = 0;
            if (boardState[0][i] > Board.empty) {
                for (int j = 1; j < boardState.length; j++) {
                    if (boardState[j][i] == boardState[0][i]) {
                        count++;
                    } else {
                        break;
                    }
                }
            }
            if (count == boardState.length - 1) {
                return true;
            }
        }
        return false;
    }
    
    public void reset() {
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                boardState[i][j] = Board.empty;
            }
        }
        System.out.println("Board cleared.");
    }
    
    public void print() {
        String horizontal = "\u2501".repeat(7 * boardState.length - 1);
//        String vertical = "\u2502";
        System.out.println("Tic Tac Toe \n" +
                "\u250F" + horizontal + "\u2513");
        
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[i].length; j++) {
                int printElement = boardState[i][j];
                if (printElement == -1) {
                    System.out.print("\u258E      ");
                } else if (printElement == 1) {
                    System.out.print("\u258E  X   ");
                } else {
                    System.out.print("\u258E  O   ");
                }
            }
            System.out.print("\u258E \n");
            if (i != boardState.length - 1) {
                System.out.println("\u2523" + horizontal + "\u252B");
            } else {
                System.out.println("\u2517" + horizontal + "\u251B");
            }
        }
    }
    
    public int getVal() {
        return val;
    }
    
}

