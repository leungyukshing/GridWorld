import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class BlusterCritter extends Critter {
  private static final double DARKENING_FACTOR = 0.05;
  private int c;

  public BlusterCritter(int c) {
    super();
    this.c = c;
  }

  public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> result = new ArrayList<Actor>();
        
        Location presentPosition = getLocation();
        int present_column = presentPosition.getCol();
        int present_row = presentPosition.getRow();

        int neighbor_column_left = (present_column - 2) < 0 ? 0 : present_column - 2;
        int neighbor_column_right = (present_column + 2) >= getGrid().getNumCols() ? getGrid().getNumCols() - 1 : present_column + 2;
        int neighbor_row_top = (present_row - 2) < 0 ? 0 : present_row - 2;
        int neighbor_row_button = (present_row + 2) >= getGrid().getNumRows() ? getGrid().getNumRows() - 1 : present_row + 2;
        Grid<Actor> grid = getGrid();
        for (int i = neighbor_row_top; i <= neighbor_row_button; i++) {
          for (int j = neighbor_column_left; j <= neighbor_column_right; j++) {
            Actor temp = grid.get(new Location(i, j));
            if (temp != null) {
              result.add(temp);
            }
          }
        }
        return result;
    }

    public void processActors(ArrayList<Actor> actors)
    {
      for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
        // brighter
        if (actors.size() < c) {
          Color c = getColor();
          int red = (int) (c.getRed() * (1 + DARKENING_FACTOR));
          int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR));
          int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR));
          red = red > 255 ? 255 : red;
          green = green > 255 ? 255 : green;
          blue = blue > 255 ? 255 : blue;
          setColor(new Color(red, green, blue));
        }
        // darker
        else {
          Color c = getColor();
          int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
          int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
          int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
          red = red < 0 ? 0 : red;
          green = green < 0 ? 0 : green;
          blue = blue < 0 ? 0 : blue;
          setColor(new Color(red, green, blue));
        }
    }
}