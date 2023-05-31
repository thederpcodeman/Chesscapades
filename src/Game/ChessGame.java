package Game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    Stockfish stockfish = new Stockfish();
    Board chessBoard;
    Tile selectedTile;
    public static int turn;
    static final Color highlightedColor = new Color(00, 100, 200);
    static final Color selfColor = new Color(55, 160, 80);
    static final Color dangerColor = new Color(179, 0, 27);

    static final Color highlightedColor2 = new Color(48, 134, 215);
    static final Color allyTan = new Color(215, 255, 222);
    static final Color allyRed = new Color(150, 122, 97);

    static final Color rTan = new Color(255, 248, 182);

    static final Color rRed = new Color(235, 142, 57);

    static final Color pTan = new Color(255, 228, 252);

    static final Color pRed = new Color(215, 102, 117);

    Action spaceAction;

    ArrayList<String> fens;
    public int atomic;
    public int ranged;

    public boolean touchRule;

    public boolean tLocked;
    public static boolean myst;

    public static boolean re;
    public static boolean recheck;

    public boolean bStab;

    public boolean bTrayal;

    public static boolean epic;

    public static boolean chaos;

    public static boolean ruth;


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
        ruth = !((int) (Math.random() * 6.0) == 1);
        re = ((int) (Math.random() * 10.3) == 1);
        recheck = false;
        chaos = ((int) (Math.random() * 20.0) == 1);
        epic = false;
        bTrayal = ((int) (Math.random() * 6.5) == 1);
        bStab = ((int) (Math.random() * 5.5) == 1);
        touchRule = ((int) (Math.random() * 8) == 1);
        tLocked = false;
        myst = ((int) (Math.random() * 8.5) == 1);
        if (myst && !touchRule){
            touchRule = ((int) (Math.random() * 2) == 1);
        }
        chessBoard.wRadness = 3;
        chessBoard.bRadness = 3;
        atomic = (int) (Math.random() * 20);
        if (atomic < 16){
            atomic = 0;
        } else {
            atomic -= 15;//
        }
        if ((Math.random() * 5) == 1){
            ranged = 1;
        } else {
            ranged = 0;
        }

        AudioPlayer.play("src/resources/audio/startgame.wav");
        int rand = (int) (Math.random() * 8);
        if (rand == 1 || rand == 0){
             //setup black pieces
             chessBoard.getTile(0).setPiece(Cloning.Common(0));
             chessBoard.getTile(7).setPiece(Cloning.Common(0));
             chessBoard.getTile(1).setPiece(Cloning.Common(0));
             chessBoard.getTile(6).setPiece(Cloning.Common(0));
             chessBoard.getTile(2).setPiece(Cloning.Common(0));
             chessBoard.getTile(5).setPiece(Cloning.Common(0));
             chessBoard.getTile(3).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(4).setPiece(Cloning.Royal(0));
             for (int i = 8; i < 16; i++) {
                 chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
             }
             //setup white pieces
             chessBoard.getTile(56).setPiece(Cloning.Common(1));
             chessBoard.getTile(63).setPiece(Cloning.Common(1));
             chessBoard.getTile(57).setPiece(Cloning.Common(1));
             chessBoard.getTile(62).setPiece(Cloning.Common(1));
             chessBoard.getTile(58).setPiece(Cloning.Common(1));
             chessBoard.getTile(61).setPiece(Cloning.Common(1));
             chessBoard.getTile(59).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(60).setPiece(Cloning.Royal(1));
             for (int i = 48; i < 56; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.rPawn(1));
             }
        }else if (rand == 2){

            //power chess

             //setup black pieces
             chessBoard.getTile(0).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(7).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(1).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(6).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(2).setPiece(Cloning.Royal(0));
             chessBoard.getTile(5).setPiece(Cloning.Royal(0));
             chessBoard.getTile(3).setPiece(Cloning.Advanced(0));
             chessBoard.getTile(4).setPiece(Cloning.Advanced(0));
             for (int i = 8; i < 16; i++) {
                 chessBoard.getTile(i).setPiece(Cloning.Common(0));
             }
             for (int i = 16; i < 24; i++) {
                 chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
             }
             //setup white pieces
             chessBoard.getTile(56).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(63).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(57).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(62).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(58).setPiece(Cloning.Royal(1));
             chessBoard.getTile(61).setPiece(Cloning.Royal(1));
             chessBoard.getTile(59).setPiece(Cloning.Advanced(1));
             chessBoard.getTile(60).setPiece(Cloning.Advanced(1));
             for (int i = 48; i < 56; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.Common(1));
             }
             for (int i = 40; i < 48; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.rPawn(1));
             }
        }else if (rand == 3) {

            //revolt chess

             //setup black pieces
             chessBoard.getTile(0).setPiece(new Rook(0));
             chessBoard.getTile(7).setPiece(new Rook(0));
             chessBoard.getTile(1).setPiece(new Knight(0));
             chessBoard.getTile(6).setPiece(new Knight(0));
             chessBoard.getTile(2).setPiece(new Bishop(0));
             chessBoard.getTile(5).setPiece(new Bishop(0));
             chessBoard.getTile(3).setPiece(new Queen(0));
             chessBoard.getTile(4).setPiece(new King(0));
             for (int i = 32; i < 40; i++) {
                 chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
             }
             //setup white pieces
             chessBoard.getTile(56).setPiece(new Rook(1));
             chessBoard.getTile(63).setPiece(new Rook(1));
             chessBoard.getTile(57).setPiece(new Knight(1));
             chessBoard.getTile(62).setPiece(new Knight(1));
             chessBoard.getTile(58).setPiece(new Bishop(1));
             chessBoard.getTile(61).setPiece(new Bishop(1));
             chessBoard.getTile(59).setPiece(new Queen(1));
             chessBoard.getTile(60).setPiece(new King(1));
             for (int i = 24; i < 32; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.rPawn(1));
             }
        }else if (rand == 4){

            //fear chess

             for (int i = 0; i < 16; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.Fear(0));
             }
             for (int i = 48; i < 64; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(Cloning.Fear(1));
             }
             int i = (int) (Math.random() * 8);
             chessBoard.getTile(i).setPiece(Cloning.Royal(0));
             i = (int) (Math.random() * 8);
             chessBoard.getTile(i + 56).setPiece(Cloning.Royal(1));
        }else if (rand == 5){

            //grand chess

            //setup black pieces
            chessBoard.getTile(0).setPiece(Cloning.Royal(0));
            chessBoard.getTile(7).setPiece(Cloning.Royal(0));
            chessBoard.getTile(1).setPiece(Cloning.rPawn(0));
            chessBoard.getTile(6).setPiece(Cloning.rPawn(0));
            chessBoard.getTile(2).setPiece(Cloning.Advanced(0));
            chessBoard.getTile(5).setPiece(Cloning.Advanced(0));
            chessBoard.getTile(3).setPiece(Cloning.rPawn(0));
            chessBoard.getTile(4).setPiece(Cloning.rPawn(0));
            for (int i = 8; i < 24; i++) {
                chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
            }
            //setup white pieces
            chessBoard.getTile(56).setPiece(Cloning.Royal(1));
            chessBoard.getTile(63).setPiece(Cloning.Royal(1));
            chessBoard.getTile(57).setPiece(Cloning.rPawn(1));
            chessBoard.getTile(62).setPiece(Cloning.rPawn(1));
            chessBoard.getTile(58).setPiece(Cloning.Advanced(1));
            chessBoard.getTile(61).setPiece(Cloning.Advanced(1));
            chessBoard.getTile(59).setPiece(Cloning.rPawn(1));
            chessBoard.getTile(60).setPiece(Cloning.rPawn(1));
            for (int i = 40; i < 56; i++) {
                Tile tile = (Tile) chessBoard.getComponent(i);
                tile.setPiece(Cloning.rPawn(1));
            }
        }else{
             // 1/3 960
             //setup black pieces
             chessBoard.getTile(0).setPiece(new Rook(0));
             chessBoard.getTile(7).setPiece(new Rook(0));
             chessBoard.getTile(1).setPiece(new Knight(0));
             chessBoard.getTile(6).setPiece(new Knight(0));
             chessBoard.getTile(2).setPiece(new Bishop(0));
             chessBoard.getTile(5).setPiece(new Bishop(0));
             chessBoard.getTile(3).setPiece(new Queen(0));
             chessBoard.getTile(4).setPiece(new King(0));
             for (int i = 8; i < 16; i++) {
                 chessBoard.getTile(i).setPiece(new Pawn(0));
             }
             //setup white pieces
             chessBoard.getTile(56).setPiece(new Rook(1));
             chessBoard.getTile(63).setPiece(new Rook(1));
             chessBoard.getTile(57).setPiece(new Knight(1));
             chessBoard.getTile(62).setPiece(new Knight(1));
             chessBoard.getTile(58).setPiece(new Bishop(1));
             chessBoard.getTile(61).setPiece(new Bishop(1));
             chessBoard.getTile(59).setPiece(new Queen(1));
             chessBoard.getTile(60).setPiece(new King(1));
             for (int i = 48; i < 56; i++) {
                 Tile tile = (Tile) chessBoard.getComponent(i);
                 tile.setPiece(new Pawn(1));
             }
             //Modify pieces
             ArrayList<Integer> loop = new ArrayList<Integer>();
             loop.add(0);
             loop.add(1);
             loop.add(2);
             loop.add(5);
             loop.add(6);
             loop.add(7);
             for (Integer i : loop){
                 if ((int) (Math.random() * 3) == 1){
                     chessBoard.getTile(i).setPiece(Cloning.Common(0));
                 }
             }
             loop.clear();
             loop.add(56);
             loop.add(63);
             loop.add(57);
             loop.add(62);
             loop.add(58);
             loop.add(61);
             for (Integer i : loop){
                 if ((int) (Math.random() * 3) == 1){
                     chessBoard.getTile(i).setPiece(Cloning.Common(1));
                 }
             }
             if ((int) (Math.random() * 3) == 1){
                 chessBoard.getTile(3).setPiece(Cloning.Advanced(0));
             }
             if ((int) (Math.random() * 3) == 1){
                 chessBoard.getTile(59).setPiece(Cloning.Advanced(1));
             }
             if ((int) (Math.random() * 3) == 1){
                 chessBoard.getTile(4).setPiece(Cloning.Royal(0));
             }
             if ((int) (Math.random() * 3) == 1){
                 chessBoard.getTile(60).setPiece(Cloning.Royal(1));
             }
             for (int i = 8; i < 16; i++) {
                 if ((int) (Math.random() * 3) == 1){
                     chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
                 }
             }
             for (int i = 48; i < 56; i++) {
                 if ((int) (Math.random() * 3) == 1){
                     chessBoard.getTile(i).setPiece(Cloning.rPawn(1));
                 }
             }
         }
        // modifiers
        if (Math.random() * 7 == 1){
            for (Tile i : chessBoard.getOccupiedTiles()){
                if (i.getPiece() != null && !(i.getPiece() instanceof Pawn)){
                    i.getPiece().royal = true;
                }
            }
        }
        if (Math.random() * 7 == 1){
            epic = true;
            for (Tile i : chessBoard.getOccupiedTiles()){
                if (i.getPiece() != null && (i.getPiece() instanceof Pawn)){
                    i.getPiece().wall = true;
                }
            }
        }else{
            epic = false;
        }
        turn = 1;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    public void playMove(moveInfo move){
        int location = move.end.getLocationOnBoard();
        Tile start = move.start;
        boolean resp = (move.end.getPiece() != null && re);
        if (start != null && (start.isPlayableMove(location, chessBoard, true) != 0)) {
            //process move
            tLocked = false;
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

            if (bStab && start.getPiece() != null){
                if (location >= 8){
                    Tile t = chessBoard.getTile(location - 8);
                    if (t.getPiece() != null && t.getPiece().getColor() != start.getPiece().getColor() && t.getPiece().getForwardDirection() == -1){
                        obliterate(location - 8, false, true);
                    }
                }if (location < 56){
                    Tile t = chessBoard.getTile(location + 8);
                    if (t.getPiece() != null && t.getPiece().getColor() != start.getPiece().getColor() && t.getPiece().getForwardDirection() == 1){
                        obliterate(location + 8, false, true);
                    }
                }
            }

            if ((ranged == 0) || (move.end.getPiece() == null)){
                move.end.setPiece(start.getPiece());
                start.setPiece(null);
                if (bTrayal && move.end.getPiece() != null && move.end.getPiece().royal == false && (int) (Math.random() * 5) == 1){
                    move.end.getPiece().color = (move.end.getPiece().color + 1) % 2;
                    move.end.getPiece().setForwardDirection(move.end.getPiece().getForwardDirection() * -1);
                    move.end.setPiece(move.end.getPiece());
                }
            } else {
                move.end.setPiece(null);
            }



            start.setBackground(start.getColor());
            if (!tLocked){
                selectedTile = null;
                for (Tile rTile : chessBoard.getTiles()) {
                    rTile.setBackground(rTile.getColor());
                }
            }

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

            if (resp && !recheck){
                recheck = true;
                playGoodishMove();
                turn = 1 - turn;
                recheck = false;

            }

            Tile[] enemyTiles = chessBoard.getOccupiedTilesOfColor(turn);
            boolean wKing = false;
            boolean bKing = false;
            for (int square = 0; square < 64; square ++){
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
        if (!tLocked){
            for (Tile rTile : tiles) {
                rTile.setBackground(rTile.getColor());
            }
        }
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        Piece piece = tile.getPiece();
        if (piece != null && !(selectedTile != null && selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard, false))) {
            if (piece.getColor() == turn && !tLocked) {
                if (!tLocked){
                    selectedTile = tile;
                }
                tile.setBackground(selfColor);
                Tile[] legalMoves = chessBoard.getTiles();
                for (Tile legalTile : legalMoves) {
                    if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 1){
                        if (legalTile.getColor() == Tile.tan) {
                            legalTile.setBackground(highlightedColor2);
                        } else {
                            legalTile.setBackground(highlightedColor);
                        }
                        if (touchRule) {
                            tLocked = true;
                        }

                    }else if (tile.isPlayableMove(legalTile.getLocationOnBoard(), chessBoard, false) == 2) {
                        legalTile.setBackground(dangerColor);
                    }else if (tile == legalTile){
                        legalTile.setBackground(selfColor);
                    }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() == turn)) {
                        if (legalTile.getColor() == Tile.tan){
                            if (legalTile.getPiece().royal){
                                legalTile.setBackground(rTan);
                            }else{
                                legalTile.setBackground(allyTan);
                            }

                        }else {
                            if (legalTile.getPiece().royal){
                                legalTile.setBackground(rRed);
                            }else{
                                legalTile.setBackground(allyRed);
                            }
                        }
                    }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() != turn) && legalTile.getPiece().royal) {
                        if (legalTile.getColor() == Tile.tan){
                            legalTile.setBackground(pTan);
                        }else {
                            legalTile.setBackground(pRed);
                        }
                    }

                }
                return;
            }
        }
        boolean ch = (chaos && selectedTile != null && tile != null && selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard, false));
        playMove(new moveInfo(selectedTile, tile, chessBoard));
        if (ch){
            playGoodishMove();
            playGoodishMove();
        }
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