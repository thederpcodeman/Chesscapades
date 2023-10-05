package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Bladedancer extends Piece {
    public Bladedancer(int color) {
        super(color);
        value = 7;
        name = "Blade Dancer";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bAssassin.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wAssassin.png"));
        } else {
            return null;
        }
    }
    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal){
        int dx = newX - x;
        int dy = newY - y;
        if (Math.abs(dy ) <= 1 && Math.abs(dx ) <= 1){
            return false;
        }
        if (Math.abs(dy ) > 2 && Math.abs(dx ) > 2){
            return false;
        }
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        return (Moves.allClear(getColor(), destination));
    }
}
