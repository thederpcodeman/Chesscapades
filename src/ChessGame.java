import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessGame extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;

    Tile selectedTile;
    int turn;

    public ChessGame(){
        Dimension boardSize = new Dimension(600, 600);

        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0,0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            Tile tile = new Tile(i, new BorderLayout());
            chessBoard.add(tile);
        }
        
        setupPieces();
        turn = 1;
        //Board board = new Board();

    }
    
    public void setupPieces() {
        //setup black pieces
        Tile tile1 = (Tile) chessBoard.getComponent(0);
        Tile tile2 = (Tile) chessBoard.getComponent(1);
        Tile tile3 = (Tile) chessBoard.getComponent(2);
        Tile tile4 = (Tile) chessBoard.getComponent(3);
        Tile tile5 = (Tile) chessBoard.getComponent(4);
        Tile tile6 = (Tile) chessBoard.getComponent(5);
        Tile tile7 = (Tile) chessBoard.getComponent(6);
        Tile tile8 = (Tile) chessBoard.getComponent(7);

        tile1.setPiece(new Rook(0));
        tile8.setPiece(new Rook(0));
        tile2.setPiece(new Knight(0));
        tile7.setPiece(new Knight(0));
        tile3.setPiece(new Bishop(0));
        tile6.setPiece(new Bishop(0));
        tile4.setPiece(new Queen(0));
        tile5.setPiece(new King(0));
        for (int i = 8; i < 16; i++) 
        {
            Tile tile = (Tile) chessBoard.getComponent(i);
            tile.setPiece(new Pawn(0));
        }

        //setup white pieces
        Tile tile1w = (Tile) chessBoard.getComponent(56);
        Tile tile2w = (Tile) chessBoard.getComponent(57);
        Tile tile3w = (Tile) chessBoard.getComponent(58);
        Tile tile4w = (Tile) chessBoard.getComponent(59);
        Tile tile5w = (Tile) chessBoard.getComponent(60);
        Tile tile6w = (Tile) chessBoard.getComponent(61);
        Tile tile7w = (Tile) chessBoard.getComponent(62);
        Tile tile8w = (Tile) chessBoard.getComponent(63);

        tile1w.setPiece(new Rook(1));
        tile8w.setPiece(new Rook(1));
        tile2w.setPiece(new Knight(1));
        tile7w.setPiece(new Knight(1));
        tile3w.setPiece(new Bishop(1));
        tile6w.setPiece(new Bishop(1));
        tile4w.setPiece(new Queen(1));
        tile5w.setPiece(new King(1));
        for (int i = 48; i < 56; i++)
        {
            Tile tile = (Tile) chessBoard.getComponent(i);
            tile.setPiece(new Pawn(1));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Tile tile = (Tile) chessBoard.getComponentAt(e.getX(), e.getY());
        Piece piece = tile.getPiece();
        if(piece != null)
        {
            if (piece.getColor() == turn) {
                if(selectedTile != null)
                {
                    selectedTile.setBackground(selectedTile.getColor());
                }
                selectedTile = tile;
                tile.setBackground(Color.green);
                return;
            }
        }
        if(selectedTile != null && selectedTile.getPiece().isMovable())
        {
            tile.setPiece(selectedTile.getPiece());
            selectedTile.setPiece(null);
            selectedTile.setBackground(selectedTile.getColor());
            selectedTile = null;
            turn = 1 - turn;
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


}