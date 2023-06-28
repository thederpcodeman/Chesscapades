package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Mage extends Piece {
    public int mana;
    public Mage(int color) {
        super(color);
        value = 5;
        mana = -1;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bMage.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wMage.png"));
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
            return (destination.getPiece() == null);
        }
        if (board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null){
            return (false);
        }
        if (dx == 0){
            if (Moves.allClear(getColor(), destination) && (Math.abs(dy) <= mana)){
                if (forReal){
                    mana -= Math.abs(dy) + 1;
                }
                return true;
            }
        }else if (dy == 0){
            if (Moves.allClear(getColor(), destination) && (Math.abs(dx) <= mana)){
                if (forReal){
                    mana -= Math.abs(dx) + 1;
                }
                return true;
            }
        }
        return false;
    }
}
