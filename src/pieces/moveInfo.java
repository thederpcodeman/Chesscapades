package pieces;

import Game.Board;
import Game.Tile;

public class moveInfo {
    public Tile start;
    public Tile end;
    public Board board;
    public moveInfo(Tile s, Tile e, Board cboard){
        start = s;
        end = e;
        board = cboard;
    }
    public double score(){
        double score = 0;
        score -= start.getPiece().value / 2.0;
        if (end.getPiece() != null){
            score += end.getPiece().value;
            score += Math.random() * 3;
            if (end.getPiece().royal) {
                score += 10 + (Math.random() * 5);
            }
        }
        for (Tile king : board.getKings(start.getPiece().getColor())) {
            int c = start.isPlayableMove(king.getLocationOnBoard(), board, false);
            if (c == 1) {
                score += 5;
            } else if (c == 2){
                score += 1;
            }
        }
        if (start.isPlayableMove(end.getLocationOnBoard(), board, false) == 2) {
            score -= 5 + (Math.random() * 5);
        }
        score += Math.random() * 3;
        return score;
    }
}
