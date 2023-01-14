package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class King extends Piece {
    public King(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKing.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if(destination.isOccupied())
        {
            if(destination.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx > 1 || dy > 1)
        {
            return false;
        }
        return true;
    }
}
