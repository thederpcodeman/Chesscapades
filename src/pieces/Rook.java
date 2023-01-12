package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Rook extends Piece {
    public Rook(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bRook.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wRook.png"));
        } else {
            return null;
        }
    }



}
