package pieces;

import javax.swing.*;

public class Knight extends Piece {
    public Knight(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKnight.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKnight.png"));
        } else {
            return null;
        }
    }
}
