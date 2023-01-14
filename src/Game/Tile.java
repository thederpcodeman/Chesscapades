package Game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Tile extends JPanel {
    private Piece piece;
    private int location;
    static final Color tan = new Color(255, 248, 232);
    static final Color red = new Color(215, 122, 97);

    private final Color normalColor;

    int size;

    public Tile(int location, BorderLayout layout, int size)
    {
        super(layout);
        this.location = location;
        boolean isBlack;
        isBlack = location % 2 == 1;
        if (!((location / 8) % 2 == 0)) {
            isBlack = !isBlack;
        }
        if (!isBlack) {
            normalColor = tan;
        } else {
            normalColor = red;
        }
        setBackground(normalColor);
        this.size = size;
    }

    public boolean isOccupied()
    {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JLabel image = new JLabel(imageIcon);
            add(image);
            revalidate();
            repaint();
        }

        //check for pawn being on the top or bottom rows
        if (Board.getYFromLocation(getLocationOnBoard()) % 7 == 0 && getPiece() instanceof Pawn) {
            promPawn();
        }
    }

    public int getLocationOnBoard()
    {
        return location;
    }

    public Color getColor() {
        return normalColor;
    }

    public boolean isLegalMove(int location, Board board) {
        if(getPiece() == null)
        {
            return false;
        }
        int x = getLocationOnBoard() % 8;
        int y = getLocationOnBoard() / 8;

        int newX = location % 8;
        int newY = location / 8;
        return getPiece().isLegalMove(x, y, newX, newY, board);
    }

    public Tile[] getLegalMoves(Board board) {
        Tile[] allTiles = board.getTiles();
        ArrayList<Tile> goodTiles = new ArrayList();
        for (Tile tile:allTiles) {
            if(isLegalMove(tile.getLocationOnBoard(), board))
            {
                goodTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[goodTiles.size()];
        arr = goodTiles.toArray(arr);
        return arr;
    }

    public void promPawn() {
        int input;

        String[] possibilities = {"Queen", "Rook", "Knight", "Bishop"};
        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(possibilities);
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose a piece to promote to: ", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();

            if (s == "Queen") {
                setPiece(new Queen(1));
            } else if (s == "Rook") {
                setPiece(new Rook(1));
            } else if (s == "Knight") {
                setPiece(new Knight(1));
            } else if (s == "Bishop") {
                setPiece(new Bishop(1));
            }
        }
    }
}
