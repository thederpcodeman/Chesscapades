import Game.ChessGame;

import javax.swing.*;

public class Main {
    public static void main(String args[]) {
        JFrame frame = new ChessGame(1200);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setTitle("Chesscapades");
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
