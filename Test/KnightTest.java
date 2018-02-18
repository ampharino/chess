import models.Board;
import models.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class KnightTest {

    @Test
    public void validMoves() {
        Board b = new Board();
        b.init();
        Piece knight = b.getTilePiece(0,1);
        knight.move(b,2,0);

        assertEquals(knight,b.getTilePiece(2,0));
        knight.move(b,3,2);

        assertEquals(knight,b.getTilePiece(3,2));
        knight.move(b,5,1);

        assertEquals(knight,b.getTilePiece(5,1));
        knight.move(b,3,0);
        assertEquals(knight,b.getTilePiece(3,0));
    }

    @Test
    public void invalidMoves(){
        Board b = new Board();
        b.init();
        Piece knight = b.getTilePiece(0,6);
        knight.move(b,2,6);
        assertEquals(knight,b.getTilePiece(0,6));
        knight.move(b,4,2);
        assertEquals(knight,b.getTilePiece(0,6));
    }

    @Test
    public void capturePiece(){
        Board b = new Board();
        b.init();
        Piece knight = b.getTilePiece(7,1);
        knight.move(b,5,2);
        knight.move(b,3,1);
        knight.move(b,1,0);
        assertEquals(knight,b.getTilePiece(1,0));
        assertEquals(15,b.getBlackPieces().size());

    }

    @Test
    public void captureOwnPiece(){
        Board b = new Board();
        b.init();
        Piece knight = b.getTilePiece(7,6);
        knight.move(b,6,4);
        assertEquals(knight,b.getTilePiece(7,6));
        assertEquals(16,b.getWhitePieces().size());
    }
}