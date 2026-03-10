package com.app.patterns.creational.prototype;

import java.util.ArrayList;
import java.util.List;

public class GameBoard implements Prototype<GameBoard> {

    private List<GamePiece> pieces = new ArrayList<>();

    public void addPiece(GamePiece gamePiece) {
        pieces.add(gamePiece);
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public void showBoardState() {
        System.out.println("Current Board ");
        for (GamePiece piece : pieces) {
            System.out.println(piece.toString());
        }
    }

    @Override
    public GameBoard clone() {
        GameBoard cloneGameBoard =  new GameBoard();
        for (GamePiece gamePiece : pieces) {
            cloneGameBoard.addPiece(gamePiece.clone());
        }
        return cloneGameBoard;
    }

}
