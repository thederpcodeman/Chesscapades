package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Berserker extends Piece {
    public Berserker(int color) {
        super(color);
        value = 6;
        name = "Berserker";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bViking.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wViking.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(color, destination) && Moves.norseMove(board, 3, x, y, newX, newY));
    }
}