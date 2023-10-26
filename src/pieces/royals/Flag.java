package pieces.royals;

import Game.Board;
import Game.Tile;
import pieces.*;

import javax.swing.*;

public class Flag extends Piece {

    public Flag(int color) {
        super(color);
        royal = true;
        value = 4;
        name = "Flag bearer";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bFlag.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wFlag.png"));
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
        if(dx <= 1 && dy <= 1)
        {
            if (forReal && isEndSquare(y)){
                for (Tile i : board.getOccupiedTilesOfColor(color - 1)){
                    i.getPiece().color = color;
                }
            }
            return true;
        }

        return false;
    }

    private boolean isEndSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y == 7;
        }
        else
        {
            return y == 0;
        }
    }
}
