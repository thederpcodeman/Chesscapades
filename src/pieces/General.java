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
        name = "General";
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
        if (!Moves.allClear(getColor(), destinationTile)){
            return false;
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);

        return ((dx <= 1 && dy <= 1) || Moves.knightMove(dx, dy)) ;
    }
}
