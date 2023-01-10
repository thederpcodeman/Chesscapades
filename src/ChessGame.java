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
    Board board = new Board();
    Color tan = new Color(255, 248, 232);
    Color red = new Color(215, 122, 97);

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
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);
            boolean isBlack;
            isBlack = i % 2 == 1;
            if (!((i / 8) % 2 == 0)) {
                isBlack = !isBlack;
            }
            if (!isBlack) {
                square.setBackground(tan);
            } else {
                square.setBackground(red);
            }
        }

        Pawn pawn = new Pawn(0);
        Rook rook = new Rook(0);
        Queen queen = new Queen(0);
        Knight knight = new Knight(0);
        King king = new King(0);
        Bishop bishop = new Bishop(0);

        JLabel placer = new JLabel(rook.pieceColor());
        JPanel panel = (JPanel)chessBoard.getComponent(0);
        panel.add(placer);



        for(int x = 8; x < 16; x++) {
            placer = new JLabel(pawn.pieceColor());
            panel = (JPanel) chessBoard.getComponent(x);
            panel.add(placer);
        }

        //Board board = new Board();
        for(int i = 0; i < 64; i++) {
            Piece piece = board.getPieceFromPosition(i);
            if(piece != null)
            {
                //render piece
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

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
