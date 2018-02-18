package models;

import java.util.List;

public class Rook extends Queen{

    public Rook(int row, int col, int color){
        super(row, col, color);
    }


    @Override
    public boolean validMove(Board board, int newRow, int newCol){
        if(outOfBounds(newRow,newCol)){
            return false;
        }
        if (noMove(newRow,newCol)){
            return false;
        }
        if(newRow != this.getRow() && newCol != this.getColumn()){
            return false;
        }
        if(!checkObstacles(board,newRow,newCol)){
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }

    @Override
    public List<Tile> getValidMoves(Board board){
        return getValidStraightMoves(board);
    }


}
