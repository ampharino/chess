package models;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{

    public Queen(int row, int col, int color){

        super(row, col, color);
    }

    //-------------------------------------------------------------------------------------------------
    //helper function to check for obstacles when moving diagonally up left
    //Starting from curr position, continuosly increment row and col until destination
    //Check if tile on way to destination is occupied
    //-------------------------------------------------------------------------------------------------
    private boolean upLeft(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        while (currRow != newRow+1) {
            currRow--;
            currCol--;
            if (board.getTile(currRow, currCol).isOccupied()) {
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
            if (board.getTile(currRow, currCol).isOccupied()) {
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
            if (board.getTile(currRow, currCol).isOccupied()) {
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
            if (board.getTile(currRow, currCol).isOccupied()) {
                return false;
            }
        }
        return true;
    }

    //function to check if there are pieces in the way of destination tile
    public boolean checkDiagObstacles(Board board, int newRow, int newCol) {
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



    //-------------------------------------------------------------------------------------------------
    //Check for obstacles in straight paths
    //Starting from curr positon, increment row and col until destination
    //If at any point a tile is occupied, there is an obstacle
    //-------------------------------------------------------------------------------------------------
    public boolean checkObstacles(Board board, int newRow, int newCol){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if(newRow == currRow) {
            if (newCol < currCol) {
                for (int col = currCol - 1; col > newCol; col--) {
                    if (board.getTile(newRow, col).isOccupied()) {

                        return false;
                    }
                }
            }
            if (newCol > currCol) {
                for (int col = currCol+1; col < newCol; col++) {
                    if (board.getTile(newRow, col).isOccupied()) {
                        return false;
                    }
                }
            }
        }
        if(newCol == currCol){
            if (newRow < currRow) {
                for (int row = currRow - 1; row > newRow; row--) {
                    if (board.getTile(row,newCol).isOccupied()) {
                        return false;
                    }
                }
            }
            if (newRow > currRow) {
                for (int row = currRow+1; row < newRow; row++) {
                    if (board.getTile(row, newCol).isOccupied()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    //-------------------------------------------------------------------------------------------------
    //Check if move is out of bounds
    //Check if destination = source
    //Determine if move is diagonal or straight
    //Validate move according to rules above
    //Check that destination not occupied by piece of same color
    //-------------------------------------------------------------------------------------------------
    public boolean validMove(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol =  this.getColumn();
        if(outOfBounds(newRow,newCol)){
            return false;
        }
        if(noMove(newRow,newCol)){
            return false;
        }
        if(newRow == currRow || newCol == currCol){
            if(!checkObstacles(board,newRow,newCol)){
                return false;
            }
        }
        else if(Math.abs(currRow-newRow) == Math.abs(currCol-newCol)){
            if(!checkDiagObstacles(board,newRow,newCol)){
                return false;
            }
        }
        else{
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }
    //-------------------------------------------------------------------------------------------------
    //Get all valid diagonal spaces piece can move to
    //Starting from current position, increment until reaching an invalid move
    //-------------------------------------------------------------------------------------------------
    public List<Tile> getValidDiagMoves(Board board){
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

    //Get all valid straight moves
    public List<Tile> getValidStraightMoves(Board board){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        List<Tile> straightMoves = new ArrayList<>();
        int r = currRow;
        int c = currCol;
        while(true){
            r++;
            if(validMove(board,r,c)){
                straightMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        r = currRow;
        while(true){
            r--;
            if(validMove(board,r,c)){
                straightMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        r = currRow;
        while(true){
            c++;
            if(validMove(board,r,c)){
                straightMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        c = currCol;
        while(true){
            c--;
            if(validMove(board,r,c)){
                straightMoves.add(board.getTile(r,c));
            }
            else{
                break;
            }
        }
        return straightMoves;
    }


    @Override
    //Get diagonal moves and straight moves
    public List<Tile> getValidMoves(Board board) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        List<Tile> validMoves = new ArrayList<>();
        validMoves.addAll(getValidDiagMoves(board));
        validMoves.addAll(getValidStraightMoves(board));
        return validMoves;
    }
}
