package models;

import java.util.ArrayList;
import java.util.List;

public class Archbishop extends Piece {

    public Archbishop(int row, int col, int color){
        super(row, col, color);
    }

    private boolean upLeft(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        while (currRow != newRow+1) {
            currRow--;
            currCol--;
            if (board.getTile(currRow, currCol).isOccupied() &&
                    board.getTilePiece(currRow,currCol).getColor() != this.getColor()) {
                return false;
            }
        }
        return true;
    }

    //-------------------------------------------------------------------------------------------------
    //helper function to check for obstacles when moving diagonally up right
    //Starting from curr position, continuosly increment row and col until destination
    //Check if tile on way to destination is occupied
    //-------------------------------------------------------------------------------------------------
    private boolean upRight(Board board, int newRow, int newCol){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        while (currRow != newRow+1) {
            currRow--;
            currCol++;
            if (board.getTile(currRow, currCol).isOccupied() &&
                    board.getTilePiece(currRow,currCol).getColor() != this.getColor()) {
                return false;
            }
        }
        return true;
    }

    //-------------------------------------------------------------------------------------------------
    //helper function to check for obstacles when moving diagonally down left
    //Starting from curr position, continuosly increment row and col until destination
    //Check if tile on way to destination is occupied
    //-------------------------------------------------------------------------------------------------
    private boolean downLeft(Board board, int newRow, int newCol){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        while (currRow != newRow-1) {
            currRow++;
            currCol--;
            if (board.getTile(currRow, currCol).isOccupied() &&
                    board.getTilePiece(currRow,currCol).getColor() != this.getColor()) {
                return false;
            }
        }
        return true;
    }

    //-------------------------------------------------------------------------------------------------
    //helper function to check for obstacles when moving diagonally down right
    //Starting from curr position, continuosly increment row and col until destination
    //Check if tile on way to destination is occupied
    //-------------------------------------------------------------------------------------------------
    private boolean downRight(Board board, int newRow, int newCol){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        while (currRow != newRow-1) {
            currRow++;
            currCol++;
            if (board.getTile(currRow, currCol).isOccupied() &&
                    board.getTilePiece(currRow,currCol).getColor() != this.getColor()) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagObstacles(Board board, int newRow, int newCol){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if (newRow < currRow) {
            if (newCol < currCol) {
                if (upLeft(board, newRow, newCol) == false) {
                    return false;
                }

            }
            if (newCol > currCol) {
                if (upRight(board, newRow, newCol) == false) {
                    return false;
                }
            }

        }
        if (newRow > currRow) {
            if (newCol < currCol) {
                if (downLeft(board, newRow, newCol) == false) {
                    return false;
                }
            }
            if (newCol > currCol) {
                if (downRight(board, newRow, newCol) == false) {
                    return false;
                }
            }
        }
        return true;

    }
    @Override
    public boolean validMove(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if (outOfBounds(newRow, newCol)){
            return false;
        }
        if (noMove(newRow, newCol)){
            return false;
        }
        if (Math.abs(currRow-newRow) != Math.abs(currCol-newCol)){
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }

    @Override
    public List<Tile> getValidMoves(Board board) {
        List<Tile> diagMoves = new ArrayList<>();
        int currRow = this.getRow();
        int currCol = this.getColumn();
        int r = currRow;
        int c = currCol;
        while(true){
            r++;
            c++;
            if(validMove(board,r,c)){
                diagMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        r = currRow;
        c = currCol;
        while(true){
            r++;
            c--;
            if(validMove(board,r,c)){
                diagMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        r = currRow;
        c = currCol;
        while(true){
            r--;
            c--;
            if(validMove(board,r,c)){
                diagMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        r = currRow;
        c = currCol;
        while(true){
            r--;
            c++;
            if(validMove(board,r,c)){
                diagMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        return diagMoves;
    }
}
