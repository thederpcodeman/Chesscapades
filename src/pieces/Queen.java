package pieces;

import javax.swing.*;

public class Queen extends Piece {
    public Queen(int color) {
        super(color);
    }

    @Override
    public ImageIcon pieceColor() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bQueen.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wQueen.png"));
        } else {
            return null;
        }
    }
}
