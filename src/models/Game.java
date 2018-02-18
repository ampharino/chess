package models;

import java.util.List;

public class Game {
    public Board board;
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public boolean gameOver;
    public boolean blackTurn;

    public Game(){
        board = new Board();
        board.init();
        blackTurn = false;
        gameOver = false;
    }

    //-------------------------------------------------------------------------------------------------
    //Check if the king is currently checked
    //If true, then get all valid moves for pieces of given color
    //Try all valid moves to see if there is one where the king is no longer checked
    //If no such move exists, then checkmate is true
    //-------------------------------------------------------------------------------------------------
    public boolean checkMate(){
        List<Piece> pieces;
        if(blackTurn){
            pieces = board.getBlackPieces();

        }
        else{
            pieces = board.getWhitePieces();
        }
        King king = (King)pieces.get(0);
        if(!king.isKingChecked(board)){
            return false;
        }
        for(Piece piece:pieces){
            List<Tile> possibleMoves = piece.getValidMoves(board);
            for(Tile t:possibleMoves){
                boolean result = piece.tryMove(board,t.getRow(),t.getColumn());
                if(result){
                    return false;
                }
            }
        }
        System.out.println("----CHECKMATE----");
        gameOver = true;
        return true;
    }

    //-------------------------------------------------------------------------------------------------
    //Determine if the king has no valid moves and is the only piece remaining
    //Determine if the king is checked
    //If not checked, then stalemate is true
    //-------------------------------------------------------------------------------------------------
    public boolean stalemate(){
        List<Piece> pieces;
        if(blackTurn){
            pieces = board.getBlackPieces();

        }
        else{
            pieces = board.getWhitePieces();
        }
        King king = (King)pieces.get(0);
        if(king.getValidMoves(board).size() == 0 && pieces.size() == 1){
            if(!king.isKingChecked(board)){
                gameOver = true;
                return true;
            }
        }
        return false;
    }


    public boolean check(){
        List<Piece> pieces;
        if(blackTurn){
            pieces = board.getBlackPieces();

        }
        else{
            pieces = board.getWhitePieces();
        }
        King king = (King)pieces.get(0);
        if(!king.isKingChecked(board)){
            return false;
        }
        return true;
    }
}

