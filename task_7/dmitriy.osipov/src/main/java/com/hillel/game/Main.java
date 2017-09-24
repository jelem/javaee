package com.hillel.game;

public class Main {

  public static void main(String[] args) {

    TicTacToe ticTacToe = new TicTacToe(new Board(), new PlayerHuman("John", 'X'),
        new PlayerPc("T100", 'O'));
    ticTacToe.startGame();

  }
}
