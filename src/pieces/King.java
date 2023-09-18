package pieces;

import Game.Board;
import Game.Tile;

import javax.swing.*;

public class King extends Piece {
    private boolean canCastle;

    public King(int color) {
        super(color);
        royal = true;
        canCastle = true;
        value = 3;
        name = "King";
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bKing.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wKing.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        boolean cc = canCastle;
        if (forReal){
            canCastle = false;
        }
        Tile destinationTile = board.getTile(board.getLocationFromCords(newX, newY));
        if (!Moves.allClear(getColor(), destinationTile)){
            return false;
        }
        int dx = Math.abs(newX - x);
        int dy = Math.abs(newY - y);
        if(dx <= 1 && dy <= 1)
        {
            return true;
        }
        Tile start = board.getTile(board.getLocationFromCords(x, y));
        if (cc && dy == 0 && isOnStartingSquare(y)){ // (this has not moved) && (does not move vertically) && (is in a castle-able position)
            if (dy == 2){
                boolean cont = true;
                int loc = -1;
                for (int i = x +1; (x < 8 && cont); x++){ // finds the location of the first applicable rook
                    if (board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() != null && board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() instanceof Rook){ //finds a rook
                        loc = i;
                        cont = false;
                    }else if (board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() != null){ // finds and stops the loop if there is something in the way.
                        cont = false;
                    }
                }
                if (loc != -1){ // success, asuming the rook we found can castle
                    Rook r = (Rook) (board.getTile(Board.getLocationFromCords(loc, color * 7)).getPiece());
                    if (r.canCastle()){
                        if (forReal){
                            r.setCastleable(false);
                            board.getTile(Board.getLocationFromCords(x + 1, y)).setPiece(r);
                            board.getTile(Board.getLocationFromCords(loc, color * 7)).setPiece(null);
                        }
                        return true;
                    }
                }
            }else if (dy == -2){
                boolean cont = true;
                int loc = -1;
                for (int i = x -1; (x > 0 && cont); x--){ // finds the location of the first applicable rook
                    if (board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() != null && board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() instanceof Rook){ //finds a rook
                        loc = i;
                        cont = false;
                    }else if (board.getTile(Board.getLocationFromCords(i, color * 7)).getPiece() != null){ // finds and stops the loop if there is something in the way.
                        cont = false;
                    }
                }
                if (loc != -1){ // success, asuming the rook we found can castle
                    Rook r = (Rook) (board.getTile(Board.getLocationFromCords(loc, color * 7)).getPiece());
                    if (r.canCastle()){
                        if (forReal){
                            r.setCastleable(false);
                            board.getTile(Board.getLocationFromCords(x - 1, y)).setPiece(r);
                            board.getTile(Board.getLocationFromCords(loc, color * 7)).setPiece(null);
                        }
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public void setCastleable(boolean b)
    {
        canCastle = b;
    }

    public boolean isInCheck(Board board)
    {
        int color = getColor();
        Tile[] enemyPieces = board.getOccupiedTilesOfColor(1 - color);
        Tile king = board.getKing(color);
        for(Tile tile: enemyPieces) {

            if (!(tile.getPiece() instanceof King))
            {
                for (Tile tile2 : tile.getLegalMoves(board))
                {
                    if (tile2 == king)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isOnStartingSquare(int y)
    {
        if(getForwardDirection() == 1)
        {
            return y < 1;
        }
        else
        {
            return y > 6;
        }
    }
}
