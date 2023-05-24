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
        score -= start.getPiece().value / 8.9;
        if (end.getPiece() != null){
            if (end.getPiece().color != start.getPiece().getColor()){
                score += (end.getPiece().value * 4.5);
                if (end.getPiece().royal) {
                    score += 100;
                }
            }else{
                score -= (end.getPiece().value * 4.5);
                if (end.getPiece().royal) {
                    score -= 100;
                }
                score -= 10;
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
            score -= 20;
        }
        Piece store = end.getPiece();
        end.setPiece(null);
        for (Tile foe : board.getOccupiedTiles()){
            try{
                if (foe != null && foe.getPiece() != null){
                    if ((foe.isLegalMove(end.getLocationOnBoard(), board, false)) && (foe.getPiece().getColor() != start.getPiece().getColor())) {
                        score -= start.getPiece().value * 5.5;
                    }
                }
            }catch (Exception e){
                score -= Math.random() * 3;
            }
        }
        end.setPiece(store);
        if ((end.getY() - start.getY()) * start.getPiece().getForwardDirection() > 0){ // i'm a dingus who used jpanel .get y ad is too lazy to change it and just divided by 100
            score += (end.getY() - start.getY()) * start.getPiece().getForwardDirection()  / 400;

        }
        score += Math.random() * 5;
        return score;
    }
}
