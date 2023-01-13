package pieces;

import Game.Board;

import javax.swing.*;

public class Bishop extends Piece {
    public Bishop(int color) {
        super(color);
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bBishop.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wBishop.png"));
        } else {
            return null;
        }
    }
    public boolean isLegalMove(int x, int y, int newx, int newy, Board board){
        int yoffset = newy - y;
        int xoffset = newx - x;
        if (Math.abs(xoffset) == Math.abs(yoffset)){
            for (int i = 1; i < Math.abs(yoffset); i++){
                if (board.getTile(Board.getLocationFromCords((int)(x + (i * Math.signum(xoffset))), (int)(y + (i * Math.signum(yoffset))))).getPiece() != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
