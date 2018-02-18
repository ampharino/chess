package models;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {

    public King(int row, int col, int color){
        super(row,col,color);
    }


    @Override
    //-------------------------------------------------------------------------------------------------
    //Determine if move is out of bounds
    //Determine if destination is same as curr position
    //Determine that destination is adjacent to curr position
    //Check that destination is not occupied by piece with same color
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
        if(Math.abs(newRow-currRow) > 1 || Math.abs(newCol-currCol) > 1){
            return false;
        }
        if(board.getTile(newRow,newCol).isOccupied() && board.getPieceColor(newRow,newCol)== this.getColor()){
            return false;
        }
        return true;
    }

    @Override
    //-------------------------------------------------------------------------------------------------
    //Check all adjacent tiles to see if it is a legal move
    //If it is legal, check if moving there would put king in check
    //If true, then it is not a valid move
    //Return list of valid moves
    //-------------------------------------------------------------------------------------------------
    public List<Tile> getValidMoves(Board board){
        int currRow = this.getRow();
        int currCol = this.getColumn();
        List<Tile> validMoves = new ArrayList<>();
        for(int y = -1; y < 2; y++){
            for(int x = -1; x < 2; x++){
                if(validMove(board,currRow+y,currCol+x) &&
                        !isDestChecked(board,currRow+y,currCol+x)){

                    validMoves.add(board.getTile(currRow+y,currCol+x));
                }
            }
        }
        return validMoves;
    }


    //-------------------------------------------------------------------------------------------------
    //For each piece on opposing team, check if can legally move to king's curr position
    //If true, then king is in check
    //-------------------------------------------------------------------------------------------------
    public boolean isKingChecked(Board board){
        if(this.getColor() == BLACK){
            for(Piece piece : board.getWhitePieces()){
                if(piece.validMove(board,this.getRow(),this.getColumn())){
                    return true;

                }
            }
        }
        if(this.getColor() == WHITE){
            for(Piece piece : board.getBlackPieces()){
                if(piece.validMove(board,this.getRow(),this.getColumn())){
                    return true;
                }
            }
        }
        return false;

    }
    //-------------------------------------------------------------------------------------------------
    //Move to destination then determine if king is checked
    //Undo move and restore state
    //-------------------------------------------------------------------------------------------------
    public boolean isDestChecked(Board board, int destRow, int destCol){
        Tile curr = board.getTile(this.getRow(),this.getColumn());
        Tile dest = board.getTile(destRow,destCol);
        Piece temp = dest.getPiece();
        dest.setPiece(this);
        curr.setPiece(null);

        if(this.getColor() == BLACK){
            for(Piece piece : board.getWhitePieces()){
                if(piece.validMove(board,dest.getRow(),dest.getColumn())){
                    dest.setPiece(temp);
                    curr.setPiece(this);
                    return true;

                }
            }
        }
        if(this.getColor() == WHITE){
            for(Piece piece : board.getBlackPieces()){
                if(piece.validMove(board,dest.getRow(),dest.getColumn())){
                    dest.setPiece(temp);
                    curr.setPiece(this);
                    return true;
                }
            }
        }
        dest.setPiece(temp);
        curr.setPiece(this);
        return false;
    }

    @Override
    //-------------------------------------------------------------------------------------------------
    //Do not move if destination would put king in check
    //Otherwise, move to destination
    //If destination is occupied, capture enemy piece
    //Update new position
    //-------------------------------------------------------------------------------------------------
    public boolean move(Board board, int newRow, int newCol) {
        if(validMove(board, newRow, newCol)){
            Tile curr = board.getTile(this.getRow(),this.getColumn());
            Tile dest = board.getTile(newRow, newCol);
            Piece temp = dest.getPiece();
            if(isDestChecked(board,newRow,newCol)){
                System.out.println("Cannot move king to checked position");
                return false;
            }
            if(dest.isOccupied()){
                Piece removedPiece = dest.getPiece();
                capturePiece(board, removedPiece, dest);
            }
            dest.setPiece(this);
            curr.setPiece(null);
            setNewPos(newRow,newCol);
            return true;

        }
        return false;

    }

}
