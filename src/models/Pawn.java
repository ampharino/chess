package models;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private boolean firstMove;

    public Pawn(int row, int col, int color) {
        super(row, col, color);
        this.firstMove = true;
    }

    public boolean isFirstMove() {
        return this.firstMove;
    }

    private void setNotFirstMove() {
        this.firstMove = false;
    }



    //Check if the pawn is allowed to move two spaces
    private boolean validFirstMove(Board board, int newRow, int newCol) {
        if (this.isFirstMove() == false) {
            return false;
        }
        if (board.getTile(newRow, newCol).isOccupied()) {
            return false;
        }
        if (newCol != this.getColumn()) {
            return false;
        }
        return true;
    }


    //Check if the pawn is allowed to move to destination row
    private boolean rowChecker(Board board, int newRow, int newCol, int color) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if (color == BLACK) {
            if (newRow < currRow) {
                return false;
            }
            if (newRow == currRow + 2) {
                if (!validFirstMove(board, newRow, newCol)) {
                    return false;
                }
            }
            if (newRow > currRow + 2) {
                return false;
            }
        }
        else if (color == WHITE) {
            if (newRow > currRow) {
                return false;
            }
            if (newRow == currRow - 2) {
                if (!validFirstMove(board, newRow, newCol)) {
                    return false;
                }
            }
            if (newRow < currRow - 2) {
                return false;
            }
        }
        return true;
    }

    @Override
    //-------------------------------------------------------------------------------------------------
    //Verifies that pawn can legally move to given row and column
    //Allow diagonal moves only if space occupied by opponent piece
    //-------------------------------------------------------------------------------------------------
    public boolean validMove(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if(outOfBounds(newRow, newCol)){
            return false;
        }
        if(noMove(newRow, newCol)){
            return false;
        }
        if(this.getColor() == BLACK){
            if(!rowChecker(board, newRow, newCol, BLACK)){
                return false;
            }
        }
        else{
            if(!rowChecker(board, newRow, newCol, WHITE)){
                return false;
            }
        }
        if (Math.abs(newCol - currCol) > 1) {
            return false;
        }
        if (newCol == currCol) {
            if (board.getTile(newRow, newCol).isOccupied()) {
                return false;
            }
        }
        if (Math.abs(newCol - currCol) == 1) {
            if (!board.getTile(newRow, newCol).isOccupied()) {
                return false;
            }
            if(board.getPieceColor(newRow,newCol) == this.getColor()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Tile> getValidMoves(Board board) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        List<Tile> validMoves = new ArrayList<>();
        if(this.getColor() == BLACK){
            if(validMove(board,currRow+1,currCol)){
                validMoves.add(board.getTile(currRow+1,currCol));
            }
            if(validMove(board,currRow+2,currCol)){
                validMoves.add(board.getTile(currRow+2,currCol));
            }
            if(validMove(board,currRow+1,currCol+1)){
                validMoves.add(board.getTile(currRow+1,currCol+1));
            }
            if(validMove(board,currRow+1,currCol-1)){
                validMoves.add(board.getTile(currRow+1,currCol-1));
            }
        }
        else{
            if(validMove(board,currRow-1,currCol)){
                validMoves.add(board.getTile(currRow-1,currCol));
            }
            if(validMove(board,currRow-2,currCol)){
                validMoves.add(board.getTile(currRow-2,currCol));
            }
            if(validMove(board,currRow-1,currCol+1)){
                validMoves.add(board.getTile(currRow-1,currCol+1));
            }
            if(validMove(board,currRow-1,currCol-1)){
                validMoves.add(board.getTile(currRow-1,currCol-1));
            }

        }
        return validMoves;
    }


    @Override
    //-------------------------------------------------------------------------------------------------
    //Move to destination, capturing enemy piece if space was occupied
    //Check if move puts own king in check
    //If so, undo move and restore state
    //Otherwise update new position and pawn's first move status
    //-------------------------------------------------------------------------------------------------
    public boolean move(Board board, int newRow, int newCol) {
        if(validMove(board, newRow, newCol)){
            Tile curr = board.getTile(this.getRow(),this.getColumn());
            Tile dest = board.getTile(newRow, newCol);
            Piece temp = dest.getPiece();
            King king;
            if(this.getColor() == BLACK){
                king = (King)board.getBlackPieces().get(0);
            }
            else{
                king = (King)board.getWhitePieces().get(0);
            }
            if(dest.isOccupied()){
                Piece removedPiece = dest.getPiece();
                capturePiece(board, removedPiece, dest);
            }
            dest.setPiece(this);
            curr.setPiece(null);
            if(king.isKingChecked(board)){
                System.out.println("Cannot leave king in check");
                dest.setPiece(temp);
                curr.setPiece(this);
                if(this.getColor() == BLACK && temp != null){
                    board.getWhitePieces().add(temp);
                }
                else if(temp != null){
                    board.getBlackPieces().add(temp);
                }
                return false;
            }
            if(isFirstMove()){
                this.setNotFirstMove();
            }
            setNewPos(newRow,newCol);
            return true;

        }
        return false;
    }
}


