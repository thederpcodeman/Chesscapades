package pieces;

        import Game.Board;
        import Game.Tile;

        import javax.swing.*;

public class Gryphon extends Piece {
    public Gryphon(int color) {
        super(color);
        value = 8;
    }

    @Override
    public ImageIcon getImageIcon() {
        if(color == 0) {
            return(new ImageIcon("src/resources/bGryphon.png"));
        } else if(color == 1) {
            return(new ImageIcon("src/resources/wGryphon.png"));
        } else {
            return null;
        }
    }

    @Override
    public boolean isLegalMove(int x, int y, int newX, int newY, Board board, boolean forReal) {
        int dx = newX - x;
        int dy = newY - y;
        Tile destination = board.getTile(Board.getLocationFromCords(newX, newY));
        if(destination.isOccupied()) {
            if(destination.getPiece().getColor() == getColor())
            {
                return false;
            }
        }
        if (dy == 0 || dx == 0){
            System.out.println(newX + "" + newY);
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
                if (c){//
                    return true;
                }
            }
        }
        return false;
    }
}
