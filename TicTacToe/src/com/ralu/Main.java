package com.ralu;

import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) throws Exception {
        
        Scanner s = new Scanner(System.in);
        boolean play = true;
        while (play) {
            
            System.out.println("Choose the size of the Tic-Tac-Toe:");
            System.out.println("Note: the size should be 3 <= size < 10 for the game to work!");
            int number = 0;
            number = s.nextInt();
            Board board = new Board(number);
            boolean checkWin = false;
            
            System.out.println("Player 1, you will play with O. Insert your name");
            String player1 = s.next();
            System.out.println("Player 2, you will play with X. Insert your name");
            String player2 = s.next();
            
            Game game = new Game(board, new Player(player1), new Player(player2));
            while (!checkWin) {
                checkWin = game.playGame();
            }
            
            play = game.playAgain();
        }
    }
}
