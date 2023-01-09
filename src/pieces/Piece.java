package pieces;

import javax.swing.*;

public abstract class Piece {
    private final int color;
    private String imgPath;

    public Piece(int color, String imgPath) {
        this.color = color;
        this.imgPath = imgPath;

        ImageIcon img = new ImageIcon(imgPath);

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
