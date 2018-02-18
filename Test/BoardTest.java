import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    @Test
    public void getBlackPieces() {
        Board b = new Board();
        assertEquals(0, b.getBlackPieces().size());
        b.init();
        b.printBoard();
        assertEquals(16,b.getBlackPieces().size());
        assertEquals(1,b.getBlackPieces().get(10).getColor());
    }

    @Test
    public void getWhitePieces() {
        Board b = new Board();
        assertEquals(0, b.getWhitePieces().size());
        b.init();
        assertEquals(16,b.getWhitePieces().size());
        assertEquals(2,b.getWhitePieces().get(10).getColor());
    }

    @Test
    public void correctKingPositions() {
        Board b = new Board();
        b.init();
        Tile black = b.getTile(0,4);
        Tile white = b.getTile(7,4);
        assertTrue(black.getPiece() instanceof King);
        assertEquals(1, black.getPiece().getColor());
        assertTrue(white.getPiece() instanceof King);
        assertEquals(2, white.getPiece().getColor());
    }

    @Test
    public void correctQueenPositions() {
        Board b = new Board();
        b.init();
        Tile black = b.getTile(0,3);
        Tile white = b.getTile(7,3);
        assertTrue(black.getPiece() instanceof Queen);
        assertEquals(1, black.getPiece().getColor());
        assertTrue(white.getPiece() instanceof Queen);
        assertEquals(2, white.getPiece().getColor());
    }

    @Test
    public void correctRookPositions() {
        Board b = new Board();
        b.init();
        Tile black1 = b.getTile(0,0);
        Tile black2 = b.getTile(0,7);
        Tile white1 = b.getTile(7,0);
        Tile white2 = b.getTile(7,7);

        assertTrue(black1.getPiece() instanceof Rook);
        assertEquals(1, black1.getPiece().getColor());
        assertTrue(white1.getPiece() instanceof Rook);
        assertEquals(2, white1.getPiece().getColor());

        assertTrue(black2.getPiece() instanceof Rook);
        assertEquals(1, black2.getPiece().getColor());
        assertTrue(white2.getPiece() instanceof Rook);
        assertEquals(2, white1.getPiece().getColor());
    }

    @Test
    public void correctKnightPositions() {
        Board b = new Board();
        b.init();
        Tile black1 = b.getTile(0,1);
        Tile black2 = b.getTile(0,6);
        Tile white1 = b.getTile(7,1);
        Tile white2 = b.getTile(7,6);
        assertTrue(black1.getPiece() instanceof Knight);
        assertEquals(1, black1.getPiece().getColor());
        assertTrue(white1.getPiece() instanceof Knight);
        assertEquals(2, white1.getPiece().getColor());

        assertTrue(black2.getPiece() instanceof Knight);
        assertEquals(1, black2.getPiece().getColor());
        assertTrue(white2.getPiece() instanceof Knight);
        assertEquals(2, white1.getPiece().getColor());
    }

    @Test
    public void correctBishopPositions() {
        Board b = new Board();
        b.init();
        Tile black1 = b.getTile(0,2);
        Tile white1 = b.getTile(7,2);
        Tile black2 = b.getTile(0,5);
        Tile white2 = b.getTile(7,5);
        assertTrue(black1.getPiece() instanceof Bishop);
        assertEquals(1, black1.getPiece().getColor());
        assertTrue(white1.getPiece() instanceof Bishop);
        assertEquals(2, white1.getPiece().getColor());

        assertTrue(black2.getPiece() instanceof Bishop);
        assertEquals(1, black2.getPiece().getColor());
        assertTrue(white2.getPiece() instanceof Bishop);
        assertEquals(2, white2.getPiece().getColor());
    }

    @Test
    public void correctPawnPositions() {
        Board b = new Board();
        b.init();
        for(int col = 0; col<8;col++){
            Tile t = b.getTile(1,col);
            assertTrue(t.getPiece() instanceof Pawn);
            assertEquals(1,t.getPiece().getColor());
        }
        for(int col = 0; col<8;col++){
            Tile t = b.getTile(6,col);
            assertTrue(t.getPiece() instanceof Pawn);
            assertEquals(2,t.getPiece().getColor());
        }

    }


}