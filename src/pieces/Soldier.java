package pieces;

import Game.Board;
import Game.Tile;

public class Soldier extends Pawn{
    public Soldier(int color){
        super(color);
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        Tile destination = board.getTile(board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(getColor(), destination) || x != newX){
            return false;
        }
        int dy = newY - y;

        if (dy == getForwardDirection()){
            return true;
        } else if (dy == 2 * getForwardDirection() && isOnStartingSquare(y) && board.getTile(Board.getLocationFromCords(newX, newY)).getPiece() == null){
            if (forReal){
                moved2 = 2;
            }
            return true;
        }
        return false;
    }
    private boolean isOnStartingSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y <= 1;
        }
        else
        {
            return y >= 6;
        }
    }
}
