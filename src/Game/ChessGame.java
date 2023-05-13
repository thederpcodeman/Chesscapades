package Game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    Stockfish stockfish = new Stockfish();
    Board chessBoard;
    Tile selectedTile;
    int turn;
    static final Color highlightedColor = new Color(00, 100, 200);
    static final Color selfColor = new Color(55, 160, 80);
    static final Color dangerColor = new Color(179, 0, 27);

    static final Color highlightedColor2 = new Color(48, 134, 215);
    static final Color allyTan = new Color(215, 255, 222);
    static final Color allyRed = new Color(150, 122, 97);

    Action spaceAction;

    ArrayList<String> fens;
    public int atomic;
    public int ranged;

    public ChessGame(int size){
        Dimension boardSize = new Dimension(size, size);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        spaceAction = new SpaceAction();



        chessBoard = new Board();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i, new BorderLayout(), size / 8);
            chessBoard.add(tile);
        }

        setupPieces();

        fens = new ArrayList<String>();
        fens.add(chessBoard.computeFen(turn));
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), spaceAction);
        chessBoard.getActionMap().put(spaceAction, spaceAction);
        stockfish.startEngine();
    }

    public void setupPieces() {
        atomic = (int) (Math.random() * 20);
        if (atomic < 16){
            atomic = 0;
        } else {
            atomic -= 15;
        }
        if ((Math.random() * 5) == 1){
            ranged = 1;
        } else {
            ranged = 0;
        }

        AudioPlayer.play("src/resources/audio/startgame.wav");

        //setup black pieces
        chessBoard.getTile(0).setPiece(new Rook(0));
        chessBoard.getTile(7).setPiece(new Rook(0));
        chessBoard.getTile(0).setCastleable(true);
        chessBoard.getTile(7).setCastleable(true);
        chessBoard.getTile(1).setPiece(new Knight(0));
        chessBoard.getTile(6).setPiece(new Knight(0));
        chessBoard.getTile(2).setPiece(new Bishop(0));
        chessBoard.getTile(5).setPiece(new Bishop(0));
        chessBoard.getTile(3).setPiece(new Queen(0));
        chessBoard.getTile(4).setPiece(new King(0));
        chessBoard.getTile(4).setCastleable(true);
        for (int i = 8; i < 16; i++) {
            chessBoard.getTile(i).setPiece(new Pawn(0));
        }
        //setup white pieces
        chessBoard.getTile(56).setPiece(new Rook(1));
        chessBoard.getTile(63).setPiece(new Rook(1));
        chessBoard.getTile(56).setCastleable(true);
        chessBoard.getTile(63).setCastleable(true);
        chessBoard.getTile(57).setPiece(new Knight(1));
        chessBoard.getTile(62).setPiece(new Knight(1));
        chessBoard.getTile(58).setPiece(new Bishop(1));
        chessBoard.getTile(61).setPiece(new Bishop(1));
        chessBoard.getTile(59).setPiece(new Queen(1));
        chessBoard.getTile(60).setPiece(new King(1));
        chessBoard.getTile(60).setCastleable(true);
        for (int i = 48; i < 56; i++) {
            Tile tile = (Tile) chessBoard.getComponent(i);
            tile.setPiece(new Pawn(1));
        }
        turn = 1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public void playMove(moveInfo move){
        int location = move.end.getLocationOnBoard();
        Tile start = move.start;
        if (start != null && (start.isPlayableMove(location, chessBoard, true) != 0)) {
            //process move

            if ((chessBoard.getTile(location).getPiece() != null) && (atomic > 0)){
                boolean ks = false;
                boolean ps = false;
                if ((atomic - 1) / 2 == 0){
                    ks = true;
                }
                if (atomic % 2 == 1){
                    ps = true;
                }
                nuke(location, ks, ps);
                if (ranged == 0){
                    obliterate(start.getLocationOnBoard(), ks, ps);
                }

            }

            if ((ranged == 0) || (move.end.getPiece() == null)){
                move.end.setPiece(start.getPiece());
                start.setPiece(null);
            } else {
                move.end.setPiece(null);
            }



            start.setBackground(start.getColor());
            selectedTile = null;

            AudioPlayer.play("src/resources/audio/move-self.wav");
            turn = 1 - turn;
            for (int check = 0; check < 64; check++){
                Piece checked = chessBoard.getTile(check).getPiece();
                if (checked instanceof Pawn){
                    Pawn p = (Pawn) checked;
                    if (p.moved2 > 0) {p.moved2 -=1;}
                }
            }


            //compute fen
            String fen = chessBoard.computeFen(turn);
            fens.add(fen);


            Tile[] enemyTiles = chessBoard.getOccupiedTilesOfColor(turn);
            boolean wKing = false;
            boolean bKing = false;
            for (int square = 0; square < 63; square ++){
                if ((chessBoard.getTile(square).getPiece() != null ) && (chessBoard.getTile(square).getPiece().royal)){
                    if (chessBoard.getTile(square).getPiece().getColor() == 1){
                        wKing = true;
                    } else{
                        bKing = true;
                    }
                }
            }
            if (!wKing){
                if (!bKing){
                    System.out.println("Here's the FEN for the final position!");
                    System.out.println(fens.get(fens.size() - 1));
                    int option;
                    String buttons[] = {"Replay", "Quit"};
                    option = JOptionPane.showOptionDialog(null, "A tie? Too bad. Play again?", "Draw", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                    if (option == 0) {
                        for (int i = 0; i < 64; i++) {
                            chessBoard.getTile(i).setPiece(null);
                        }
                        fens.clear();
                        setupPieces();
                    } else {
                        System.exit(0);
                    }
                } else {
                    System.out.println("Here's the FEN for the final position!");
                    System.out.println(fens.get(fens.size() - 1));
                    int option;
                    String buttons[] = {"Replay", "Quit"};
                    option = JOptionPane.showOptionDialog(null, "Black wins! Play again?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                    if (option == 0) {
                        for (int i = 0; i < 64; i++) {
                            chessBoard.getTile(i).setPiece(null);
                        }
                        fens.clear();
                        setupPieces();
                    } else {
                        System.exit(0);
                    }
                }
            } else if (!bKing){
                System.out.println("Here's the FEN for the final position!");
                System.out.println(fens.get(fens.size() - 1));
                int option;
                String buttons[] = {"Replay", "Quit"};
                option = JOptionPane.showOptionDialog(null, "White wins! Play again?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
                if (option == 0) {
                    for (int i = 0; i < 64; i++) {
                        chessBoard.getTile(i).setPiece(null);
                    }
                    fens.clear();
                    setupPieces();
                } else {
                    System.exit(0);
                }
            }

            //check for three move repetition
            int priorOccurrences = 0;
            for (String oldFen : fens) {
                if (fen.equals(oldFen)) {
                    priorOccurrences++;
                }
            }
            if (priorOccurrences >= 3) {
                stalemate();
            }

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile[] tiles = chessBoard.getTiles();
        for (Tile rTile : tiles) {
            rTile.setBackground(rTile.getColor());
        }
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        Piece piece = tile.getPiece();
        if (piece != null) {
            if (piece.getColor() == turn) {
                selectedTile = tile;
                tile.setBackground(selfColor);
                Tile[] legalMoves = chessBoard.getTiles();
                for (Tile legalTile : legalMoves) {
                    if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 1){
                        if (legalTile.getColor() == Tile.tan) {
                            legalTile.setBackground(highlightedColor2);
                        } else {
                            legalTile.setBackground(highlightedColor);
                        }
                    }else if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 2) {
                        legalTile.setBackground(dangerColor);
                    }else if (tile == legalTile){
                        legalTile.setBackground(selfColor);
                    }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() == turn)) {
                        if (legalTile.getColor() == Tile.tan){
                            legalTile.setBackground(allyTan);
                        }else {
                            legalTile.setBackground(allyRed);
                        }
                    }

                }
                return;
            }
        }
        playMove(new moveInfo(selectedTile, tile, chessBoard));
    }
    public void obliterate(int square, boolean kingSlayer, boolean pawnSlayer){
        if ( (chessBoard.getTile(square) != null) && (chessBoard.getTile(square).getPiece() != null) && (kingSlayer || !(chessBoard.getTile(square).getPiece() instanceof King)) && (pawnSlayer || !(chessBoard.getTile(square).getPiece() instanceof Pawn))) {
            chessBoard.getTile(square).setPiece(null);
        }
    }
    public void nuke(int square, boolean kingSlayer, boolean pawnSlayer) {
        int y = Board.getYFromLocation(square);
        int x = Board.getXFromLocation(square);
        int Yoff = -1;
        while (Yoff <= 1){
            int Xoff = -1;
            while (Xoff <= 1){
                if ((y + Yoff >= 0) && (y + Yoff < 8) && (x + Xoff >= 0) && (x + Xoff < 8)){
                    obliterate(Board.getLocationFromCords(x + Xoff,y + Yoff ), kingSlayer, pawnSlayer);
                }
                Xoff ++;
            }
            Yoff ++;
        }
    }

    void checkmate() {
        AudioPlayer.play("src/resources/audio/win.wav");
        System.out.println("Checkmate! Here's the FEN for the final position!");
        System.out.println(fens.get(fens.size() - 1));
        int option;
        String buttons[] = {"Replay", "Quit"};
        if (turn == 1) {
            option = JOptionPane.showOptionDialog(null, "Black wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        } else {
            option = JOptionPane.showOptionDialog(null, "White wins! Play again or quit?", "Checkmate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        }
        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            fens.clear();
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    void stalemate() {
        AudioPlayer.play("src/resources/audio/stalemate.wav");
        System.out.println("Stalemate! Here's the FEN for the final position!");
        System.out.println(fens.get(fens.size() - 1));
        int option;
        String buttons[] = {"Replay", "Quit"};
        option = JOptionPane.showOptionDialog(null, "Stalemate! Play again or quit?", "Stalemate", JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, buttons, "default");
        if (option == 0) {
            for (int i = 0; i < 64; i++) {
                chessBoard.getTile(i).setPiece(null);
            }
            fens.clear();
            setupPieces();
        } else {
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
    private void playGoodishMove(){
        ArrayList<moveInfo> choices = new ArrayList<moveInfo>();
        for (Tile myDude : chessBoard.getOccupiedTilesOfColor(turn)){
            for (Tile place : myDude.getLegalMoves(chessBoard)){
                choices.add(new moveInfo(myDude, place, chessBoard));
            }
        }
        if (choices.size() == 0){
            System.exit(0);
        }
        double moveMin = -999999;
        int selected = 0;
        for (int mov = 0; mov < choices.size(); mov++){
            double sc = choices.get(mov).score();
            if (sc > moveMin){
                moveMin = sc;
                selected = mov;
            }
        }
        playMove(choices.get(selected));


    }

    public class SpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            playGoodishMove();
        }
    }
}