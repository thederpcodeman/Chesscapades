package Game;

import pieces.King;
import pieces.Pawn;
import pieces.Piece;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class fakeGame {
    public static int score(Board chessBoard, int team, int stepsLeft, int turn, int at, int ran) {
        if (stepsLeft == 0){
            if (chessBoard.getKings(team).size() == 0){
                if (chessBoard.getKings((team + 1) % 2).size() == 0){
                    return 0;
                } else {
                    return -100;
                }
            } else if (chessBoard.getKings((team + 1) % 2).size() == 0){
                return 100;
            } else {
                int score = 0;
                for (Tile fr : chessBoard.getOccupiedTilesOfColor(team)){
                    score += fr.getPiece().value;
                }
                for (Tile fr : chessBoard.getOccupiedTilesOfColor((team + 1) % 2)){
                    score -= fr.getPiece().value;
                }
                return score;
            }
        }
        else {
            ArrayList<moveInfo> choices = new ArrayList<moveInfo>();
            for (Tile myDude : chessBoard.getOccupiedTilesOfColor(turn)){
                for (Tile place : myDude.getLegalMoves(chessBoard)){
                    choices.add(new moveInfo(myDude, place, chessBoard));
                }
            }
            if (choices.size() == 0){
                System.exit(0);
            }
            Collections.shuffle(choices);

            int moveMin;
            if (team == turn){
                moveMin = -100;
            }else{
                moveMin = 100;
            }
            for (int mov = 0; mov < choices.size(); mov++){
                int sc = 0;
                if ((choices.get(mov).end.getPiece() != null) && (choices.get(mov).end.getPiece().royal)){
                    if (choices.get(mov).end.getPiece().color == team){
                        if (choices.get(mov).start.isPlayableMove(choices.get(mov).end.getLocationOnBoard(), chessBoard, false) == 2){
                            sc = -50;
                        } else{
                            sc = -100;
                        }
                    }else{
                        if (choices.get(mov).start.isPlayableMove(choices.get(mov).end.getLocationOnBoard(), chessBoard, false) == 2){
                            sc = 50;
                        } else{
                            sc = 100;
                        }
                    }
                }else {
                    Board b = fakeGame.update(chessBoard, choices.get(mov), at, ran);
                    sc = fakeGame.score(b, team, stepsLeft - 1, ((turn +1) %2), at, ran);
                }

                if (((sc > moveMin) && (team == turn)) || ((sc < moveMin) && (team != turn))){
                    moveMin = sc;
                }
            }
            return (moveMin);
        }

    }
    public static Board update(Board b, moveInfo move, int at, int ran){
        Board chessBoard = new Board();
        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i, new BorderLayout(), 20);
            chessBoard.add(tile);
        }
        for (Tile i : b.getOccupiedTiles()){
            chessBoard.getTile(i.getLocationOnBoard()).setPiece(i.getPiece());
        }

        //play the move
        move.start.isLegalMove(move.end.getLocationOnBoard(), chessBoard, true);
        if ((move.end.getPiece() != null) && (at > 0)){
            boolean ks = false;
            boolean ps = false;
            if ((at - 1) / 2 == 0){
                ks = true;
            }
            if (at % 2 == 1){
                ps = true;
            }
            nuke(chessBoard, move.end.getLocationOnBoard(), ks, ps);
            if (ran == 0){
                obliterate(chessBoard, move.start.getLocationOnBoard(), ks, ps);
            }

        }
        if (ran == 0){
            move.end.setPiece(move.start.getPiece());
            move.start.setPiece(null);
        }
        return chessBoard;
    }
    public static void obliterate(Board chessBoard, int square, boolean kingSlayer, boolean pawnSlayer){
        if ( (chessBoard.getTile(square) != null) && (chessBoard.getTile(square).getPiece() != null) && (kingSlayer || !(chessBoard.getTile(square).getPiece() instanceof King)) && (pawnSlayer || !(chessBoard.getTile(square).getPiece() instanceof Pawn))) {
            chessBoard.getTile(square).setPiece(null);
        }
    }
    public static void nuke(Board chessBoard, int square, boolean kingSlayer, boolean pawnSlayer) {
        int y = Board.getYFromLocation(square);
        int x = Board.getXFromLocation(square);
        int Yoff = -1;
        while (Yoff <= 1){
            int Xoff = -1;
            while (Xoff <= 1){
                if ((y + Yoff >= 0) && (y + Yoff < 8) && (x + Xoff >= 0) && (x + Xoff < 8)){
                    obliterate(chessBoard, Board.getLocationFromCords(x + Xoff,y + Yoff ), kingSlayer, pawnSlayer);
                }
                Xoff ++;
            }
            Yoff ++;
        }
    }
}
