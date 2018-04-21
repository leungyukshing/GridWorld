import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;

public class SparseBoundedGrid<E> extends AbstractGrid<E> {
  private ArrayList<LinkedList<OccupantInCol>> occupantArray;
  private int rows, columns;
  
  public SparseBoundedGrid(int rows, int cols) {
    if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
        //occupantArray = new Object[rows][cols];
    this.rows = rows;
    this.columns = cols;
    occupantArray = new ArrayList<LinkedList<OccupantInCol>>(rows);
    for (int i = 0; i < rows; i++) {
      LinkedList<OccupantInCol> temp = new LinkedList<OccupantInCol>();
      occupantArray.set(i, temp);
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

    for (int r = 0; r < rows; r++) {
      for (int c = 0; c < occupantArray.get(r).size(); c++) {
        OccupantInCol ob = occupantArray.get(r).get(c);
         Location loc = new Location(r, ob.getCol());
         theLocations.add(loc);
      }
    }

    return theLocations;
  }

  public E get(Location loc) {
    if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        return (E) occupantArray.get(loc.getRow()).get(loc.getCol()); // unavoidable warning
  }

  public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).set(loc.getCol(), (OccupantInCol)obj);
        return oldOccupant;
    }

  public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
        occupantArray.get(loc.getRow()).remove(loc.getCol());
        return r;
    }
}