package Game;

import Game.Board;
import Game.Tile;
import pieces.Piece;

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
        if (end.getPiece() != null){
            if (end.getPiece().color != start.getPiece().getColor()){
                score += (end.getPiece().value * 100);
                if (end.getPiece().royal) {
                    score += 1000;
                }
            }else{
                score -= (end.getPiece().value * 100);
                if (end.getPiece().royal) {
                    score -= 1000;
                }
            }

        }
        if (start.isPlayableMove(end.getLocationOnBoard(), board, false) == 2){
            score -= 1000;
        }
        Piece store = end.getPiece();
        end.quietlyUpdatePiece(start.getPiece());
        start.quietlyUpdatePiece(null);
        for (Tile foe : board.getOccupiedTilesOfColor(1 - end.getPiece().getColor())){
            if (foe.isLegalMove(end.getLocationOnBoard(), board, false)){
                score -= end.getPiece().value * 5;
            }
        }
        for (Tile foe : board.getOccupiedTilesOfColor(1 - end.getPiece().getColor())){
            for (Tile ours : board.getOccupiedTilesOfColor(end.getPiece().getColor())){
                if (foe.isLegalMove(ours.getLocationOnBoard(), board, false)){
                    score -= end.getPiece().value * 5;
                }
            }
        }
        for (Tile king : board.getKings(1 - end.getPiece().getColor())){
            for(Tile tile: board.getOccupiedTilesOfColor(end.getPiece().getColor()))
            {
                if (tile.isLegalMove(king.getLocationOnBoard(), board, false)){
                    score += 25;
                }
            }
        }
        start.quietlyUpdatePiece(end.getPiece());
        end.quietlyUpdatePiece(store);
        score += Math.random() * 5;
        return score;
    }
}
