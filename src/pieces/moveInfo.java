package pieces;

import Game.Tile;

public class moveInfo {
    public Tile start;
    public Tile end;
    public moveInfo(Tile s, Tile e){
        start = s;
        end = e;
    }
    public double score(){
        return 0;
    }
}
