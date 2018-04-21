import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.awt.Color;

public class RockHound extends Critter {
  public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
          // Only remove Rock
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }
}