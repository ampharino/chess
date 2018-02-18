package models;

import java.util.List;

public class Bishop extends Queen{

    public Bishop(int row, int col, int color){
        super(row, col, color);
    }



    @Override
    //-------------------------------------------------------------------------------------------------
    //Determine if move is out of bounds
    //Determine if piece stays in place
    //Determine if move is diagonal
    //Starting from curr position, determine if there are obstacles in path to destination
    //Determine if destination already occupied by piece with same color
    //-------------------------------------------------------------------------------------------------
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
        if(checkDiagObstacles(board,newRow,newCol)== false){
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }

        return true;
    }

    @Override
    public List<Tile> getValidMoves(Board board){
        return getValidDiagMoves(board);
    }


}
