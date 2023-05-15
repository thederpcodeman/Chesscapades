package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class General extends King{
    private boolean canCastle;

    public General(int color) {
        super(color);
        canCastle = false;
        value = 5;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bGeneral.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wGeneral.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destinationTile = board.getTile(board.getLocationFromCords(newX, newY));
        if(destinationTile.isOccupied())
        {
            if(destinationTile.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);

        if (((Math.abs(dx) == 1) && (Math.abs(dy) == 2)) || ((Math.abs(dx) == 2) && (Math.abs(dy) == 1))) {
            return true;
        }

        if(dx <= 1 && dy <= 1)
        {
            return true;
        }
        return false;
    }
}
