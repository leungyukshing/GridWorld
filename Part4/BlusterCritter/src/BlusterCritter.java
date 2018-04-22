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
        int presentColumn = presentPosition.getCol();
        int presentRow = presentPosition.getRow();

        // Calculate all actors in the 24 positions
        int neighborColumnLeft = (presentColumn - 2) < 0 ? 0 : presentColumn - 2;
        int neighborColumnRight = (presentColumn + 2) >= getGrid().getNumCols() ? getGrid().getNumCols() - 1 : presentColumn + 2;
        int neighborRowTop = (presentRow - 2) < 0 ? 0 : presentRow - 2;
        int neighborRowButton = (presentRow + 2) >= getGrid().getNumRows() ? getGrid().getNumRows() - 1 : presentRow + 2;
        Grid<Actor> grid = getGrid();
        for (int i = neighborRowTop; i <= neighborRowButton; i++) {
          for (int j = neighborColumnLeft; j <= neighborColumnRight; j++) {
            Actor temp = grid.get(new Location(i, j));
            // If there is an actor, then add it to the List
            if (temp != null) {
              result.add(temp);
            }
          }
        }
        return result;
    }

    // brighten the critter
    private void brighten() {
       Color color = getColor();
          int red = (int) (color.getRed() * (1 + DARKENING_FACTOR));
          int green = (int) (color.getGreen() * (1 + DARKENING_FACTOR));
          int blue = (int) (color.getBlue() * (1 + DARKENING_FACTOR));
          red = red > 255 ? 255 : red;
          green = green > 255 ? 255 : green;
          blue = blue > 255 ? 255 : blue;
          setColor(new Color(red, green, blue));
    }

    // darken the critter
    private void darken() {
      Color color = getColor();
          int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
          int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
          int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
          red = red < 0 ? 0 : red;
          green = green < 0 ? 0 : green;
          blue = blue < 0 ? 0 : blue;
          setColor(new Color(red, green, blue));
    }

    public void processActors(ArrayList<Actor> actors)
    {
      for (Actor a : actors)
        {
          // Not a rock or a critter, then eat it
            if (!(a instanceof Rock) && !(a instanceof Critter)) {
              a.removeSelfFromGrid();
            }  
        }
        // brighter
        if (actors.size() < c) {
          brighten();
        }
        // darker
        else {
          darken();
        }
    }
}