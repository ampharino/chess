package models;

import java.util.List;

public abstract class Piece {
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    private int row;
    private int column;
    private final int color;

    public Piece(int row, int col, int color){
        this.row = row;
        this.column = col;
        this.color = color;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public int getColor(){
        return this.color;
    }
    public void setNewPos(int row, int col){
        this.row = row;
        this.column = col;
    }

    //-------------------------------------------------------------------------------------------------
    //Remove given piece from list of pieces in play
    //Set piece to null
    //-------------------------------------------------------------------------------------------------

    public void capturePiece(Board board, Piece removedPiece, Tile target){
        if(this.getColor() == BLACK) {
            board.getWhitePieces().remove(removedPiece);
        }
        else {
            board.getBlackPieces().remove(removedPiece);

        }
        target.removePiece();

    }
    public boolean outOfBounds(int newRow, int newCol){
        if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
            return true;
        }
        return false;
    }
    public boolean noMove(int newRow, int newCol){
        if(newRow == this.getRow() && newCol == this.getColumn()){
            return true;
        }
        return false;
    }

    public abstract boolean validMove(Board board, int newRow, int newCol);
    public abstract List<Tile> getValidMoves(Board board);


    //-------------------------------------------------------------------------------------------------
    //Move to destination and capture enemy piece is space is occupied
    //If move puts king in check, undo move and restore state
    //-------------------------------------------------------------------------------------------------
    public boolean move(Board board, int newRow, int newCol){
        if(validMove(board, newRow, newCol)){
            Tile curr = board.getTile(this.getRow(),this.getColumn());
            Tile dest = board.getTile(newRow, newCol);
            Piece temp = dest.getPiece();
            King king;
            if(color == BLACK){
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
                if(color == BLACK && temp != null){
                    board.getWhitePieces().add(temp);
                }
                else if(temp != null){
                    board.getBlackPieces().add(temp);
                }
                return false;
            }
            setNewPos(newRow,newCol);
            return true;
        }
        return false;

    }


    //Simulates move and undo it right after
    public boolean tryMove(Board board, int newRow, int newCol){
        if(validMove(board, newRow, newCol)){
            Tile curr = board.getTile(this.getRow(),this.getColumn());
            Tile dest = board.getTile(newRow, newCol);
            Piece temp = dest.getPiece();
            King king;
            if(color == BLACK){
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
                if(color == BLACK && temp != null){
                    board.getWhitePieces().add(temp);
                }
                else if(temp != null){
                    board.getBlackPieces().add(temp);
                }
                return false;
            }
            dest.setPiece(temp);
            curr.setPiece(this);
            if(color == BLACK && temp != null){
                board.getWhitePieces().add(temp);
            }
            else if(temp != null){
                board.getBlackPieces().add(temp);
            }
            return true;
        }
        return false;
    }
}
