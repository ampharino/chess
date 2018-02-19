package models;

import java.util.ArrayList;
import java.util.List;

public class Guardian extends Piece {

    public Guardian(int row, int col, int color){
        super(row,col,color);
    }

    @Override
    public boolean validMove(Board board, int newRow, int newCol) {
        int currRow = this.getRow();
        int currCol = this.getColumn();
        if(outOfBounds(newRow,newCol)){
            return false;
        }
        if(noMove(newRow,newCol)){
            return false;
        }
        if(this.getColor() == BLACK){
            if(newRow > 3){
                return false;
            }
        }
        else if(newRow < 4){
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }

    @Override
    public List<Tile> getValidMoves(Board board) {
        List<Tile> validMoves = new ArrayList<>();
        if(this.getColor() == BLACK){
            for(int row = 0; row < 4; row++){
                for(int col = 0; col < 8; col++)
                {
                    if(validMove(board,row,col)){
                        validMoves.add(board.getTile(row,col));
                    }
                }
            }
        }
        else{
            for(int row = 7; row > 3; row--){
                for(int col =0; col < 8; col++){
                    if(validMove(board,row,col)){
                        validMoves.add(board.getTile(row,col));
                    }
                }
            }
        }
        return validMoves;
    }
}
