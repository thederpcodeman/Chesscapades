package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Camel extends Piece {
    public Camel(int color) {
        super(color);
        value = 3;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bCamel.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wCamel.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int xoffset = newX - x;
        int yoffset = newY - y;
        if (((Math.abs(xoffset) == 1) && (Math.abs(yoffset) == 3)) || ((Math.abs(xoffset) == 3) && (Math.abs(yoffset) == 1))){
            Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
            if(destination.isOccupied())
            {
                if(destination.getPiece().getColor() == getColor())
                {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
