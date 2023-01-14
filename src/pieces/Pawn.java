package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Pawn extends Piece {
    public Pawn(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bPawn.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wPawn.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board) {
        if (newX != x)
        {
            return false;
        }
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if(destination.isOccupied())
        {
            if(destination.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        int dy = newY - y;
        if(dy == getForwardDirection() || (isOnStartingSquare(y) && dy == getForwardDirection() * 2))
        {
            return true;
        }
        return false;
    }

    private int getForwardDirection()
    {
        if(getColor() == 0)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    private boolean isOnStartingSquare(int y)
    {
        if(getColor() == 0)
        {
            return y == 1;
        }
        else
        {
            return y == 6;
        }
    }
}
