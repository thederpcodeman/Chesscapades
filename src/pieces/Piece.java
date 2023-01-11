package pieces;

import javax.swing.*;

public abstract class Piece {
    public final int color;
    public Piece(int color) {
        this.color = color;
    }

    public ImageIcon getImageIcon() {
        return null;
    }

    public int getColor() {
        return color;
    }

    public boolean isMovable() {
        return isMovable();
    }

    public boolean canJump() {
        return canJump();
    }

}
