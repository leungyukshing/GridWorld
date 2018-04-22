import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

public class QuickCrab extends CrabCritter {

  private ArrayList<Location> getQuick() {
    /*get the quick position*/
      ArrayList<Location> locs = new ArrayList<Location>();
      
      Location present = getLocation();
      Location left = present.getAdjacentLocation(getDirection() + Location.LEFT);
      Location right = present.getAdjacentLocation(getDirection() + Location.RIGHT);

      Grid<Actor> grid = getGrid();

      /*Left*/
      if (grid.isValid(left) && grid.get(left) == null) {
        Location leftTwo = left.getAdjacentLocation(getDirection() + Location.LEFT);
        // Valid and empty
        if (grid.isValid(leftTwo) && grid.get(leftTwo) == null) {
          locs.add(leftTwo);
        }
      }

      /*Right*/
      if (grid.isValid(right) && grid.get(right) == null) {
        Location rightTwo = right.getAdjacentLocation(getDirection() + Location.RIGHT);
        // Valid and empty
        if (grid.isValid(rightTwo) && grid.get(rightTwo) == null) {
          locs.add(rightTwo);
        }
      }

      return locs;
  }

  public ArrayList<Location> getMoveLocations()
    {
      ArrayList<Location> locs = getQuick();
      // If no quickLocation, do the same thing as Crab
      if (locs.size() == 0) {
         
        int[] dirs =
            { Location.LEFT, Location.RIGHT };
        for (Location loc : getLocationsInDirections(dirs)) {
          if (getGrid().get(loc) == null) {
              locs.add(loc);
            }
        }
      }
      return locs;
    }
}