import pieces.*;

public class Board {

    Piece[][] board = new Piece[8][8];

    public Board() {
        board[0][0] = new Rook(1);
        board[0][1] = new Knight(1);
        board[0][2] = new Bishop(1);
        board[0][3] = new Queen(1);
        board[0][4] = new King(1);
        board[0][5] = new Bishop(1);
        board[0][6] = new Knight(1);
        board[0][7] = new Rook(1);
        board[1][0] = new Pawn(1);
        board[1][1] = new Pawn(1);
        board[1][2] = new Pawn(1);
        board[1][3] = new Pawn(1);
        board[1][4] = new Pawn(1);
        board[1][5] = new Pawn(1);
        board[1][6] = new Pawn(1);
        board[1][7] = new Pawn(1);

        board[7][0] = new Rook(0);
        board[7][1] = new Knight(0);
        board[7][2] = new Bishop(0);
        board[7][3] = new Queen(0);
        board[7][4] = new King(0);
        board[7][5] = new Bishop(0);
        board[7][6] = new Knight(0);
        board[7][7] = new Rook(0);
        board[6][0] = new Pawn(0);
        board[6][1] = new Pawn(0);
        board[6][2] = new Pawn(0);
        board[6][3] = new Pawn(0);
        board[6][4] = new Pawn(0);
        board[6][5] = new Pawn(0);
        board[6][6] = new Pawn(0);
        board[6][7] = new Pawn(0);
    }


    Piece getPieceFromPosition(int location) {
        int column = location / 8;
        int row = location % 8;
        return getPieceFromPosition(column, row);
    }
    Piece getPieceFromPosition(int column, int row) {
        return board[column][row];
    }

    Piece getPieceFromPosition(String position) {
        if (position.length() != 2){
            throw new IllegalArgumentException();
        }
        int column = convertColumnToInt(position.charAt(0));
        int row = convertRowToInt(position.charAt(1));
        return board[column][row];
    }
    int convertColumnToInt(char column) {
        column = Character.toUpperCase(column);
        if (column == 'A')
        {
            return 0;
        }
        else if (column == 'B')
        {
            return 1;
        }
        else if (column == 'C')
        {
            return 2;
        }
        else if (column == 'D')
        {
            return 3;
        }
        else if (column == 'E')
        {
            return 4;
        }
        else if (column == 'F')
        {
            return 5;
        }
        else if (column == 'G')
        {
            return 6;
        }
        else if (column == 'H')
        {
            return 7;
        }
        else {
            throw new IndexOutOfBoundsException("letter must be a-h");
        }
    }
    int convertRowToInt(int row) {
        if (row > 8 || row < 1) {
            throw new IndexOutOfBoundsException("number must be 1-8");
        }
        return row - 1;
    }
}
