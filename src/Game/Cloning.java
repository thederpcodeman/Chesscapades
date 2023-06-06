package Game;

import com.sun.org.apache.bcel.internal.generic.SWITCH;
import pieces.*;

public class Cloning {
    public static Piece Common(int color){
        int ran = (int) (Math.random() * 10);
        if (ran == 0){
            return new Elephant(color);
        } else if (ran == 1){
            return new Rook(color);
        } else if (ran == 2){
            return new Bishop(color);
        } else if (ran == 3){
            return new Frog(color);
        } else if (ran == 4){
            return new Knight(color);
        } else if (ran == 5){
            return new Camel(color);
        } else if (ran == 6){
            return new Bull(color);
        } else if (ran == 7){
            return Cloning.Advanced(color);
        } else if (ran == 8){
            return new Boat(color);
        }  else if (ran == 9) {
            return new Spider(color);
        }
        return null;
    }
    public static Piece Pawn(int color){
        return new Pawn(color);
    }
    public static Piece Advanced(int color){
        int ran = (int) (Math.random() * 11);
        if (ran == 0){
            return new Archbishop(color);
        } else if (ran == 1){
            return new Chancellor(color);
        } else if (ran == 2){
            return Cloning.Royal(color);
        } else if (ran == 3 || ran == 4){
            return Cloning.Super(color);
        } else if (ran == 5){
            return new Queen(color);
        } else if (ran == 6){
            return new Gryphon(color);
        } else if (ran == 7){
            return new Pegasus(color);
        } else if (ran == 8){
            return new Wyvern(color);
        } else if (ran == 9){
            return new Bladesinger(color);
        } else if (ran == 10) {
            return new Buffalo(color);
        }
        return null;
    }

    public static Piece Super(int color){
        int ran = (int) (Math.random() * 4);
        if (ran == 0){
            return new Amazon(color);
        } else if (ran == 1){
            return new Lion(color);
        } else if (ran == 2){
            return new Greatwyrm(color);
        } else if (ran == 3){
            return new Quetzacoatl(color);
        }
        return null;
    }
    public static Piece Royal(int color){
        int ran = (int) (Math.random() * 4);
        if (ran == 0 || ran == 1){
            return new King(color);
        } else if (ran == 2){
            return new General(color);
        } else if (ran == 3){
            return new Tyrant(color);
        }
        return null;
    }
    public static Piece rPawn(int color){
        int ran = (int) (Math.random() * 8);
        if (ran == 0 || ran == 1 || ran == 2){
            return new Pawn(color);
        } else if (ran == 3){
            return new Soldier(color);
        } else if (ran == 4){
            return new Prince(color);
        } else if (ran == 5){
            return new Pikeman(color);
        } else if (ran == 6){
            return new Wall(color);
        } else if (ran == 7){
            return new Checker(color);
        }
        return null;
    }
    public static Piece Fear(int color){
        int ran = (int) (Math.random() * 24);
        if (ran == 0){
            return new Elephant(color);
        } else if (ran == 1){
            return new Rook(color);
        } else if (ran == 2){
            return new Bishop(color);
        } else if (ran == 3){
            return new Frog(color);
        } else if (ran == 4){
            return new Knight(color);
        } else if (ran == 5){
            return new Camel(color);
        } else if (ran == 6) {
            return new Bull(color);
        } else if (ran == 7) {
            return new Boat(color);
        } else if (ran == 8) {
            return new King(color);
        } else if (ran == 9) {
            return new General(color);
        } else if (ran == 10) {
            return new Tyrant(color);
        } else if (ran == 11) {
            return new Amazon(color);
        } else if (ran == 12) {
            return new Archbishop(color);
        } else if (ran == 13) {
            return new Chancellor(color);
        } else if (ran == 14) {
            return new Lion(color);
        } else if (ran == 15) {
            return new Queen(color);
        } else if (ran == 16) {
            return new Gryphon(color);
        } else if (ran == 17) {
            return new Pegasus(color);
        } else if (ran == 18) {
            return new Wyvern(color);
        } else if (ran == 19) {
            return new Greatwyrm(color);
        } else if (ran == 20) {
            return new Bladesinger(color);
        } else if (ran == 21) {
            return new Spider(color);
        } else if (ran == 22) {
            return new Checker(color);
        } else if (ran == 23){
            return new Buffalo(color);
        }
        return (null);
    }
}
//