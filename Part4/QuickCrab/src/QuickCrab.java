import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {
  public ArrayList<Location> getMoveLocations()
    {
      /*get the quik position*/
      ArrayList<Location> locs = new ArrayList<Location>();
      
      Location present = getLocation();
      Location left = present.getAdjacentLocation(getDirection() + Location.LEFT);
      Location right = present.getAdjacentLocation(getDirection() + Location.RIGHT);

      Grid<Actor> grid = getGrid();

      /*Left*/
      if (grid.isValid(left) && grid.get(left) == null) {
        Location leftTwo = left.getAdjacentLocation(getDirection() + Location.LEFT);
        /*valid and empty*/
        if (grid.isValid(leftTwo) && grid.get(leftTwo) == null) {
          locs.add(leftTwo);
        }
      }

      /*Right*/
      if (grid.isValid(right) && grid.get(right) == null) {
        Location rightTwo = right.getAdjacentLocation(getDirection() + Location.RIGHT);
        if (grid.isValid(rightTwo) && grid.get(rightTwo) == null) {
          locs.add(rightTwo);
        }
      }

      if (locs.size() == 0) {
         
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
            if (getGrid().get(loc) == null)
                locs.add(loc);

      }
      return locs;
    }
}