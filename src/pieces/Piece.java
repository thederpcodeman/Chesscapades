package pieces;

import javax.swing.*;

public abstract class Piece {
    public final int color;
    public String imgPath;

    public Piece(int color) {
        this.color = color;
    }

    public ImageIcon getImageIcon() {
        return null;
    }

    public int getColor() {
        return color;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String setImgPath() {
        this.imgPath = imgPath;
        return imgPath;
    }
    public boolean isMovable() {
        return isMovable();
    }

    public boolean canJump() {
        return canJump();
    }

}
