import models.Board;
import models.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class RookTest {

    @Test
    public void obstacle() {
        Board b = new Board();
        b.init();
        Piece rook = b.getTilePiece(0,0);
        rook.move(b,4,0);
        assertEquals(rook,b.getTilePiece(0,0));
        assertNull(b.getTilePiece(4,0));
    }

    @Test
    public void invalidMove(){
        Board b = new Board();
        b.init();
        Piece rook = b.getTilePiece(0,7);
        Piece pawn = b.getTilePiece(1,6);
        pawn.move(b,3,6);
        rook.move(b,1,6);
        assertEquals(rook,b.getTilePiece(0,7));
        assertNull(b.getTilePiece(1,6));
    }

    @Test
    public void movetoEmpty(){
        Board b = new Board();
        b.init();
        Piece rook = b.getTilePiece(7,7);
        Piece pawn = b.getTilePiece(6,7);
        pawn.move(b,4,7);
        rook.move(b,5,7);
        assertEquals(rook,b.getTilePiece(5,7));
        rook.move(b,5,0);
        assertEquals(rook,b.getTilePiece(5,0));

    }

    @Test
    public void captureOwnPiece(){
        Board b = new Board();
        b.init();
        Piece rook = b.getTilePiece(7,0);
        Piece pawn = b.getTilePiece(6,0);
        pawn.move(b,5,0);
        rook.move(b,5,0);
        assertEquals(rook,b.getTilePiece(7,0));
        assertEquals(pawn,b.getTilePiece(5,0));
        assertEquals(16,b.getWhitePieces().size());
    }

    @Test
    public void capturePiece(){
        Board b = new Board();
        b.init();
        Piece rook = b.getTilePiece(7,7);
        Piece pawn = b.getTilePiece(6,7);
        pawn.move(b,4,7);
        rook.move(b,5,7);
        rook.move(b,5,4);
        rook.move(b,1,4);
        assertEquals(rook,b.getTilePiece(1,4));
        assertEquals(15,b.getBlackPieces().size());

    }
}