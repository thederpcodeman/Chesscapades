package pieces;

import Game.Board;
import Game.ChessGame;
import Game.Tile;

import javax.swing.*;

public class rPawn extends Pawn {
    public rPawn(int color) {
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

    public int getForwardDirection()
    {
        if(getColor() == 1)
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
        if(getColor() == 1)
        {
            return y <= 1;
        }
        else
        {
            return y >= 6;
        }
    }
}
