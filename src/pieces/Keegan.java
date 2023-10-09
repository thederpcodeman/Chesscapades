package pieces;

import Game.Board;
import Game.Tile;
import pieces.Assassins.Action_Man;

import javax.swing.*;

public class Keegan extends Piece {
    public Keegan(int color) {
        super(color);
        value = 4;
        this.royal = true;
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
        int dy = newY - y;
        int dx = newX - x;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination) && (Moves.frogMove(dx, dy) || Moves.elephantMove(dx, dy)));
    }
}