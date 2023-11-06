package Game;

import pieces.*;
import pieces.Assassins.Action_Man;
import pieces.Assassins.Assassin;
import pieces.Assassins.Bladedancer;
import pieces.pawns.Checker;
import pieces.pawns.Pawn;
import pieces.pawns.Prince;
import pieces.royals.Flag;
import pieces.royals.General;
import pieces.royals.King;
import pieces.royals.Tyrant;

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
            ImageIcon imageIcon = null;
            if (ChessGame.myst){
                if (getPiece().color == 1){
                    imageIcon = new ImageIcon(new ImageIcon("src/resources/wUnknown.png").getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
                }else if (getPiece().color == 0){
                    imageIcon = new ImageIcon(new ImageIcon("src/resources/bUnknown.png").getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
                }
            }else if (ChessGame.skatter){
                imageIcon = Skatterer.rimage(getPiece().getColor(), size);
            }else{
                imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            }
            JLabel image = new JLabel(imageIcon);
            add(image);
        }
        //check for pawn being on the top or bottom rows

        if ( (getPiece() instanceof Pawn && (((Board.getYFromLocation(getLocationOnBoard()) == 0) && (( getPiece()).getForwardDirection() == -1)) || ((Board.getYFromLocation(getLocationOnBoard()) == 7) && (( getPiece()).getForwardDirection() == 1))))){
            promPawn();
        }else if ( (getPiece() instanceof Mage && (((Board.getYFromLocation(getLocationOnBoard()) == 0) && (( getPiece()).getForwardDirection() == -1)) || ((Board.getYFromLocation(getLocationOnBoard()) == 7) && (( getPiece()).getForwardDirection() == 1))))){
            setPiece(new Archmage(getPiece().getColor()));
            return;
        }
        revalidate();
        repaint();
    }

    public void forceSetPiece(Piece piece)
    {
        this.piece = piece;
        for(int i = 0; i < getComponentCount(); i++)
        {
            remove(i);
        }
        if(piece != null)
        {
            ImageIcon imageIcon = new ImageIcon(piece.getImageIcon().getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            if (ChessGame.myst){
                imageIcon = new ImageIcon(new ImageIcon("src/resources/bAmazon.png").getImage().getScaledInstance(size * 7 / 8, size * 7 / 8, Image.SCALE_DEFAULT));
            }
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
        if((getPiece() == null) || ((board.getTile(location).getPiece() != null) && (board.getTile(location).getPiece().wall == true && !(getPiece() instanceof Pawn && getPiece().wall) &&  !getPiece().royal)))
        {
            return false;
        }
        if (location == getLocationOnBoard()) {
            return false;
        }
        if (ChessGame.wBackWall && Board.getYFromLocation(location) == 7){
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
        if (destination.getPiece() != null && destination.getPiece().royal == true && destination.getPiece().color == color){
            return 2;
        }
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

        if (getPiece() instanceof Checker){
            getPiece().royal = true;
            forceSetPiece(getPiece());
            return ;
        }

        ArrayList<String> possibilities = new ArrayList<String>();
        possibilities.add("Turn around");
        possibilities.add("Queen");
        possibilities.add("Rook");
        possibilities.add("Knight");
        possibilities.add("Bishop");
        possibilities.add("King");
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
        possibilities.add("Pegasus rider");
        possibilities.add("Tyrant");
        possibilities.add("No thank you.");
        possibilities.add("Ship");
        possibilities.add("Spider");
        possibilities.add("Assassin");
        possibilities.add("Manticore");
        possibilities.add("Greatwyrm");
        possibilities.add("Mage");
        possibilities.add("Immortal");
        possibilities.add("Spearman");
        possibilities.add("Falcon");
        possibilities.add("Flag bearer");
        possibilities.add("Crusader");
        Collections.shuffle(possibilities);
        int o = 3;
        if (getPiece().getColor() == 0){
            o = Board.bRadness;
        }else if (getPiece().getColor() == 1){
            o = Board.wRadness;
        }
        if (o > 10){
            o = 10;
        }
        String[] options = new String[o];
        for (int i = 0; i < o; i++){
            options[i] = possibilities.get(i);
        }
        JPanel jPanel = new JPanel(new GridBagLayout());
        JComboBox comboBox = new JComboBox(options);
        input = JOptionPane.showConfirmDialog(null, comboBox, "Choose a piece to promote to: ", JOptionPane.DEFAULT_OPTION);
        jPanel.add(comboBox);

        if(input == JOptionPane.OK_OPTION) {
            String s = (String) comboBox.getSelectedItem();
            boolean roy = (getPiece() instanceof Prince);

            if (s == "Queen") {
                setPiece(new Queen(piece.getColor()));
            } else if (s == "Rook") {
                setPiece(new Rook(piece.getColor()));
            } else if (s == "Crusader") {
                setPiece(new Crusader(piece.getColor()));
            } else if (s == "Knight") {
                setPiece(new Knight(piece.getColor()));
            } else if (s == "Bishop") {
                setPiece(new Bishop(piece.getColor()));
            } else if (s == "King") {
                setPiece(new King(piece.getColor()));
            }  else if (s == "Turn around") {
                if (getPiece().getColor() == 1){
                    Board.wRadness += 3;
                }else if (getPiece().getColor() == 0){
                    Board.bRadness += 3;
                }
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
                if (getPiece().getColor() == 1){
                    Board.wRadness += 3;
                }else if (getPiece().getColor() == 0){
                    Board.bRadness += 3;
                }
                setPiece(null);
            } else if (s == "Ship") {
                setPiece(new Boat(piece.getColor()));
            } else if (s == "Pegasus rider") {
                setPiece(new Pegasus(piece.getColor()));
            } else if (s == "Tyrant") {
                setPiece(new Tyrant(piece.getColor()));
            } else if (s == "Assassin") {
                int ran = (int) (Math.random() * 3);
                if (ran == 0){
                    setPiece(new Assassin(piece.getColor()));
                }else if (ran == 1){
                    setPiece(new Bladedancer(piece.getColor()));
                }else if (ran == 2){
                    setPiece(new Action_Man(piece.getColor()));
                }
            } else if (s == "Spider") {
                setPiece(new Spider(piece.getColor()));
            } else if (s == "Manticore") {
                setPiece(new Manticore(piece.getColor()));
            } else if (s == "Greatwyrm") {
                setPiece(new Greatwyrm(piece.getColor()));
            } else if (s == "Mage") {
                Piece spin = new Mage(piece.getColor());
                spin.setForwardDirection(getPiece().getForwardDirection() * -1);
                setPiece(spin);
            } else if (s == "Flag bearer") {
                Piece spin = new Flag(piece.getColor());
                spin.setForwardDirection(getPiece().getForwardDirection() * -1);
                setPiece(spin);
            } else if (s == "Spearman") {
                setPiece(new Spearman(piece.getColor()));
            } else if (s == "Immortal") {
                setPiece(new Immortal(piece.getColor()));
            } else if (s == "Falcon") {
                setPiece(new Falcon(piece.getColor()));
            }

            if (roy && getPiece() != null){
                getPiece().royal = true;
            }
        }
    }
}
