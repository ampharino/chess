package gui;
import models.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Chessboard extends JPanel{
    public Game game;
    public JButton[][] boardTiles = new JButton[8][8];
    private Insets buttonMargin = new Insets(0,0,0,0);
    private Font buttonFont = new Font("Arial Unicode MS", Font.BOLD, 40);
    public static final String WHITE_KING = "\u2654";
    public static final String WHITE_QUEEN = "\u2655";
    public static final String WHITE_ROOK = "\u2656";
    public static final String WHITE_BISHOP = "\u2657";
    public static final String WHITE_KNIGHT = "\u2658";
    public static final String WHITE_PAWN = "\u2659";

    public static final String BLACK_KING = "\u265A";
    public static final String BLACK_QUEEN = "\u265B";
    public static final String BLACK_ROOK = "\u265C";
    public static final String BLACK_BISHOP = "\u265D";
    public static final String BLACK_KNIGHT = "\u265E";
    public static final String BLACK_PAWN = "\u265F";
    public int[] scoreRef;
    public String[] messageRef;
    public Main gui;

    private Piece currSelected;




    //------------------------------------------------
    //Initializes the chessboard
    //Creates 64 buttons for each tile
    //Sets button text to chess unicode character
    //------------------------------------------------
    public Chessboard(int[] scores, String[] messages, Main gui){
        super(new GridLayout(0,10));
        this.gui = gui;
        scoreRef = scores;
        messageRef = messages;
        currSelected = null;
        this.setBorder( new LineBorder(Color.black));
        this.add(new JLabel(""));
        for(int col = 0; col < 8; col++){
            this.add(new JLabel(Integer.toString(col),SwingConstants.CENTER));
        }
        this.add(new JLabel(""));
        for(int row = 0; row <8; row++){
            this.add(new JLabel(Integer.toString(row),SwingConstants.CENTER));
            for(int col =0; col<8; col++){
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                b.setFont(buttonFont);
                b.setText("");
                if((row% 2 == 1 && col%2 == 1)||(row% 2 == 0 && col%2 == 0)){
                    b.setBackground(new Color(204,204,204));
                }
                else{
                    b.setBackground(Color.white);
                }
                boardTiles[row][col] = b;
                this.add(boardTiles[row][col]);
            }
            this.add(new JLabel(""));
        }
        for(int col =0; col < 10; col++){
            this.add(new JLabel(""));
        }
        game = new Game();
        addListeners();
        drawPieces();
    }

    private void selectPiece(Piece piece, int row, int col){
        if(game.blackTurn) {
            if (piece.getColor() == Piece.BLACK) {
                currSelected = piece;
                boardTiles[row][col].setBorder(BorderFactory.createLineBorder(Color.blue, 4));

            }
        }
        else{
            if(piece.getColor() == Piece.WHITE){
                currSelected = piece;
                boardTiles[row][col].setBorder(BorderFactory.createLineBorder(Color.blue, 4));
            }
        }
        if(currSelected != null){
            List<Tile> moves = piece.getValidMoves(game.board);
            for(Tile tile : moves){
                int r = tile.getRow();
                int c = tile.getColumn();
                boardTiles[r][c].setBorder(BorderFactory.createLineBorder(Color.green, 4));

            }
        }

    }

    private void doMove(int row, int col){
        int prevRow = currSelected.getRow();
        int prevCol = currSelected.getColumn();
        boolean result = currSelected.move(game.board,row,col);
        if(result){
            boardTiles[prevRow][prevCol].setText("");
            drawPieces();
            currSelected = null;
            game.blackTurn = !game.blackTurn;

        }
        else{
            messageRef[1] = "INVALID MOVE";
            currSelected = null;
            gui.updateMessages();
        }
        for(int r=0; r< 8; r++){
            for(int c=0; c<8; c++){
                boardTiles[r][c].setBorder(UIManager.getBorder("Button.border"));
            }
        }

    }

    private void checkHandle(){
        if(game.check()){
            messageRef[1] = messageRef[0] + "'s KING IN CHECK";
            if(game.blackTurn){
                King king = (King)game.board.getBlackPieces().get(0);
                boardTiles[king.getRow()][king.getColumn()].setBorder(BorderFactory.createLineBorder(Color.orange,4));
            }
            else{
                King king = (King)game.board.getWhitePieces().get(0);
                boardTiles[king.getRow()][king.getColumn()].setBorder(BorderFactory.createLineBorder(Color.orange,4));
            }
        }
        else{
            King king = (King)game.board.getBlackPieces().get(0);
            boardTiles[king.getRow()][king.getColumn()].setBorder(UIManager.getBorder("Button.border"));
            king = (King)game.board.getWhitePieces().get(0);
            boardTiles[king.getRow()][king.getColumn()].setBorder(UIManager.getBorder("Button.border"));
        }
        revalidate();
        repaint();
    }
    private void addListeners(){
        for(int row=0; row<8;row++){
            for(int col=0; col<8; col++){
                int finalRow = row;
                int finalCol = col;
                boardTiles[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(game.gameOver){
                            return;
                        }
                        messageRef[1] = "";
                        if(currSelected == null){
                            Piece temp = game.board.getTilePiece(finalRow, finalCol);
                            if(temp == null){
                                return;
                            }
                            selectPiece(temp, finalRow, finalCol);
                        }
                        else{
                            doMove(finalRow, finalCol);
                        }
                        revalidate();
                        repaint();

                        if(game.checkMate()) {
                            messageRef[1] = messageRef[0] + " WINS!";
                            gui.showCheckmateLabel();
                            if(game.blackTurn){
                                scoreRef[Piece.BLACK]++;
                            }
                            else{
                                scoreRef[Piece.WHITE]++;
                            }
                            gui.updateScores();
                            gui.updateMessages();
                            return;

                        }
                        if(game.blackTurn){
                            messageRef[0] = "BLACK";
                        }
                        else{
                            messageRef[0] = "WHITE";
                        }
                        checkHandle();
                        gui.updateMessages();

                    }
                });
            }
        }
    }


    private void drawPieces(){
        List<Piece> blackPieces = game.board.getBlackPieces();
        List<Piece> whitePieces = game.board.getWhitePieces();
        for(Piece piece: blackPieces){
            int row = piece.getRow();
            int col = piece.getColumn();
            if(piece instanceof Pawn){
                boardTiles[row][col].setText(BLACK_PAWN);
            }
            else if(piece instanceof Knight){
                boardTiles[row][col].setText(BLACK_KNIGHT);
            }
            else if(piece instanceof King){
                boardTiles[row][col].setText(BLACK_KING);
            }
            else if(piece instanceof Rook){
                boardTiles[row][col].setText(BLACK_ROOK);
            }
            else if(piece instanceof Bishop){
                boardTiles[row][col].setText(BLACK_BISHOP);
            }
            else{
                boardTiles[row][col].setText(BLACK_QUEEN);
            }
        }
        for(Piece piece: whitePieces){
            int row = piece.getRow();
            int col = piece.getColumn();
            if(piece instanceof Pawn){
                boardTiles[row][col].setText(WHITE_PAWN);
            }
            else if(piece instanceof Knight){
                boardTiles[row][col].setText(WHITE_KNIGHT);
            }
            else if(piece instanceof King){
                boardTiles[row][col].setText(WHITE_KING);
            }
            else if(piece instanceof Rook){
                boardTiles[row][col].setText(WHITE_ROOK);
            }
            else if(piece instanceof Bishop){
                boardTiles[row][col].setText(WHITE_BISHOP);
            }
            else{
                boardTiles[row][col].setText(WHITE_QUEEN);
            }
        }

    }



}
