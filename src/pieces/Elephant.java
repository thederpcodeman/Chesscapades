package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Elephant extends Piece {
    public Elephant(int color) {
        super(color);
        value = 3;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bElephant.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wElephant.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int yoffset = newY - y;
        int xoffset = newX - x;
        if (Math.abs(xoffset) == Math.abs(yoffset)){
            Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
            if(destination.isOccupied()) {
                if (destination.getPiece().getColor() == getColor()) {
                    return false;
                }
            }
            if (Math.abs(yoffset) > 2){
                return false;
            }
            return true;
        }
        return false;
    }
}
