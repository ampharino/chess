import models.Game;
import models.King;
import models.Piece;
import org.junit.Test;
import static org.junit.Assert.*;


public class GameTest {

    @Test
    public void testCheckMate(){
        Game game = new Game();

        game.board.getTilePiece(6,5).move(game.board,5,5);
        game.board.getTilePiece(1,4).move(game.board,3,4);
        game.board.getTilePiece(6,6).move(game.board,4,6);
        game.board.getTilePiece(0,3).move(game.board,4,7);
        //assertTrue(game.checkMate(Game.WHITE));
        game.board.printBoard();
    }

    @Test
    public void notCheckMate(){
        Game game = new Game();

        game.board.getTilePiece(6,5).move(game.board,4,5);
        game.board.getTilePiece(1,4).move(game.board,3,4);
        game.board.getTilePiece(6,6).move(game.board,4,6);
        game.board.getTilePiece(7,6).move(game.board,5,5);
        game.board.getTilePiece(0,3).move(game.board,4,7);
        King king = (King)game.board.getWhitePieces().get(0);
        assertTrue(king.isKingChecked(game.board));
        //boolean result = game.checkMate(Game.WHITE);
        //assertFalse(result);
        assertEquals(16,game.board.getBlackPieces().size());
        game.board.printBoard();
    }

    @Test
    public void kingMustMove(){
        Game game = new Game();
        Piece bishop = game.board.getTilePiece(0,5);
        game.board.getTilePiece(1,4).move(game.board,3,4);
        bishop.move(game.board,3,2);
        bishop.move(game.board,6,5);
        King king = (King)game.board.getWhitePieces().get(0);
        assertTrue(king.isKingChecked(game.board));
        //boolean result = game.checkMate(Game.WHITE);
        //assertFalse(result);
        assertFalse(game.board.getTilePiece(7,1).move(game.board,5,0));
        assertEquals(16,game.board.getBlackPieces().size());
        assertEquals(15,game.board.getWhitePieces().size());
        game.board.printBoard();

    }
}
