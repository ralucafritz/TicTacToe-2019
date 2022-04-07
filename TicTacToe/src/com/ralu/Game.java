package com.ralu;


import java.util.Scanner;

public class Game {
    
    private Board board;
    private Player[] players;
    private Scanner s = new Scanner(System.in);
    
    public Game(Board board, Player playerO, Player playerX) {
        this.board = board;
        players = new Player[]{playerO, playerX};
        System.out.println("Start!");
        board.print();
    }
    
    public boolean playGame() {
        int turn = board.getVal();
        System.out.println(players[turn].getName() + " choose your move");
        int choice = s.nextInt();
        boolean checkwin = board.add(choice);
        board.print();
        
        if (checkwin) {
            System.out.println(" WINNER WINNER CHICKEN DINNER \n " + pickWinner().getName() + " is the winner \n");
            return true;
        } else if (board.checkFull() && !checkwin) {
            System.out.println("It's a TIE!");
            return true;
        }
        return false;
    }
    
    public boolean playAgain() {
        System.out.println("Do you want to play again?");
        String answer = s.next();
        return "yes".equalsIgnoreCase(answer);
    }
    
    
    public Player pickWinner() {
        
        if (board.checkTurns() == 1) {
            return players[0];
        } else if (board.checkTurns() == 0) {
            return players[1];
        }
        
        return null;
        
    }
    
}
