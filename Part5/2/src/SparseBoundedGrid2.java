import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.awt.Color;

public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
  // A HashMap to store Location and Actor
  private Map<Location, E> occupantArray;
  private int rows, columns;
  
  // Ensures at least one valid grid
  public SparseBoundedGrid2(int rows, int cols) {
    if (rows <= 0) {
      throw new IllegalArgumentException("rows <= 0");
    }
            
        if (cols <= 0) {
          throw new IllegalArgumentException("cols <= 0");
        }

    this.rows = rows;
    this.columns = cols;

    // Initialize a HashMap
    occupantArray = new HashMap<Location, E>();
    
  }

  public int getNumRows() {
    return rows;
  }

  public int getNumCols() {
    return columns;
  }

  public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

  public ArrayList<Location> getOccupiedLocations() {
    ArrayList<Location> theLocations = new ArrayList<Location>();
    // Each key in the Map is a unique location
    for (Location key : occupantArray.keySet()) {  
    theLocations.add(key);
    }  

    return theLocations;
  }

  public E get(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
    }
          // return an occupant based on the given location
        return (E) occupantArray.get(loc); // unavoidable warning
  }

  public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
            
        if (obj == null) {
          throw new IllegalArgumentException("obj == null");
        }
            

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.put(loc, obj);
        return oldOccupant;
    }

  public E remove(Location loc)
    {
        if (!isValid(loc)) {
          throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        }
            
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray.remove(loc);
        return r;
    }
}