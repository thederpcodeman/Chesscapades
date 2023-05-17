package pieces;

import Game.Board;

public class Soldier extends Pawn{
    public Soldier(int color){
        super(color);
    }
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        if (newX != x){
            return false;
        }
        return true;
    }//
}
