import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class KingTest {

    @Test
    public void noValidMoves() {
        Board b = new Board();
        b.init();
        Piece king = b.getBlackPieces().get(0);
        assertEquals(0,king.getValidMoves(b).size());

    }

    @Test
    public void allValidMoves(){
        Board b = new Board();
        King king = new King(4,4,Piece.BLACK);
        b.getTile(4,4).setPiece(king);
        assertEquals(8, king.getValidMoves(b).size());
        king.move(b,5,4);
        assertEquals(king, b.getTilePiece(5,4));
        king.move(b,4,3);
        assertEquals(king, b.getTilePiece(4,3));
        king.move(b,6,2);
        assertEquals(king, b.getTilePiece(4,3));
    }

    @Test
    public void detectCheck(){
        Board b = new Board();
        King king = new King(4,4,Piece.BLACK);
        b.getTile(4,4).setPiece(king);
        b.getBlackPieces().add(king);

        Queen queen = new Queen(7,4, Piece.WHITE);
        b.getTile(7,4).setPiece(queen);
        b.getWhitePieces().add(queen);
        assertTrue(king.isKingChecked(b));
        boolean result = king.move(b,3,4);
        assertFalse(result);
        assertEquals(6,king.getValidMoves(b).size());
        result = king.move(b,5,5);
        assertTrue(result);
        assertFalse(king.isKingChecked(b));

    }

    @Test
    public void cannotLeaveKingChecked(){
        Board b = new Board();
        King king = new King(4,4,Piece.BLACK);
        Pawn pawn = new Pawn(1,0,Piece.BLACK);
        b.getTile(1,0).setPiece(pawn);
        b.getTile(4,4).setPiece(king);
        b.getBlackPieces().add(king);
        b.getBlackPieces().add(pawn);

        Queen queen = new Queen(7,4, Piece.WHITE);
        b.getTile(7,4).setPiece(queen);
        b.getWhitePieces().add(queen);
        boolean result = pawn.move(b,3,0);
        assertFalse(result);
        assertEquals(pawn, b.getTilePiece(1,0));
        assertNull(b.getTilePiece(3,0));
        assertTrue(pawn.isFirstMove());
    }

    @Test
    public void capturePiece(){
        Board b = new Board();
        King king = new King(4,4,Piece.BLACK);
        King kingw = new King(7,4,Piece.WHITE);
        Pawn pawn = new Pawn(5,5,Piece.WHITE);
        b.getTile(5,5).setPiece(pawn);
        b.getTile(4,4).setPiece(king);
        b.getTile(7,4).setPiece(kingw);
        b.getBlackPieces().add(king);
        b.getWhitePieces().add(kingw);
        b.getWhitePieces().add(pawn);
        assertTrue(king.isKingChecked(b));
        boolean result = king.move(b,5,5);
        assertEquals(1,b.getWhitePieces().size());
        assertTrue(result);
        assertFalse(king.isKingChecked(b));

    }

    @Test
    public void capturePieceChecked(){
        Board b = new Board();
        King king = new King(4,4, Piece.BLACK);
        King kingw = new King(7,4,Piece.WHITE);
        Pawn pawn = new Pawn(5,4,Piece.WHITE);
        Rook rook = new Rook(5,0,Piece.WHITE);
        b.getTile(5,4).setPiece(pawn);
        b.getTile(4,4).setPiece(king);
        b.getTile(7,4).setPiece(kingw);
        b.getTile(5,0).setPiece(rook);
        b.getBlackPieces().add(king);
        b.getWhitePieces().add(kingw);
        b.getWhitePieces().add(pawn);
        b.getWhitePieces().add(rook);
        boolean result = king.move(b,5,4);
        assertFalse(result);
        assertEquals(3,b.getWhitePieces().size());
    }

    @Test
    public void undoMove(){
        Board b = new Board();
        King king = new King(4,4,Piece.BLACK);
        Queen queen = new Queen(5,7,Piece.BLACK);
        King kingw = new King(7,4,Piece.WHITE);
        Pawn pawn = new Pawn(5,4,Piece.WHITE);
        Rook rook = new Rook(4,0,Piece.WHITE);
        b.getTile(5,4).setPiece(pawn);
        b.getTile(4,4).setPiece(king);
        b.getTile(7,4).setPiece(kingw);
        b.getTile(4,0).setPiece(rook);
        b.getTile(5,7).setPiece(queen);
        b.getBlackPieces().add(king);
        b.getBlackPieces().add(queen);
        b.getWhitePieces().add(kingw);
        b.getWhitePieces().add(pawn);
        b.getWhitePieces().add(rook);
        assertTrue(king.isKingChecked(b));
        boolean result = queen.move(b,5,4);
        assertFalse(result);
        assertEquals(3,b.getWhitePieces().size());
        assertEquals(7,queen.getColumn());


    }

}