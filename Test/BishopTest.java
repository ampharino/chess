import models.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class BishopTest {

    @Test
    public void obstacle() {
        Board b = new Board();
        b.init();
        Tile t = b.getTile(0,2);
        Piece p = t.getPiece();
        Bishop bi = (Bishop)p;
        bi.move(b,2,4);
        assertEquals(bi, b.getTile(0,2).getPiece());
        Piece bi2 = b.getTilePiece(7,2);
        bi2.move(b,4,5);
        assertEquals(bi2, b.getTile(7,2).getPiece());



    }

    @Test
    public void ownPiece(){
        Board b = new Board();
        b.init();
        Tile t = b.getTile(0,2);
        Piece p = t.getPiece();
        Bishop bi = (Bishop)p;
        bi.move(b,1,3);
        assertEquals(bi, b.getTile(0,2).getPiece());
        assertEquals(16, b.getBlackPieces().size());
        assertTrue(b.getTile(1,3).getPiece() instanceof Pawn);
    }

    @Test
    public void moveToEmpty() {
        Board b = new Board();
        b.init();
        Tile t = b.getTile(0,2);
        Piece p = t.getPiece();
        Bishop bi = (Bishop)p;
        Pawn pa = (Pawn)b.getTilePiece(1,3);
        pa.move(b,3,3);
        bi.move(b,2,4);
        assertEquals(bi, b.getTilePiece(2,4));
    }

    @Test
    public void capturePiece(){
        Board b = new Board();
        b.init();
        Bishop bi = (Bishop)b.getTilePiece(0,2);
        Pawn pa = (Pawn)b.getTilePiece(1,3);
        pa.move(b,3,3);
        Pawn pa2 = (Pawn)b.getTilePiece(6,6);
        pa2.move(b,4,6);
        bi.move(b,4,6);
        assertEquals(bi, b.getTilePiece(4,6));
        assertEquals(15,b.getWhitePieces().size());
    }

    @Test
    public void invalidMove(){
        Board b = new Board();
        b.init();
        Piece bishop = b.getTilePiece(0,2);
        Piece pawn = b.getTilePiece(1,2);
        pawn.move(b,3,2);
        bishop.move(b, 2,2);
        assertEquals(bishop,b.getTilePiece(0,2));
    }

}