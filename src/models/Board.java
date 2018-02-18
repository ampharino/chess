package models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    private Tile[][] tiles;
    private List<Piece> blackPieces = new ArrayList<>();
    private List<Piece> whitePieces = new ArrayList<>();

    //Initializes empty 8x8 board
    public Board(){
        this.tiles = new Tile[8][8];
        for(int col = 0; col < tiles.length; col++){
            for(int row = 0; row < tiles.length; row++){
                tiles[row][col] = new Tile(row, col);
            }
        }
    }

    //initializes rooks in correct board positions and adds them to respective player's pieces
    private void initRooks(){
        Rook blackRook1 = new Rook(0,0, BLACK);
        Rook blackRook2 = new Rook(0,7, BLACK);
        tiles[0][0].setPiece(blackRook1);
        tiles[0][7].setPiece(blackRook2);
        blackPieces.add(blackRook1);
        blackPieces.add(blackRook2);

        Rook whiteRook1 = new Rook(7,0, WHITE);
        Rook whiteRook2 = new Rook(7,7, WHITE);
        tiles[7][0].setPiece(whiteRook1);
        tiles[7][7].setPiece(whiteRook2);
        whitePieces.add(whiteRook1);
        whitePieces.add(whiteRook2);

    }
    //initializes knights in correct board positions and adds them to respective player's pieces
    private void initKnights(){
        Knight blackKnight1 = new Knight(0,1, BLACK);
        Knight blackKnight2 = new Knight(0,6, BLACK);
        tiles[0][1].setPiece(blackKnight1);
        tiles[0][6].setPiece(blackKnight2);
        blackPieces.add(blackKnight1);
        blackPieces.add(blackKnight2);

        Knight whiteKnight1 = new Knight(7,1, WHITE);
        Knight whiteKnight2 = new Knight(7,6, WHITE);
        tiles[7][1].setPiece(whiteKnight1);
        tiles[7][6].setPiece(whiteKnight2);
        whitePieces.add(whiteKnight1);
        whitePieces.add(whiteKnight2);



    }

    //initializes bishops in correct board positions and adds them to respective player's pieces
    private void initBishops(){
        Bishop blackBishop1 = new Bishop(0, 2, BLACK);
        Bishop blackBishop2 = new Bishop(0, 5, BLACK);
        tiles[0][2].setPiece(blackBishop1);
        tiles[0][5].setPiece(blackBishop2);
        blackPieces.add(blackBishop1);
        blackPieces.add(blackBishop2);

        Bishop whiteBishop1 = new Bishop(7,2, WHITE);
        Bishop whiteBishop2 = new Bishop(7,5, WHITE);
        tiles[7][2].setPiece(whiteBishop1);
        tiles[7][5].setPiece(whiteBishop2);
        whitePieces.add(whiteBishop1);
        whitePieces.add(whiteBishop2);

    }

    //initializes kings in correct board positions and adds them to respective player's pieces
    private void initKings(){
        King blackKing = new King(0, 4, BLACK);
        tiles[0][4].setPiece(blackKing);
        blackPieces.add(blackKing);

        King whiteKing = new King(7,4, WHITE);
        tiles[7][4].setPiece(whiteKing);
        whitePieces.add(whiteKing);


    }

    //initializes queens in correct board positions and adds them to respective player's pieces
    private void initQueens(){
        Queen blackQueen = new Queen(0,3, BLACK);
        tiles[0][3].setPiece(blackQueen);
        blackPieces.add(blackQueen);

        Queen whiteQueen = new Queen(7,3, WHITE);
        tiles[7][3].setPiece(whiteQueen);
        whitePieces.add(whiteQueen);

    }

    //initializes pawns in correct board positions and adds them to respective player's pieces
    private void initPawns(){
        for(int col = 0; col < 8; col++){
            Pawn p = new Pawn(1,col, BLACK);
            blackPieces.add(p);
            tiles[1][col].setPiece(p);
        }
        for(int col = 0; col < 8; col++){
            Pawn p = new Pawn(6,col, WHITE);
            whitePieces.add(p);
            tiles[6][col].setPiece(p);
        }

    }

    //initializes all pieces in correct positions
    public void init(){
        initKings();
        initBishops();
        initKnights();
        initPawns();
        initQueens();
        initRooks();

    }
    public Tile getTile(int row, int col){
        return tiles[row][col];
    }

    public Piece getTilePiece(int row, int col){
        return tiles[row][col].getPiece();
    }

    public int getPieceColor(int row, int col){
        return tiles[row][col].getPiece().getColor();
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    //Print out the current state of the board
    public void printBoard(){
        System.out.println("----------");
        for(int row = 0; row < 8; row++){
            System.out.print("-");
            for(int col =0; col <8; col++){

                if(getTilePiece(row,col) == null) {
                    System.out.print(" ");
                }
                else if(getTilePiece(row,col).getColor() == BLACK){
                    if(getTilePiece(row, col) instanceof Pawn){
                        System.out.print("P");
                    }
                    else if(getTilePiece(row, col) instanceof King){
                        System.out.print("K");
                    }
                    else if(getTilePiece(row, col) instanceof Knight){
                        System.out.print("N");
                    }
                    else if(getTilePiece(row, col) instanceof Bishop){
                        System.out.print("B");
                    }
                    else if(getTilePiece(row, col) instanceof Rook){
                        System.out.print("R");
                    }
                    else if(getTilePiece(row, col) instanceof Queen){
                        System.out.print("Q");
                    }
                }
                else{
                    if(getTilePiece(row, col) instanceof Pawn){
                        System.out.print("p");
                    }
                    else if(getTilePiece(row, col) instanceof King){
                        System.out.print("k");
                    }
                    else if(getTilePiece(row, col) instanceof Knight){
                        System.out.print("n");
                    }
                    else if(getTilePiece(row, col) instanceof Bishop){
                        System.out.print("b");
                    }
                    else if(getTilePiece(row, col) instanceof Rook){
                        System.out.print("r");
                    }
                    else if(getTilePiece(row, col) instanceof Queen){
                        System.out.print("q");
                    }
                    else{
                        System.out.print(" ");
                    }

                }

            }
            System.out.print("-");
            System.out.println();
        }
        System.out.println("---------");
    }
}
