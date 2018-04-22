import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class ModifiedChameleonCritter extends Critter {
  // In Flower
  private static final double DARKENING_FACTOR = 0.05;

  // change its color based on the Actors surrounded.
  public void processActors(ArrayList<Actor> actors)
    {
      // If actors is not empty, randomly select a color
      if (actors.size() != 0) {
        int randomNumber = (int)(Math.random()*actors.size());
        Color toColor = actors.get(randomNumber).getColor();
        setColor(toColor);
      }
      // darken the critter
      else {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));

        setColor(new Color(red, green, blue));
      }
    }
    
    public void makeMove(Location loc)
    {
        // If the location is not exist, remove
        if (loc == null) {
          removeSelfFromGrid();
        }
        else {
            setDirection(getLocation().getDirectionToward(loc));
            moveTo(loc);
        }

    }
}