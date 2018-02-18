package models;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece{

    public Knight(int row, int col, int color){

        super(row, col, color);
    }
    @Override
    //-------------------------------------------------------------------------------------------------
    //Determine if move is out of bounds
    //Determine if destination same as curr
    //Determine that destination is 3 tiles away
    //Determine that that move is L shape
    //Determine if there is same color piece at destination
    //-------------------------------------------------------------------------------------------------
    public boolean validMove(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if(outOfBounds(newRow,newCol)){
            return false;
        }
        if(noMove(newRow,newCol)){
            return false;
        }
        if(Math.abs(newRow-currRow) + Math.abs(newCol-currCol) != 3){
            return false;
        }
        if((Math.abs(newRow-currRow)!= 2 && Math.abs(newCol-currCol) != 1)
                && (Math.abs(newRow-currRow)!= 1 && Math.abs(newCol-currCol)!=2)) {

            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }

    @Override

    //Get legal destinations knight can move to using rules defined above
    public List<Tile> getValidMoves(Board board) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        List<Tile> validMoves = new ArrayList<>();
        if(validMove(board,currRow+2,currCol+1)){
            validMoves.add(board.getTile(currRow+2,currCol+1));
        }
        if(validMove(board,currRow+2,currCol-1)){
            validMoves.add(board.getTile(currRow+2,currCol-1));
        }
        if(validMove(board,currRow-2,currCol+1)){
            validMoves.add(board.getTile(currRow-2,currCol+1));
        }
        if(validMove(board,currRow-2,currCol-1)){
            validMoves.add(board.getTile(currRow-2,currCol-1));
        }
        if(validMove(board,currRow+1,currCol+2)){
            validMoves.add(board.getTile(currRow+1,currCol+2));
        }
        if(validMove(board,currRow-1,currCol+2)){
            validMoves.add(board.getTile(currRow-1,currCol+2));
        }
        if(validMove(board,currRow+1,currCol-2)){
            validMoves.add(board.getTile(currRow+1,currCol-2));
        }
        if(validMove(board,currRow-1,currCol-2)){
            validMoves.add(board.getTile(currRow-1,currCol-2));
        }
        return validMoves;
    }


}
