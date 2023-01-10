package pieces;

import javax.swing.*;

public abstract class Piece {
    public final int color;
    public String imgPath;

    public Piece(int color) {
        this.color = color;
    }

    public ImageIcon pieceColor() {
        if(color == 0) {
            return null;
        } else if(color == 1) {
            return null;
        } else {
            return null;
        }
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
