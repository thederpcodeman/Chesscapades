package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Wall extends Pawn{
    public Wall(int color){
        super(color);
        wall = true;
    }

    public ImageIcon getImageIcon() {
        if(color == 0) {
            if (getForwardDirection() == 1){
                return(new ImageIcon("src/resources/wUnknown.png"));
            }else{
                return(new ImageIcon("src/resources/wUnknown.png"));
            }

        } else if(color == 1) {
            if (getForwardDirection() == -1){
                return(new ImageIcon("src/resources/bUnknown.png"));
            }else{
                return(new ImageIcon("src/resources/bUnknown.png"));
            }
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if (destination.getPiece() == null && (x == newX) && (newY == y + getForwardDirection())){
            return true;
        }
        return false;
    }

}
