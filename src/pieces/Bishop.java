package pieces;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBishop.png"));
        } else {
            return null;
        }
    }
}
