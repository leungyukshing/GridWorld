import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
  // Use a list of LinkedList to store the occupants
  private ArrayList<LinkedList<OccupantInCol>> occupantArray;
  private int rows, columns;
  
  // Ensures at least one valid location
  public SparseBoundedGrid(int rows, int cols) {
    if (rows <= 0) {
      throw new IllegalArgumentException("rows <= 0");
    }
            
        if (cols <= 0) {
          throw new IllegalArgumentException("cols <= 0");
        }

    this.rows = rows;
    this.columns = cols;

    // Allocate memeory space
    occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rows);
    for (int i = 0; i < rows; i++) {
      LinkedList<OccupantInCol> temp = new LinkedList<OccupantInCol>();
      occupantArray.add(temp);
    }
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
    // Find out all the occupants stored in occupantArray
    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < occupantArray.get(r).size(); c++) {
        OccupantInCol ob = occupantArray.get(r).get(c);
        // Obtain Location for each occupant
         Location loc = new Location(r, ob.getCol());
         theLocations.add(loc);
      }
    }

    return theLocations;
  }

  public E get(Location loc) {
    if (!isValid(loc)) {
      throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
    }
            
        LinkedList<OccupantInCol> row = occupantArray.get(loc.getRow());
        // First to test whether the row has non-empty actor
        if (row.size() == 0) {
          return null;
        }
        else {
          // To find the occupant that in the column
          for (int i = 0; i < row.size(); i++) {
            // Traverse each ocupant in the LinkedList
            if (row.get(i).getCol() == loc.getCol()) {
              return (E) row.get(i).getOccupant();
            }
          }
        }

    return null;
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
        occupantArray.get(loc.getRow()).add(new OccupantInCol(obj, loc.getCol()));
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
        LinkedList<OccupantInCol> list = occupantArray.get(loc.getRow());
        if (list.size() != 0) {
          for (int i = 0; i< list.size(); i++) {
            // Find the occupant in the specified column
            if (list.get(i).getCol() == loc.getCol()) {
              list.remove(i);
            }
          }
        }
        return r;
    }
}