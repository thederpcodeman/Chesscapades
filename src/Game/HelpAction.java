package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class HelpAction extends AbstractAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        JFrame frame = new JFrame("Chesscapades Help Menu");
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocation(430, 100);

        JPanel panel = new JPanel();

        frame.add(panel);

        JLabel lbl = new JLabel("");
        lbl.setVisible(true);
        panel.add(lbl);

        JLabel lbl2 = new JLabel("");
        lbl2.setVisible(true);
        panel.add(lbl2);

        JLabel lbl3 = new JLabel("");
        lbl3.setVisible(true);
        panel.add(lbl3);

        JLabel lbl4 = new JLabel("");
        lbl4.setVisible(true);
        panel.add(lbl4);

        JLabel lbl5 = new JLabel("");
        lbl5.setVisible(true);
        panel.add(lbl5);

        String[] choices = {"Basic Rules", "Randomized Rules", "Piece Specific Rules", "Setup Variants"};

        final JComboBox<String> cb = new JComboBox<String>(choices);
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("src/resources/wKnight.png"));
        cb.setVisible(true);
        panel.add(cb);

        JButton btn = new JButton("OK");
        btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (Objects.equals(cb.getSelectedItem().toString(), "Basic Rules")) {
                    lbl.setText("Each player takes turns moving 1 piece, to another square,");
                    lbl2.setText("Royal pieces are highlighted in gold/purple.");
                    lbl3.setText("Capture all the opponents Royal pieces to win.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Basic Rules");
                    cb.addItem("Randomized Rules");
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("Setup Variants");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Randomized Rules")) {
                    lbl.setText("Some additional rules have a random chance to be in play,");
                    lbl2.setText("These are hidden,");
                    lbl3.setText("you must find out which are in play during the game.");
                    lbl4.setText("Good luck!");
                    lbl5.setText("(here is the list of rules)");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Traitor Chess");
                    cb.addItem("Mystery Chess");
                    cb.addItem("Epic Chess");
                    cb.addItem("Riffle Chess");
                    cb.addItem("Atomic Chess");
                    cb.addItem("Gravity Chess");
                    cb.addItem("Secret bomber Chess");
                    cb.addItem("Formal Chess");
                    cb.addItem("Backstab Chess");
                    cb.addItem("Friendly fire Chess");
                    cb.addItem("Chaos Chess");
                    cb.addItem("Fast & Furious Chess");
                    cb.addItem("Total War Chess");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Piece Specific Rules")) {
                    lbl.setText("Each Piece has their own movement. ");
                    lbl2.setText("Some have additional rules as well.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Pawn");
                    cb.addItem("Knight");
                    cb.addItem("Rook");
                    cb.addItem("Bishop");
                    cb.addItem("Queen");
                    cb.addItem("King");
                    cb.addItem("Show More");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Setup Variants")) {
                    lbl.setText("The setup rules ");
                    lbl2.setText("are not ");
                    lbl3.setText("the same ");
                    lbl4.setText("each time. ");
                    lbl5.setText("Here are some examples.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("2/3 Chess");
                    cb.addItem("Chess 960");
                    cb.addItem("Power Chess");
                    cb.addItem("Fear Chess");
                    cb.addItem("Grand Chess");
                    cb.addItem("Revolt Chess");

                } else if (Objects.equals(cb.getSelectedItem().toString(), "OK")) {
                    lbl.setText("");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("Basic Rules");
                    cb.addItem("Randomized Rules");
                    cb.addItem("Piece Specific Rules");
                    cb.addItem("Setup Variants");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Show More")) {
                    lbl.setText("The Full List of pieces");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("---");
                    cb.addItem("Pawn");
                    cb.addItem("Soldier");
                    cb.addItem("Pikeman");
                    cb.addItem("Prince");
                    cb.addItem("Wall");
                    cb.addItem("Checker");
                    cb.addItem("---");
                    cb.addItem("King");
                    cb.addItem("General");
                    cb.addItem("Empress");
                    cb.addItem("Tyrant");
                    cb.addItem("---");
                    cb.addItem("Knight");
                    cb.addItem("Rook");
                    cb.addItem("Bishop");
                    cb.addItem("Camel");
                    cb.addItem("Elephant");
                    cb.addItem("Frog");
                    cb.addItem("Ship");
                    cb.addItem("Bull");
                    cb.addItem("---");
                    cb.addItem("Archer");
                    cb.addItem("Assassin");
                    cb.addItem("Mage");
                    cb.addItem("---");
                    cb.addItem("Archbishop");
                    cb.addItem("Chancellor");
                    cb.addItem("Queen");
                    cb.addItem("Spider");
                    cb.addItem("Gryphon");
                    cb.addItem("Manticore");
                    cb.addItem("Buffalo");
                    cb.addItem("Pegasus");
                    cb.addItem("---");
                    cb.addItem("Amazon");
                    cb.addItem("Lion");
                    cb.addItem("Greatwyrm");
                    cb.addItem("Quetzalcoatl");

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Atomic Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Rifle Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Backstab Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Mystery Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Traitor Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Gravity Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Fast and Furious chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Decay Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Friendly Fire Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Chaos Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Formal Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Total War Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Epic Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Secret Bomber Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pawn")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pikeman")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Soldier")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Prince")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Wall")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Checker")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "King")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "General")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Empress")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Tyrant")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Knight")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Rook")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Bishop")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Camel")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Elephant")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Frog")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                }
            }
        });
        panel.add(btn);


    }
}