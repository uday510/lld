package com.app.patterns.creational.prototype;

public class PrototypeMain {

    public static void main(String[] args) {

        GameBoard gameBoard = new GameBoard();

        gameBoard.addPiece(new GamePiece("Blue", 1));
        gameBoard.addPiece(new GamePiece("White", 1));

        GameBoard clonedGameBoard = gameBoard.clone();
        clonedGameBoard.showBoardState();
    }
}
