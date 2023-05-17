package Game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class Tile extends JPanel {
    private Piece piece;
    private int location;
    static final Color tan = new Color(255, 248, 232);
    static final Color red = new Color(215, 122, 97);

    private final Color normalColor;
    private boolean castleable;
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

    public boolean isCastleable() {
        return castleable;
    }

    public void setCastleable(boolean b) {
        castleable = b;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
        setCastleable(false);
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            JLabel image = new JLabel(imageIcon);
            add(image);
        }
        //check for pawn being on the top or bottom rows

        if ( (getPiece() instanceof Pawn) && (((Board.getYFromLocation(getLocationOnBoard()) == 0) && (((Pawn) getPiece()).getForwardDirection() == -1)) || ((Board.getYFromLocation(getLocationOnBoard()) == 7) && (((Pawn) getPiece()).getForwardDirection() == 1)))){
            promPawn();
        }
        revalidate();
        repaint();
    }

    public void forceSetPiece(Piece piece)
    {
        this.piece = piece;
        setCastleable(false);
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            JLabel image = new JLabel(imageIcon);
            add(image);
        }
        revalidate();
        repaint();
    }

    public void quietlyUpdatePiece(Piece piece) {
        this.piece = piece;
    }

    public int getLocationOnBoard()
    {
        return location;
    }

    public Color getColor() {
        return normalColor;
    }

    public boolean isLegalMove(int location, Board board, boolean forReal) {
        if((getPiece() == null) || ((board.getTile(location).getPiece() != null) && (board.getTile(location).getPiece().wall == true)))
        {
            return false;
        }
        int x = getLocationOnBoard() % 8;
        int y = getLocationOnBoard() / 8;

        int newX = location % 8;
        int newY = location / 8;
        return getPiece().isLegalMove(x, y, newX, newY, board, forReal);
    }

    public int isPlayableMove(int location, Board board, boolean forReal) {
        if(!isLegalMove(location, board, forReal))
        {
            return 0;
        }
        int color = getPiece().getColor();
        Tile[] enemyPieces = board.getOccupiedTilesOfColor(1 - color);
        Tile destination = board.getTile(location);
        Piece currentPiece = getPiece();
        Piece destinationCurrentPiece = destination.getPiece();
        destination.quietlyUpdatePiece(getPiece());
        quietlyUpdatePiece(null);
        ArrayList<Tile> kings = board.getKings(color);
        for (Tile king : kings){
            for(Tile tile: enemyPieces)
            {
                for(Tile tile2: tile.getLegalMoves(board))
                {
                    if(tile2 == king)
                    {
                        quietlyUpdatePiece(currentPiece);
                        destination.quietlyUpdatePiece(destinationCurrentPiece);
                        return 2;
                    }
                }
            }
        }

        quietlyUpdatePiece(currentPiece);
        destination.quietlyUpdatePiece(destinationCurrentPiece);
        return 1;
    }

    public Tile[] getPlayableMoves(Board board) {
        Tile[] allTiles = board.getTiles();
        ArrayList<Tile> goodTiles = new ArrayList();
        for (Tile tile:allTiles) {
            if((isPlayableMove(tile.getLocationOnBoard(), board, false) != 0))
            {
                goodTiles.add(tile);
            }
        }
        Tile[] arr = new Tile[goodTiles.size()];
        arr = goodTiles.toArray(arr);
        return arr;
    }

    public Tile[] getLegalMoves(Board board) {
        Tile[] allTiles = board.getTiles();
        ArrayList<Tile> goodTiles = new ArrayList();
        for (Tile tile:allTiles) {
            if(isLegalMove(tile.getLocationOnBoard(), board, false))
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

        AudioPlayer.play("src/resources/audio/promote.wav");

        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add("Turn around");
        possibilities.add("Queen");
        possibilities.add("Rook");
        possibilities.add("Knight");
        possibilities.add("Bishop");
        possibilities.add("King 2, electric boogaloo");
        possibilities.add("Amazon");
        possibilities.add("Archbishop");
        possibilities.add("Chancellor");
        possibilities.add("Camel");
        possibilities.add("General");
        possibilities.add("Lion");
        possibilities.add("Frog");
        possibilities.add("Elephant");
        possibilities.add("Bull");
        possibilities.add("Gryphon");
        possibilities.add("No thank you.");
        possibilities.add("Boaty Mc Boatface");
        Collections.shuffle(possibilities);
        String[] options = new String[3];
        options[0] = possibilities.get(0);
        options[1] = possibilities.get(1);
        options[2] = possibilities.get(2);
        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(options);
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose a piece to promote to: ", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();

            if (s == "Queen") {
                setPiece(new Queen(piece.getColor()));
            } else if (s == "Rook") {
                setPiece(new Rook(piece.getColor()));
            } else if (s == "Knight") {
                setPiece(new Knight(piece.getColor()));
            } else if (s == "Bishop") {
                setPiece(new Bishop(piece.getColor()));
            } else if (s == "King 2, electric boogaloo") {
                setPiece(new King(piece.getColor()));
            }  else if (s == "Turn around") {
                getPiece().setForwardDirection(getPiece().getForwardDirection() * -1);
                setPiece(getPiece());
            } else if (s == "Amazon") {
                setPiece(new Amazon(piece.getColor()));
            } else if (s == "Archbishop") {
                setPiece(new Archbishop(piece.getColor()));
            } else if (s == "Chancellor") {
                setPiece(new Chancellor(piece.getColor()));
            } else if (s == "Camel") {
                setPiece(new Camel(piece.getColor()));
            } else if (s == "General") {
                setPiece(new General(piece.getColor()));
            } else if (s == "Lion") {
                setPiece(new Lion(piece.getColor()));
            } else if (s == "Frog") {
                setPiece(new Frog(piece.getColor()));
            } else if (s == "Elephant") {
                setPiece(new Elephant(piece.getColor()));
            } else if (s == "Bull") {
                setPiece(new Bull(piece.getColor()));
            } else if (s == "Gryphon") {
                setPiece(new Gryphon(piece.getColor()));
            } else if (s == "No thank you.") {
                setPiece(null);
            } else if (s == "Boaty Mc Boatface") {
                setPiece(new Boat(piece.getColor()));
            }
        }
    }
}
