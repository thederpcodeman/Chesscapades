package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class Bladesinger extends Piece {
    public Bladesinger(int color) {
        super(color);
        value = 14;
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
        if ((Math.abs(dx) > 1 || Math.abs(dy) > 1) && destination.getPiece() == null){
            return ((Moves.wyvernMove(newX, newY, dx * -1, dy * - 1, board) || Moves.gryphonMove(newX, newY, dx * -1, dy * -1, board) || (Moves.wyvernMove(x, y, dx, dy, board) || Moves.gryphonMove(x, y, dx, dy, board))));
        }else if (Math.abs(dx) <= 1 && Math.abs(dy) <= 1 && destination.getPiece() != null){
            return true;
        }
        return false;
    }
}
