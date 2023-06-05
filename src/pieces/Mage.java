package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Mage extends Piece {
    public Mage(int color) {
        super(color);
        value = 7;
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
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;

        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if (Math.abs(dy) <= 1 && Math.abs(dx) <= 1){
            System.out.println("ahh");
            return (destination.getPiece() == null);
        }
        return (dx == 0 && Moves.allClear(getColor(), destination) && (Math.abs(dy) == dy * getForwardDirection()));
    }
}
