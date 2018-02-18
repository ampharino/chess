import models.Board;
import models.Piece;
import org.junit.Test;

import static org.junit.Assert.*;

public class QueenTest {

    @Test
    public void validMoves() {
        Board b = new Board();
        b.init();
        Piece queen = b.getTilePiece(0,3);
        Piece pawn = b.getTilePiece(1,3);
        pawn.move(b,3,3);
        queen.move(b,2,3);
        assertEquals(queen,b.getTilePiece(2,3));
        queen.move(b,2,0);
        assertEquals(queen,b.getTilePiece(2,0));
        queen.move(b,2,7);
        assertEquals(queen,b.getTilePiece(2,7));
        queen.move(b,5,4);
        assertEquals(queen,b.getTilePiece(5,4));
    }

    @Test
    public void diagObstacles(){
        Board b = new Board();
        b.init();
        Piece queen = b.getTilePiece(7,3);
        queen.move(b,4,6);
        assertEquals(queen,b.getTilePiece(7,3));
        queen.move(b,5,1);
        assertEquals(queen,b.getTilePiece(7,3));

    }

    @Test
    public void obstacles(){
        Board b = new Board();
        b.init();
        Piece queen = b.getTilePiece(7,3);
        queen.move(b,4,3);
        assertEquals(queen,b.getTilePiece(7,3));
    }

    @Test
    public void invalidMove(){
        Board b = new Board();
        b.init();
        Piece queen = b.getTilePiece(0,3);
        Piece pawn = b.getTilePiece(1,4);
        pawn.move(b,3,4);
        queen.move(b,1,4);
        assertEquals(queen,b.getTilePiece(1,4));
        queen.move(b, 2,0);
        assertEquals(queen,b.getTilePiece(1,4));
        queen.move(b,5,7);
        assertEquals(queen,b.getTilePiece(1,4));
    }

    @Test
    public void capturePiece(){
        Board b = new Board();
        b.init();
        Piece queen = b.getTilePiece(0,3);
        Piece pawn = b.getTilePiece(1,4);
        pawn.move(b,3,4);
        queen.move(b,2,5);
        assertEquals(queen,b.getTilePiece(2,5));
        queen.move(b,6,5);
        assertEquals(queen,b.getTilePiece(6,5));
        assertEquals(15,b.getWhitePieces().size());

    }
}