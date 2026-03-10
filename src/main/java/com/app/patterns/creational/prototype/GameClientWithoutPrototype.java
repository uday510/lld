package com.app.patterns.creational.prototype;

public class GameClientWithoutPrototype {

    static void main(String[] args) {
        GameBoard gameBoard = new GameBoard();
        gameBoard.addPiece(new GamePiece("Blue", 1));
        gameBoard.addPiece(new GamePiece("White", 2));

        gameBoard.showBoardState();

        // Checkpoint this state
        GameBoard clonedBoard = new GameBoard();
        for (GamePiece piece : gameBoard.getPieces()) {
            clonedBoard.addPiece(new GamePiece(piece.getColor(), piece.getPosition()));
        }

        clonedBoard.showBoardState();

    }
}
