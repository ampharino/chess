package gui;

import models.Piece;

public class Command {
    public Piece selected;
    public Piece captured;
    public int prevRow;
    int prevCol;
    boolean firstMove;

    public Command(Piece s, Piece c, int row, int col, boolean first){
        selected = s;
        captured = c;
        prevRow = row;
        prevCol = col;
        firstMove = first;
    }
}

