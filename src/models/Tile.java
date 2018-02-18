package models;

public class Tile {
    private final int row;
    private final int column;
    private Piece piece;

    // initialize tile on board in given row
    public Tile(int row, int column){
        this.row = row;
        this.column = column;
        this.piece = null;
    }

    public int getRow(){
        return this.row;
    }

    public int getColumn(){
        return this.column;
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece newPiece){
        this.piece = newPiece;
    }

    public void removePiece(){
        this.piece = null;
    }

    public boolean isOccupied(){
        if(this.piece == null){
            return false;
        }
        else{
            return true;
        }
    }






}
