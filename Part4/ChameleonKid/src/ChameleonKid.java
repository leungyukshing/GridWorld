import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class ChameleonKid extends ModifiedChameleonCritter {
  public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> result = new ArrayList<Actor>();
        ArrayList<Actor> neighbors =  getGrid().getNeighbors(getLocation());
        // Find out those in the front or back of the critter
        for (int i = 0; i < neighbors.size(); i++) {
          if (getLocation().getDirectionToward(neighbors.get(i).getLocation()) == 0 || getLocation().getDirectionToward(neighbors.get(i).getLocation()) == 180) {
            result.add(neighbors.get(i));
          }
        }
        return result;
    }
}