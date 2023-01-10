package pieces;

import javax.swing.*;

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public ImageIcon pieceColor() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bPawn.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wPawn.png"));
        } else {
            return null;
        }
    }
}
