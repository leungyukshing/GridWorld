import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class KingCrab extends CrabCritter {
  public void processActors(ArrayList<Actor> actors)
    {
      Grid<Actor> grid = getGrid();
      Location present = getLocation();
        for (Actor a : actors)
        {
            int direction = present.getDirectionToward(a.getLocation());
            // The position that the actor suppose to move to
            Location toMove = a.getLocation().getAdjacentLocation(direction);
            // If the actor can move
            if (grid.isValid(toMove)) {
              a.moveTo(toMove);
            }
            // Or remove it
            else {
              a.removeSelfFromGrid();
            }
        }
    }
}