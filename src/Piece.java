import javax.swing.*;
import java.awt.*;

public class Piece {
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

    public void draw(Graphics g) {
    //TODO: implement
    }

}
