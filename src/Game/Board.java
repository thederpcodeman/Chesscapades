package Game;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;

public class Board extends JPanel {
    public Board() {
        super();
    }

    public Tile getTile(int i)
    {
        return (Tile) getComponent(i);
    }

    public static int getXFromLocation(int i)
    {
        return i % 8;
    }

    public static int getYFromLocation(int i)
    {
        return i / 8;
    }

    public static int getLocationFromCords(int x, int y)
    {
        return y * 8 + x;
    }

    public Tile[] getTiles()
    {
        Component[] components = getComponents();
        List<Tile> tiles = new ArrayList();
        for(Component component:components)
        {
            if (component instanceof Tile)
            {
                tiles.add((Tile) component);
            }
        }
        Tile[] arr = new Tile[tiles.size()];
        arr = tiles.toArray(arr);
        return arr;
    }
}
