package pieces;

import Game.Board;
import Game.Tile;
import pieces.Assassins.Action_Man;

import javax.swing.*;

public class Keegan extends Piece {
    public Keegan(int color) {
        super(color);
        value = 12;
        this.royal = true;
        this.wall = true;
        name = "Keegan";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bUnknown.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wUnknown.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(color, destination)){
            return false;
        }
        wall = false;
        if (destination.getPiece() != null && !(destination.getPiece() instanceof Keegan) && !(destination.getPiece() instanceof Action_Man)){
            if (destination.isLegalMove(Board.getLocationFromCords(x, y), board, false)){
                wall = true;
                return true;
            }
        }
        wall = true;
        return ((Math.abs(dx) <= 2) && (Math.abs(dy) <= 1));
    }
}