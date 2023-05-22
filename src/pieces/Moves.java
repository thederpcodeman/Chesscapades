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

    public static boolean bishopMove(int x, int y, int dx, int dy, Board board){
        if (Math.abs(dx) == Math.abs(dy)){
            for (int i = 1; i < Math.abs(dy); i++){
                if (board.getTile(Board.getLocationFromCords((int)(x + (i * Math.signum(dx))), (int)(y + (i * Math.signum(dy))))).getPiece() != null){
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    public static boolean GryphonMove(int x, int y, int dx, int dy, Board board){
        int newX = x + dx;
        int newY = y + dy;
        if (dy == 0 || dx == 0){
            return false;
        }
        if (dx == 1){
            if (dy >= 1){
                boolean c = true;
                for (int i = 1; i < dy; i++){
                    if (board.getTile(Board.getLocationFromCords(newX, (int)(y + i))).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            } else if (dy <= 1){
                boolean c = true;
                for (int i = -1; i > dy; i--){
                    if (board.getTile(Board.getLocationFromCords(newX, (int)(y + i))).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            }
        }
        if (dy == 1){
            if (dx >= 1){
                boolean c = true;
                for (int i = 1; i < dx; i++){
                    if (board.getTile(Board.getLocationFromCords((int)(x + i), newY)).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            } else if (dy <= 1){
                boolean c = true;
                for (int i = -1; i > dx; i--){
                    if (board.getTile(Board.getLocationFromCords((int)(x + i), newY)).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            }
        }
        if (dx == -1){
            if (dy >= 1){
                boolean c = true;
                for (int i = 1; i < dy; i++){
                    if (board.getTile(Board.getLocationFromCords(newX, (int)(y + i))).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            } else if (dy <= 1){
                boolean c = true;
                for (int i = -1; i > dy; i--){
                    if (board.getTile(Board.getLocationFromCords(newX, (int)(y + i))).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            }
        }
        if (dy == -1){
            if (dx >= 1){
                boolean c = true;
                for (int i = 1; i < dx; i++){
                    if (board.getTile(Board.getLocationFromCords((int)(x + i), newY)).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            } else if (dy <= 1){
                boolean c = true;
                for (int i = -1; i > dx; i--){
                    if (board.getTile(Board.getLocationFromCords((int)(x + i), newY)).getPiece() != null){
                        c = false;
                    }
                }
                if (c){
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean pegasusMove(int x, int y, int dx, int dy, Board board){
        if (Moves.knightMove(dx, dy)){
            return true;
        }
        if (((Math.abs(dx) == 2) && (Math.abs(dy) == 4)) || ((Math.abs(dx) == 4) && (Math.abs(dy) == 2))){
            return (board.getTile(Board.getLocationFromCords(x + (dx / 2), y + (dy / 2))) == null);
        }
        return false;
    }
}
