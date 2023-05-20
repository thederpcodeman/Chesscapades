package pieces;

import Game.Board;
import Game.Tile;

public class Moves {

    public static boolean allClear(int color, Tile destination){
        if(destination.getPiece() != null && destination.getPiece().getColor() == color)
        {
            return false;
        }
        return true;
    }
    public static boolean knightMove(int dx, int dy){
        if (((Math.abs(dx) == 1) && (Math.abs(dy) == 2)) || ((Math.abs(dx) == 2) && (Math.abs(dy) == 1))){
            return true;
        }
        return false;
    }
    public static boolean rookMove(int x, int y, int dx, int dy, Board board) {
        if (dy == 0) {
            for (int i = 1; i < Math.abs(dx); i++) {
                if (board.getTile(Board.getLocationFromCords((int) (x + (i * Math.signum(dx))), y)).getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else if (dx == 0) {
            for (int i = 1; i < Math.abs(dy); i++) {
                if (board.getTile(Board.getLocationFromCords(x, (int) (y + (i * Math.signum(dy))))).getPiece() != null) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
}
