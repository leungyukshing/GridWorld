import info.gridworld.grid.Grid;
import java.util.ArrayList;
import java.awt.Color;

public class OccupantInCol {
  // Store the obect
  private Object occupant;
  // Store the index in ArrayList
  private int colIndex;

  /* Constructor*/
  OccupantInCol(Object object, int col) {
    this.occupant = object;
    this.colIndex = col;
  }

  /*se methods for occupant*/
  public void setOccupant(Object object) {
    this.occupant = object;
  }

  /*set methods for occupant */
  public Object getOccupant() {
    return occupant;
  }

   /*set methods for column index*/
  public void setCol(int col){
    this.colIndex = col;
  }

  /*get methods for column index*/
  public int getCol() {
    return colIndex;
  }
}