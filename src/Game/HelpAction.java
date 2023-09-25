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
                    cb.addItem("High Mage");
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
                    lbl.setText("When a piece is captured");
                    lbl2.setText("it explodes. Taking out ");
                    lbl3.setText("everything in a 3x3 area. ");
                    lbl4.setText("It is random whether it works on");
                    lbl5.setText("Pawns, and Royal pieces (independently)");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Rifle Chess")) {
                    lbl.setText("When capturing a Piece");
                    lbl2.setText("Don't move.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Backstab Chess")) {
                    lbl.setText("When moving behind an ");
                    lbl2.setText("enemy piece, it dies. ");
                    lbl3.setText("Doesn't effect Royals.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Mystery Chess")) {
                    lbl.setText("You cannot see what pieces are, ");
                    lbl2.setText("Only their team. ");
                    lbl3.setText("(the cpu can) ");
                    lbl4.setText("(50% to apply formal chess)");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Formal Chess");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Traitor Chess")) {
                    lbl.setText("When a piece is moved ");
                    lbl2.setText("there is a 20% chance for ");
                    lbl3.setText("it to switch to the other side. ");
                    lbl4.setText("(Doesn't effect Royals)");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Gravity Chess")) {
                    lbl.setText("When a piece is moved ");
                    lbl2.setText("it slides in the direction ");
                    lbl3.setText("of gravity ");
                    lbl4.setText("unless something is in the way.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Fast and Furious chess")) {
                    lbl.setText("When a piece is moved ");
                    lbl2.setText("it slides in the direction ");
                    lbl3.setText("of gravity ");
                    lbl4.setText("unless something is in the way.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Decay Chess")) {
                    lbl.setText("Every 5 turns a ");
                    lbl2.setText("random piece on ");
                    lbl3.setText("each side dies. ");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Friendly Fire Chess")) {
                    lbl.setText("You may capture ");
                    lbl2.setText("allies.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Chaos Chess")) {

                } else if (Objects.equals(cb.getSelectedItem().toString(), "Formal Chess")) {
                    lbl.setText("The touch move rule ");
                    lbl2.setText("is enforced. ");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Total War Chess")) {
                    lbl.setText("All pieces ");
                    lbl2.setText("(not pawns) ");
                    lbl3.setText("are made Royal.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Epic Chess")) {
                    lbl.setText("All pawns ");
                    lbl2.setText("cannot be captured, ");
                    lbl3.setText("Pawns may capture ");
                    lbl4.setText("pieces that can't be captured.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Secret Bomber Chess")) {
                    lbl.setText("1 piece on each side ");
                    lbl2.setText("chosen secretly, ");
                    lbl3.setText("at random, ");
                    lbl4.setText("will explode as if it were atomic.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Atomic Chess");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pawn")) {
                    lbl.setText("Can move but not capture 1 space forwards.");
                    lbl2.setText("Can capture 1 space diagonally forwards.");
                    lbl3.setText("Can move but not capture 2 spaces forwards, ");
                    lbl4.setText("but only on it's first move, and not if too far forwards. ");
                    lbl5.setText("Promotes randomly.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pikeman")) {
                    lbl.setText("Can move but not capture 1 space ");
                    lbl2.setText("in any direction but backwards. ");
                    lbl3.setText("Can capture 2 spaces forwards. ");
                    lbl4.setText("");
                    lbl5.setText("Promotes randomly.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Soldier")) {
                    lbl.setText("Can move 1 space forwards. ");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("Promotes randomly.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Prince")) {
                    lbl.setText("Can move capture 1 space in any ");
                    lbl2.setText("direction, even diagonally, ");
                    lbl3.setText("but cannot move backwards. ");
                    lbl4.setText("Promotes randomly, then becomes Royal.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Wall")) {
                    lbl.setText("Can move but not capture 1 space forwards. ");
                    lbl2.setText("Cannot be captured");
                    lbl3.setText("");
                    lbl4.setText("Promotes randomly. ");
                    lbl5.setText("Can be captured again once promoting.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Checker")) {
                    lbl.setText("Can move but not capture 1 space diagonally forwards. ");
                    lbl2.setText("Cam jump 2 spaces diagonally over a foe, ");
                    lbl3.setText("this cannot capture normally, but removes the piece it ");
                    lbl4.setText("jumps over, then it's your turn again. When this would ");
                    lbl5.setText("promote, it becomes Royal instead, and can move backwards.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "King")) {
                    lbl.setText("Can move 1 space orthogonally or diagonally. ");
                    lbl2.setText("Can \"castle\"? ");
                    lbl3.setText("Is Royal.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Castling");
                    cb.addItem("Royalty");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "General")) {
                    lbl.setText("Moves like a King, or a Knight. ");
                    lbl2.setText("Is Royal.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("King");
                    cb.addItem("Knight");
                    cb.addItem("Royalty");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Empress")) {
                    lbl.setText("Moves like an Archer. ");
                    lbl2.setText("Is Royal.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Archer");
                    cb.addItem("Royalty");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Tyrant")) {
                    lbl.setText("Moves like a Pegasus. ");
                    lbl2.setText("Can capture friendly pieces. ");
                    lbl3.setText("Is Royal.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Pegasus");
                    cb.addItem("Royalty");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Knight")) {
                    lbl.setText("Moves 2 spaces in a dimension, ");
                    lbl2.setText("and 1 in the other. ");
                    lbl3.setText("Jumps over pieces. ");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Rook")) {
                    lbl.setText("Slides orthogonally any number of spaces.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Bishop")) {
                    lbl.setText("Slides diagonally any number of spaces.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Camel")) {
                    lbl.setText("Moves 3 spaces in a dimension, ");
                    lbl2.setText("and 1 in the other. ");
                    lbl3.setText("Jumps over pieces.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Elephant")) {
                    lbl.setText("Moves up to 2 spaces diagonally. ");
                    lbl2.setText("");
                    lbl3.setText("Jumps over pieces.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Frog")) {
                    lbl.setText("Moves up to 2 spaces orthogonally. ");
                    lbl2.setText("");
                    lbl3.setText("Jumps over pieces.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Ship")) {
                    lbl.setText("Moves 1 space diagonally, ");
                    lbl2.setText("then slides any number of spaces vertically");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Bull")) {
                    lbl.setText("Moves 3 spaces in a dimension, ");
                    lbl2.setText("and 2 in the other. ");
                    lbl3.setText("Jumps over pieces.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Archer")) {
                    lbl.setText("Slides orthogonally or diagonally ");
                    lbl2.setText("at least 2 spaces. ");
                    lbl3.setText("(Queen - King)");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Queen");
                    cb.addItem("King");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Assassin")) {
                    lbl.setText("Moves but cant capture like a ");
                    lbl2.setText("Queen or a Greatwyrm, but can ");
                    lbl3.setText("only move like a King to capture. ");
                    lbl4.setText("Can capture friendly pieces.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Queen");
                    cb.addItem("Greatwyrm");
                    cb.addItem("King");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Mage")) {
                    lbl.setText("Moves but can't capture like a King. ");
                    lbl2.setText("Gains mana over time. ");
                    lbl3.setText("Spends mana to destroy orthogonal foes. ");
                    lbl4.setText("The stars indicate range at this level of mana. ");
                    lbl5.setText("Promotes to High Mage.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("King");
                    cb.addItem("High Mage");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "High Mage")) {
                    lbl.setText("Moves but can't capture like a King. ");
                    lbl2.setText("Can capture orthogonally or diagonally, ");
                    lbl3.setText("but not like a King, ");
                    lbl4.setText("jumping over pieces, ");
                    lbl5.setText("Does not move when capturing.");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("King");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Archbishop")) {
                    lbl.setText("Moves like a Bishop or a Knight.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Bishop");
                    cb.addItem("Knight");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Chancellor")) {
                    lbl.setText("Moves like a Rook or a Knight.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Rook");
                    cb.addItem("Knight");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Queen")) {
                    lbl.setText("Moves like a Bishop or a Rook.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Bishop");
                    cb.addItem("Rook");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Spider")) {
                    lbl.setText("Moves like a Elephant or a Frog.");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Elephant");
                    cb.addItem("Frog");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Gryphon")) {
                    lbl.setText("Moves 1 space diagonally, ");
                    lbl2.setText("then slides any number of spaces orthogonally.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Manticore")) {
                    lbl.setText("Moves 1 space orthogonally, ");
                    lbl2.setText("then slides any number of spaces diagonally.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Buffalo")) {
                    lbl.setText("Moves like a Knight, ");
                    lbl2.setText("Camel, or Bull.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Knight");
                    cb.addItem("Camel");
                    cb.addItem("Bull");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Pegasus")) {
                    lbl.setText("Moves like a Knight ");
                    lbl2.setText("if the space is empty, it can move again once.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Knight");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Amazon")) {
                    lbl.setText("Moves like a Queen or a Knight ");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Queen");
                    cb.addItem("Knight");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Lion")) {
                    lbl.setText("Moves up to 2 spaces orthogonally, ");
                    lbl2.setText("and up to 2 spaces diagonally");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Greatwyrm")) {
                    lbl.setText("Moves like a Gryphon or a Manticore ");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Gryphon");
                    cb.addItem("Manticore");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Quetzalcoatl")) {
                    lbl.setText("Moves like a Buffalo ");
                    lbl2.setText("if the space is empty, it can move again once.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                    cb.addItem("Buffalo");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Castling")) {
                    lbl.setText("...");
                    lbl2.setText("");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Royalty")) {
                    lbl.setText("A player louses when all of their Royal ");
                    lbl2.setText("pieces are captured, a move will be ");
                    lbl3.setText("highlighted in red if it would ");
                    lbl4.setText("put a Royal piece in danger.");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "2/3 Chess")) {
                    lbl.setText("Each piece has a 1/3 chance ");
                    lbl2.setText("to be a random piece of it's type.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Chess 960")) {
                    lbl.setText("Each piece is a random ");
                    lbl2.setText("piece of it's type.");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Power Chess")) {
                    lbl.setText("each side has a row of ");
                    lbl2.setText("random pawns with a row of ");
                    lbl3.setText("random common pieces behind ");
                    lbl4.setText("it, and behind that a row of ");
                    lbl5.setText("queen like pieces, with at least 2 royals");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Fear Chess")) {
                    lbl.setText("each side has 2 rows of ");
                    lbl2.setText("random pieces ");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Grand Chess")) {
                    lbl.setText("each side has 2 Royals, ");
                    lbl2.setText("2 powerful pieces ");
                    lbl3.setText("and a ton of random pawns.");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "Revolt Chess")) {
                    lbl.setText("Pawns are random pawns, ");
                    lbl2.setText("and are in the center of the board. ");
                    lbl3.setText("");
                    lbl4.setText("");
                    lbl5.setText("");
                    cb.removeAllItems();
                    cb.addItem("OK");
                } else if (Objects.equals(cb.getSelectedItem().toString(), "")) {

                }
            }
        });
        panel.add(btn);


    }
}