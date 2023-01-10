package pieces;

import javax.swing.*;

public class King extends Piece {
    public King(int color) {
        super(color);
    }

    @Override
    public ImageIcon pieceColor() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKing.png"));
        } else {
            return null;
        }
    }
}
