package Game;

import pieces.*;

import java.util.ArrayList;

public class Setup {
    public static void refresh(Board chessBoard, boolean ep){
        int rand = (int) (Math.random() * 9);
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
        }else if (rand == 6) {

            //B.S.O.P. chess

            //setup black pieces
            chessBoard.getTile(0).setPiece(Cloning.Super(0));
            chessBoard.getTile(7).setPiece(Cloning.Super(0));
            chessBoard.getTile(1).setPiece(Cloning.Super(0));
            chessBoard.getTile(6).setPiece(Cloning.Super(0));
            chessBoard.getTile(2).setPiece(Cloning.Super(0));
            chessBoard.getTile(5).setPiece(Cloning.Super(0));
            chessBoard.getTile(3).setPiece(Cloning.Super(0));
            chessBoard.getTile(4).setPiece(Cloning.Royal(0));
            for (int i = 8; i < 16; i++) {
                chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
            }
            //setup white pieces
            chessBoard.getTile(56).setPiece(Cloning.Super(1));
            chessBoard.getTile(63).setPiece(Cloning.Super(1));
            chessBoard.getTile(57).setPiece(Cloning.Super(1));
            chessBoard.getTile(62).setPiece(Cloning.Super(1));
            chessBoard.getTile(58).setPiece(Cloning.Super(1));
            chessBoard.getTile(61).setPiece(Cloning.Super(1));
            chessBoard.getTile(59).setPiece(Cloning.Super(1));
            chessBoard.getTile(60).setPiece(Cloning.Royal(1));
            for (int i = 48; i < 56; i++) {
                Tile tile = (Tile) chessBoard.getComponent(i);
                tile.setPiece(Cloning.rPawn(1));
            }
        }else {
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
            for (Integer i : loop) {
                if ((int) (Math.random() * 3) == 1) {
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
            for (Integer i : loop) {
                if ((int) (Math.random() * 3) == 1) {
                    chessBoard.getTile(i).setPiece(Cloning.Common(1));
                }
            }
            if ((int) (Math.random() * 3) == 1) {
                chessBoard.getTile(3).setPiece(Cloning.Advanced(0));
            }
            if ((int) (Math.random() * 3) == 1) {
                chessBoard.getTile(59).setPiece(Cloning.Advanced(1));
            }
            if ((int) (Math.random() * 3) == 1) {
                chessBoard.getTile(4).setPiece(Cloning.Royal(0));
            }
            if ((int) (Math.random() * 3) == 1) {
                chessBoard.getTile(60).setPiece(Cloning.Royal(1));
            }
            for (int i = 8; i < 16; i++) {
                if ((int) (Math.random() * 3) == 1) {
                    chessBoard.getTile(i).setPiece(Cloning.rPawn(0));
                }
            }
            for (int i = 48; i < 56; i++) {
                if ((int) (Math.random() * 3) == 1) {
                    chessBoard.getTile(i).setPiece(Cloning.rPawn(1));
                }
            }
        }
        // ensures* fairness*
        boolean unfair = true;
        int max = 20;
        while (unfair) {
            int bVal = 0;
            int wVal = 1;
            for (Tile t : chessBoard.getOccupiedTilesOfColor(0)){
                bVal += t.getPiece().value;
                if (t.getPiece().royal){
                    bVal += 5;
                }
            }
            for (Tile t : chessBoard.getOccupiedTilesOfColor(1)){
                wVal += t.getPiece().value;
                if (t.getPiece().royal){
                    wVal += 5;
                }
            }
            if (Math.abs(bVal - wVal) > 5){
                max -= 1;
                if (max <= 0){
                    unfair = false;
                    System.out.println("BS");
                }
                int rteam = 0;
                int r = (int) (Math.random() * 8);
                if ((int) (Math.random() * 2) == 0){
                    r += 56;
                    rteam = 1;
                }
                if (chessBoard.getTile(r).getPiece() != null && !chessBoard.getTile(r).getPiece().royal){
                    chessBoard.getTile(r).setPiece(Cloning.Fear(rteam));
                }

            }else{
                unfair = false;
            }
        }

        // modifiers
        if (Math.random() * 7 == 1){
            Tile sel;
            int tries = 10;
            while (tries > 0){
                tries --;
                Tile[] tot = chessBoard.getOccupiedTilesOfColor(1);
                sel = tot[(int)(Math.random() * tot.length)];
                if (!sel.getPiece().royal){
                    sel.getPiece().bomb = true;
                    tries = 0;
                }
            }
            tries = 10;
            while (tries > 0){
                tries --;
                Tile[] tot = chessBoard.getOccupiedTilesOfColor(0);
                sel = tot[(int)(Math.random() * tot.length)];
                if (!sel.getPiece().royal){
                    sel.getPiece().bomb = true;
                    tries = 0;
                }
            }
        }
        if (ep){
            for (Tile i : chessBoard.getOccupiedTiles()){
                if (i.getPiece() != null && !(i.getPiece() instanceof Pawn)){
                    i.getPiece().royal = true;
                }
            }
        }



        //if Royal no protection
        for (Tile i : chessBoard.getOccupiedTiles()){
            if (i.getPiece() != null && !(i.getPiece().royal)){
                i.getPiece().wall = false;
            }
        }
    }
}
