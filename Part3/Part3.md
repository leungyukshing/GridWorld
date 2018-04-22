# Set3

Assume the following statements when answering the following questions.
```java
Location loc1 = new Location(4, 3);
Location loc2 = new Location(3, 4);
```
1. How would you access the row value for loc1?

*Answer*: use the `getRow()` method in **Location**.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 110~113
   public int getRow()
   {
       return row;
   }
  ```

2. What is the value of b after the following statement is executed?
 ```Java
 boolean b = loc1.equals(loc2);
```

*Answer*: b is **false**. Because `loc1` and `loc2` do not in the same place.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 205~212
   public boolean equals(Object other)
   {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
  ```

3. What is the value of loc3 after the following statement is executed?
 ```java
 Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);
 ```

*Answer*: `loc3`'row is 4, and its column is also 4.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 130~169
   public Location getAdjacentLocation(int direction)
   {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
   }
  ```

4. What is the value of dir after the following statement is executed?
```java
 int dir = loc1.getDirectionToward(new Location(6, 5));
```

*Answer*: The value of dir is 135, Southeast.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 178~195
   public int getDirectionToward(Location target)
    {
        int dx = target.getCol() - getCol();
        int dy = target.getRow() - getRow();
        // y axis points opposite to mathematical orientation
        int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

        // mathematical angle is counterclockwise from x-axis,
        // compass angle is clockwise from y-axis
        int compassAngle = RIGHT - angle;
        // prepare for truncating division by 45 degrees
        compassAngle += HALF_RIGHT / 2;
        // wrap negative angles
        if (compassAngle < 0)
            compassAngle += FULL_CIRCLE;
        // round to nearest multiple of 45
        return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
    }
  ```

5. How does the getAdjacentLocation method know which adjacent location to return?

*Answer*: First the method calculate the exact the direction that the actor faces. Then the methods compute the variation of row and column. Finally the method compute the adjacent location's row and column value by invoking `getRow()` and `getCol()`.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 130~169
   public Location getAdjacentLocation(int direction)
   {
        // reduce mod 360 and round to closest multiple of 45
        int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
        if (adjustedDirection < 0)
            adjustedDirection += FULL_CIRCLE;

        adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
        int dc = 0;
        int dr = 0;
        if (adjustedDirection == EAST)
            dc = 1;
        else if (adjustedDirection == SOUTHEAST)
        {
            dc = 1;
            dr = 1;
        }
        else if (adjustedDirection == SOUTH)
            dr = 1;
        else if (adjustedDirection == SOUTHWEST)
        {
            dc = -1;
            dr = 1;
        }
        else if (adjustedDirection == WEST)
            dc = -1;
        else if (adjustedDirection == NORTHWEST)
        {
            dc = -1;
            dr = -1;
        }
        else if (adjustedDirection == NORTH)
            dr = -1;
        else if (adjustedDirection == NORTHEAST)
        {
            dc = 1;
            dr = -1;
        }
        return new Location(getRow() + dr, getCol() + dc);
   }
  ```





# Set 4
1. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

 *Answer*: First I compute the count of the objects in a grid by using `getOccupiedLocations()`.
Then I can obtain a count of the empty locations in a bounded grid by using the total count to subtract the occupied count, that is
```Java
int emptyCount = 'getNumRows() * getNumCols() - getOccupiedLocations
```


2. How can you check if location (10,10) is in a grid?

 *Answer*: I can check whether the location is in a grid by invoking the `isValid(Location loc)` method.
 + source code：
   ```Java
    // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Grid.java
    // @line: 50
    boolean isValid(Location loc);
   ```

3. Grid contains method declarations, but no code is supplied in the methods. Why Where can you find the implementations of these methods?

 *Answer*: Because `Grid` is an interface. There is no need for `Grid` to implement anything, but this job will be done by those classes that implements `Grid`. I can find the implementations of these methods in `BoundedGrid` and `UnboundedGrid`.
 + source code：
   ```Java
    // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Grid.java
    // @line: 29
    public interface Grid<E>
   ```

4. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

 *Answer*: No. Because every time you invoke these methods, you don't know the exact number of objects that you will return. So the size of the objects is unknow. Using an array may cause **out of index** problem or waste memory. However, using an `ArrayList` can avoid these problems, because it can dynamically add or remove objects.
 + source code：
   ```Java
    // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Grid.java
    // @line: 85
    ArrayList<Location> getOccupiedLocations();
    // @line: 96
    ArrayList<Location> getValidAdjacentLocations(Location loc);
    // @line: 107
    ArrayList<Location> getEmptyAdjacentLocations(Location loc);
    // @line: 118
    ArrayList<Location> getOccupiedAdjacentLocations(Location loc);
    // @line: 129
    ArrayList<E> getNeighbors(Location loc);
   ```

# Set 5
1. Name three properties of every actor.

 *Answer*: location, direction, color
 + source code:
 ```Java
 // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
 // @line: 32~34
 private Location location;
 private int direction;
 private Color color;
 ```

2. When an actor is constructed, what is its direction and color?

 *Answer*: When an actor is constructed, its direction is `Location.NORTH`, that is 0, and its color is `Color.BLUE`.
 + source code:
 ```Java
 // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
 // @line: 39~45
 public Actor()
{
    color = Color.BLUE;
    direction = Location.NORTH;
    grid = null;
    location = null;
}
 ```

3. Why do you think that the Actor class was created as a class instead of an interface?

 *Answer*: Because an interface just define a set of methods, but nothing about their implementations. And also an interface can never be instantiated. The `Actor` will be instantiated in many other class like 'BugRunner'. So it was created as a class instead of an interface.
 + source code:
 ```Java
 // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
 // @line: 29
 public class Actor
 ```

4. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

 *Answer*:
 + No. When an actor try to put itself into a grid twice without first removing itself, the program will throw `IllegalStateException()`.
 + No. When an actor try to remove itself form a grid a second time, the program will also throw `IllegalStateException()`.
 + Yes. Because when an actor is placed into a grid, the grid is available. Then remoing it and put it back. No exception will be thrown.

 + source code:
 ```Java
 // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
 // @line: 117~119
 if (grid != null)
            throw new IllegalStateException(
                    "This actor is already contained in a grid.");
  // @line: 135~137
  if (grid == null)
            throw new IllegalStateException(
                    "This actor is not contained in a grid.");
 ```


5. How can an actor turn 90 degrees to the right?

 *Answer*: An actor can turn 90 degrees to the right by invoking `setDirection(int newDirection)`. For example:
 ```
 Actor actor = new Actor();
 actor.setDirection(actor.getDirection() + Location.RIGHT);
 ```


 # Set 6
1. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

*Answer*: The following statements ensures that a bug does not try to move out of its grid.
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
// @line: 97~99
Location next = loc.getAdjacentLocation(getDirection());
        if (!gr.isValid(next))
            return false;
```

2. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

*Answer*: The following statements ensures that a bug will not walk into a rock. The method allow a bug walk into an empty grid or a flower, but not a rock.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
// @line: 100~101
Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower);
```

3. Which methods of the Grid interface are invoked by the canMove method and why?

*Answer*: The `canMove` method invokes `isValid(Location loc)` and `get(Location loc)` methods in the Grid interface. `isValid(Location loc)` is invoked to test whether the next location is in the grid. And the `get(Location loc)` methods is used to get the actor on the next location, which is used in later code.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 98~99
   if (!gr.isValid(next))
      return false;
   // @line: 100
   Actor neighbor = gr.get(next);

   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Grid.java
   // @line: 79
   E get(Location loc);
   // @line: 50
   boolean isValid(Location loc);
  ```


4. Which method of the Location class is invoked by the canMove method and why?

*Answer*: The `canMove()` method invokes `getAdjacentLocation(int direction)`. The `getAdjacentLocation(int direction)` return the next location that the bug suppose to reach.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 97
   Location next = loc.getAdjacentLocation(getDgetDirection());

   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
   // @line: 130
   public Location getAdjacentLocation(int direction)
  ```

5. Which methods inherited from the Actor class are invoked in the canMove method?

*Answer*: `getGrid()`, `getLocation()`. The `getGrid()` method return the grid that the actor on. And the `getLocation()` return the present location.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 96
   Location loc = getLocation();

   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
   // @line: 102~105
   public Location getLocation() {
     return location;
   }
  ```

6. What happens in the move method when the location immediately in front of the bug is out of the grid?

*Answer*: If the next location is out of the grid, the `move` method will call `removeSelfFromGrid()` to eliminated itself from the grid.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 80~81
   else
      removeSelfFromGrid();
  ```

7. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

*Answer*: Yes, the loc variable is necessary. It could not be avoided by calling `getLocation()` multiple times. Because we need to place a flower in the loc. After invoking `moveTo()` or `removeSelfFromGrid()`, the current location is changed. So we need to store the previous location to place a flower.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 76~83
   Location loc = getLocation();
   Location next = loc.getAdjacentLocation(getDirection());
   if (gr.isValid(next))
      moveTo(next);
   else
      removeSelfFromGrid();
   Flower flower = new Flower(getColor());
   flower.putSelfInGrid(gr, loc);
  ```

8. Why do you think the flowers that are dropped by a bug have the same color as the bug?

*Answer*: Because, in the `move()` method, when a bug move, it constructs a flower by using the `getColor()` methods to pass a parameter. So the flower left by the bug has the same color as the bug.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 82~83
   Flower flower = new Flower(getColot());
   flower.putSelfInGrid(gr, loc);
  ```

9. When a bug removes itself from the grid, will it place a flower into its previous location?

*Answer*: It depends which method you are supposed to talk about. In `move()` methods, when a bug removes itself from the grid, it will place a flower into its previous location. However, in `removeSelfFromGrid()` method, there is nothing left in the previous location.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 80~83
   else
      removeSelfFromGrid();
   Flower flower = new Flower(getColot());
   flower.putSelfInGrid(gr, loc);

   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
   // @line: 143~145
   grid.remove(location);
   grid = null;
   location = null;
  ```

10. Which statement(s) in the move method places the flower into the grid at the bug’s previous location?

*Answer*: Let's see the source code.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 82~83
   Flower flower = new Flower(getColot());
   flower.putSelfInGrid(gr, loc);
  ```

11. If a bug needs to turn 180 degrees, how many times should it call the turn method?

*Answer*: 4 times. Because calling `turn()` method one times turns right 45 degrees.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Bug.java
   // @line: 62~65
   public void turn()
   {
      setDirection(getDirection() + Location.HALF_RIGHT);
   }
  ```
