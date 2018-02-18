import models.Board;
import models.Pawn;
import models.Piece;
import models.Tile;
import org.junit.Test;

import static org.junit.Assert.*;

public class PawnTest {

    @Test
    public void isFirstMove() {
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        assertTrue(pa.isFirstMove());
        pa.move(b,2,0);
        assertFalse(pa.isFirstMove());
    }

    @Test
    public void moveToEmptyTile() {
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        pa.move(b,3,0);
        assertEquals(pa,b.getTile(3,0).getPiece());
        assertNull(t.getPiece());

    }

    @Test
    public void capturePiece() {
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        pa.move(b,3,0);
        pa.move(b,4,0);
        pa.move(b,5,0);
        pa.move(b,6,1);
        assertEquals(pa,b.getTile(6,1).getPiece());
        assertEquals(15, b.getWhitePieces().size());
    }

    @Test
    public void invalidMove(){
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        pa.move(b,4,0);
        pa.move(b,-2,-4);
        assertEquals(1, pa.getRow());
        assertEquals(0,pa.getColumn());

    }

    @Test
    public void forwardPathBlocked(){
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        pa.move(b,3,0);
        pa.move(b,4,0);
        pa.move(b,5,0);
        pa.move(b,6,0);
        assertEquals(5,pa.getRow());
        assertEquals(0,pa.getColumn());
    }

    @Test
    public void captureOwnPiece(){
        Board b = new Board();
        b.init();
        Tile t = b.getTile(1,0);
        Piece p = t.getPiece();
        Pawn pa = (Pawn) p;
        Piece p2 = b.getTile(1,1).getPiece();
        Pawn pa2 = (Pawn) p2;
        pa.move(b,2,0);
        pa2.move(b,2,0);
        assertEquals(pa2, b.getTile(1,1).getPiece());
        assertEquals(pa,b.getTile(2,0).getPiece());

    }
}