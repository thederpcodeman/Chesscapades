import pieces.Piece;

public class Board {

    Piece[][] board = new Piece[8][8];

    public Board() {

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
