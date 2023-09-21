package Game;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
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

    static final Color wRed = new Color(144, 44, 44);

    static final Color wTan = new Color(119, 132, 114);

    Action spaceAction;
    Action helpAction;

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

    public static boolean ruth;

    public boolean decay;

    public int cooldown;

    public int gravity;

    public ChessGame(int size){
        Dimension boardSize = new Dimension(size, size);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        spaceAction = new SpaceAction();
        helpAction = new HelpAction();



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
        chessBoard.getInputMap().put(KeyStroke.getKeyStroke("H"), helpAction);
        chessBoard.getActionMap().put(spaceAction, spaceAction);
        chessBoard.getActionMap().put(helpAction, helpAction);
    }

    public void setupPieces() {
        gravity = (((int) (Math.random() * 3) -1));
        if ((int) (Math.random() * 7.0) != 1){
            gravity = 0;
        }
        cooldown = 0;
        decay = ((int) (Math.random() * 7.0) == 1);
        ruth = !((int) (Math.random() * 6.0) == 1);
        re = ((int) (Math.random() * 10.3) == 1);
        recheck = false;
        epic = false;
        bTrayal = ((int) (Math.random() * 6.5) == 1);
        bStab = ((int) (Math.random() * 5.5) == 1);
        touchRule = ((int) (Math.random() * 8.5) == 1);
        tLocked = false;
        myst = ((int) (Math.random() * 9) == 1);
        if (myst && !touchRule){
            touchRule = ((int) (Math.random() * 2) == 1);
        }
        chessBoard.wRadness = 3;
        chessBoard.bRadness = 3;
        atomic = (int) (Math.random() * 25);
        if (atomic < 21){
            atomic = 0;
        } else {
            atomic -= 20;//
        }
        if ((int) (Math.random() * 6.2) == 1){
            ranged = 1;
        } else {
            ranged = 0;
        }

        AudioPlayer.play("src/resources/audio/startgame.wav");
        if (Math.random() * 7 == 1) {
            epic = true;
        }else {
            epic = false;
        }
        Setup.refresh(chessBoard, epic);
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
                if (ranged == 0  && !(start.getPiece() != null && (start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage) && move.end.getPiece() != null)){
                    obliterate(start.getLocationOnBoard(), ks, ps);
                }

            } else if ((chessBoard.getTile(location).getPiece() != null) && (chessBoard.getTile(location).getPiece().bomb) ) {
                nuke(location, false, true);
                if (ranged == 0  && !(start.getPiece() != null && (start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage) && move.end.getPiece() != null)){
                    obliterate(start.getLocationOnBoard(), false, true);
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

            if (((ranged == 0 && !(start.getPiece() instanceof Mage || start.getPiece() instanceof Archmage)) || (move.end.getPiece() == null))){
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

            if (gravity == 1){
                if (move.end.getPiece() != null && Board.getXFromLocation(move.end.getLocationOnBoard()) < 7 && chessBoard.getTile(move.end.getLocationOnBoard() + 1).getPiece() == null){
                    chessBoard.getTile(move.end.getLocationOnBoard() + 1).setPiece(move.end.getPiece());
                    move.end.setPiece(null);
                }
            }else if (gravity == -1){
                if (move.end.getPiece() != null && Board.getXFromLocation(move.end.getLocationOnBoard()) > 0 && chessBoard.getTile(move.end.getLocationOnBoard() - 1).getPiece() == null){
                    chessBoard.getTile(move.end.getLocationOnBoard() - 1).setPiece(move.end.getPiece());
                    move.end.setPiece(null);
                }
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
                } else if (checked instanceof Mage){
                    ((Mage) checked).mana += 1;
                    chessBoard.getTile(check).setPiece(chessBoard.getTile(check).getPiece());
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

            if (decay){
                cooldown += 1;
                if (cooldown == 5){
                    cooldown = 0;
                    Tile[] wh = chessBoard.getOccupiedTilesOfColor(1);
                    Tile sel = wh[((int) (Math.random() * wh.length))];
                    if (sel.getPiece().royal){
                        sel = wh[((int) (Math.random() * wh.length))];
                    }
                    sel.setPiece(null);
                    Tile[] bl = chessBoard.getOccupiedTilesOfColor(0);
                    sel = bl[((int) (Math.random() * bl.length))];
                    if (sel.getPiece().royal){
                        sel = bl[((int) (Math.random() * bl.length))];
                    }
                    sel.setPiece(null);
                }
            }

            Tile[] enemyTiles = chessBoard.getOccupiedTilesOfColor(turn);
            int wNumPieces = 0;
            int bNumPieces = 0;
            boolean wKing = false;
            boolean bKing = false;
            for (Tile i : chessBoard.getOccupiedTilesOfColor(0)){
                bNumPieces ++;
                if (i.getPiece().royal){
                    bKing = true;
                }
            }
            if (bNumPieces <= 1){
                bKing = false;
            }
            for (Tile i : chessBoard.getOccupiedTilesOfColor(1)){
                wNumPieces ++;
                if (i.getPiece().royal){
                    wKing = true;
                }
            }
            if (wNumPieces <= 1){
                wKing = false;
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
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        if (tile.getPiece() == null){
            setTitle("Chesscapades");
            setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
        }else{
            String newname = "";
            if (myst){
                newname = "Unknown Piece";
                if (tile.getPiece().royal){
                    newname += " [Royal]";
                }
                if (tile.getPiece().color == 1){
                    setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wUnknown.png"));
                }else{
                    setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/bUnknown.png"));
                }
            }else{
                newname = tile.getPiece().name;
                setIconImage(tile.getPiece().getImageIcon().getImage());
                if (tile.getPiece() instanceof Mage){
                    newname += " (mana: " + (((Mage) tile.getPiece()).mana - 1) + ")";
                }
                if (tile.getPiece().royal){
                    newname += " [Royal]";
                }
                if (tile.getPiece().wall){
                    newname += " [Protected]";
                }
            }
            setTitle(newname);
        }







        if (!tLocked){
            for (Tile rTile : tiles) {
                rTile.setBackground(rTile.getColor());
            }
            if (selectedTile != null && (!selectedTile.isLegalMove(tile.getLocationOnBoard(), chessBoard, false))){
                selectedTile = null;
            }
        }
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
                            }else if (legalTile.getPiece().wall){
                                legalTile.setBackground(allyTan);
                            }else {
                                legalTile.setBackground(allyTan);
                            }

                        }else {
                            if (legalTile.getPiece().royal){
                                legalTile.setBackground(rRed);
                            }else if (legalTile.getPiece().wall){
                                legalTile.setBackground(allyRed);
                            }else{
                                legalTile.setBackground(allyRed);
                            }
                        }
                    }else if ((legalTile.getPiece() != null) && (legalTile.getPiece().getColor() != turn)){
                        if (legalTile.getPiece().royal) {
                            if (legalTile.getColor() == Tile.tan) {
                                legalTile.setBackground(pTan);
                            } else {
                                legalTile.setBackground(pRed);
                            }
                        }else if (legalTile.getPiece().wall) {
                            if (legalTile.getColor() == Tile.tan) {
                                legalTile.setBackground(wTan);
                            } else {
                                legalTile.setBackground(wRed);
                            }
                        }
                    }

                }
                return;
            }
        }
        playMove(new moveInfo(selectedTile, tile, chessBoard));
    }
    public void obliterate(int square, boolean kingSlayer, boolean pawnSlayer){
        if ( (chessBoard.getTile(square) != null) && (chessBoard.getTile(square).getPiece() != null) && (kingSlayer || !(chessBoard.getTile(square).getPiece().royal)) && (pawnSlayer || !(chessBoard.getTile(square).getPiece() instanceof Pawn))) {
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