# Set7

The source code for the Critter class is in the critters directory

1. What methods are implemented in Critter?

*Answer*: `act()`, `getActors()`, `processActors(ArrayList<Actor> actors)`, `getMoveLocations()`, `selectMoveLocation(ArrayList<Location> locs)`, `makeMove(Location loc)`. The above methods are all implemented in `Critter`.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 38
public void act()
// @line: 56
public ArrayList<Actor> getActors()
// @line: 71
public void processActors(ArrayList<Actor> actors)
// @line: 88
public ArrayList<Location> getMoveLocations()
// @line: 104
public Location selectMoveLocation(ArrayList<Location> locs)
// @line: 125
public void makeMove(Location loc)
```

2. What are the five basic actions common to all critters when they act?

*Answer*: `act()`, `getActors()`, `processActors(ArrayList<Actor> actors)`, `getMoveLocations()`, `selectMoveLocation(ArrayList<Location> locs)`, `makeMove(Location loc)`. All critters will call the five methods when they act.
  + source code:
  ```Java
  // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
  // @line: 42~46
  ArrayList<Actor> actors = getActors();
  processActors(actors);
  ArrayList<Location> moveLocs = getMoveLocations();
  Location loc = selectMoveLocation(moveLocs);
  makeMove(loc);
  ```

3. Should subclasses of Critter override the getActors method? Explain.

*Answer*: Yes, if necessary. The subclasses of Critter can override the `getActors()` to look elsewhere for actors to process.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 56
public ArrayList<Actor> getActors()
```

4. Describe the way that a critter could process actors.

*Answer*: A critter may take the place of the actors if that are not rocks or critters.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 73~77
for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
```

5. What three methods must be invoked to make a critter move? Explain each of these methods.

*Answer*:
  + `getMoveLocations()`, we have to invoke this method to provide the critter valid position to move to.
  + `selectMoveLocation()`, we have to use this method to determine which location the critter will finally move to.
  + `makeMove()`, this method is used to move the critter to the specified location.

  + source code:
  ```Java
  // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
  // @line: 44~46
  ArrayList<Location> moveLocs = getMoveLocations();
  Location loc = selectMoveLocation(moveLocs);
  makeMove(loc);
  ```
6. Why is there no Critter constructor?

*Answer*: Because Critter inherits from Actor. Actor class has a default constructor. So when we instantiate a critter, the compiler will automatically call the default constructor in Actor class.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 31
public class Critter extends Actor
```

# Set 8
The source code for the ChameleonCritter class is in the critters directory

1. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

*Answer*: Because the methods `processActors()` and `makeMove()` in the `act()` have been override. So ChameleonCritter acts different behavior.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\ChameleonCritter
// @line: 36
public void processActors(ArrayList<Actor> actors)
// @line: 50
public void makeMove(Location loc)
```

2. Why does the makeMove method of ChameleonCritter call super.makeMove?

*Answer*: Because in ChameleonCritter, it determines the next location to move into. And then it invokes `super.makeMove()` to move the Critter. The moving action is the same so we can use the `super`.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\ChameleonCritter
// @line: 53
super.makeMove(loc);
```

3. How would you make the ChameleonCritter drop flowers in its old location when it moves?

*Answer*: Add few statements in `makeMove()` like:
```Java
public void makeMove(Location loc)
    {
      location current = getLocation();
      setDirection(getLocation().getDirectionToward(loc));
      super.makeMove(loc);
      if (!current.equals(loc)) {
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(getGrid(), current);
      }
    }
```

4. Why doesn’t ChameleonCritter override the getActors method?

*Answer*: Because ChameleonCritterw will do the same thing as the Critter.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 56
public ArrayList<Actor> getActors()
```

5. Which class contains the getLocation method?

*Answer*: The `Actor` class.
+ source code:
```Java
// @file:\Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
// @line: 102
public Location getLocation()
```

6. How can a Critter access its own grid?

*Answer*: Critter inherits from Actor. So a Critter can access its own grid by invoking the `getGrid()` methods.
+ source code:
```Java
// @file:\Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Actor.java
// @line: 92
public Grid<Actor> getGrid()
```

# Set 9

The source code for the CrabCritter class is reproduced at the end of this part of GridWorld.

1. Why doesn’t CrabCritter override the processActors method?

*Answer*: Because the CrabCritter inherits from Critters. Both Critter and CrabCritter eat the actors(except for critters and rocks) in the actors list. So their codes are the same. There is no need to override it.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 71
public void processActors(ArrayList<Actor> actors)
```

2. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

*Answer*: First the CrabCritter invokes `getActors()` methods to get all the actors that are in front of it or in left-front or in right-front. Then the CrabCritter invokes `processActors()` to eat any actors that are not critter or rocks. So a CrabCritter does not always eat all neighboring actors.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\CrabCritter
// @line: 44~57
public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

3. Why is the getLocationsInDirections method used in CrabCritter?

*Answer*: Because it has to find out the position that in the front, in left-front, or in right-front of the CrabCritter. So it need to calculate the location base on the CrabCritter's location.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\CrabCritter
// @line: 49
for (Location loc : getLocationsInDirections(dirs))
// @line: 67
for (Location loc : getLocationsInDirections(dirs))
```

4. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

*Answer*: Location(4,3), Location(4,4), Location(4,5)
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\CrabCritter
// @line: 44~57
public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
            { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
```

5. What are the similarities and differences between the movements of a CrabCritter and a Critter?

*Answer*:
  + Similarities: Both them move themselves without changing their direction. That mean, before the move it face South, then after the move it still face South.
  + Differences: ① A Critter can move to any locations that are neighboring. But a CrabCritter can only move to left or right, just like a crab.② A Critter can not turn to another direction when it can not move. But a CrabCritter can turn 90 degrees left or right if it can not move.
  + source code:
  ```Java
  // @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\CrabCritter
  // @line: 77~91
  public void makeMove(Location loc)
      {
          if (loc.equals(getLocation()))
          {
              double r = Math.random();
              int angle;
              if (r < 0.5)
                  angle = Location.LEFT;
              else
                  angle = Location.RIGHT;
              setDirection(getDirection() + angle);
          }
          else
              super.makeMove(loc);
      }
  ```
6. How does a CrabCritter determine when it turns instead of moving?

*Answer*: By checking whether the location which is supposed to move into is the same as where it occupies. If two location is the same, that means it cannot move and a turn is needed. Otherwise, the CrabCritter moves.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\projects\critters\CrabCritter
// @line: 79~88
if (loc.equals(getLocation()))
        {
            double r = Math.random();
            int angle;
            if (r < 0.5)
                angle = Location.LEFT;
            else
                angle = Location.RIGHT;
            setDirection(getDirection() + angle);
        }
```

7. Why don’t the CrabCritter objects eat each other?

*Answer*: In `processActors()` methods in Critter, the Critter only eat actors that are not Critter or Rock. Because CrabCritter inherits from Critter, it cannot eat other CrabCritter obviously.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor\Critter.java
// @line: 75~76
if (!(a instanceof Rock) && !(a instanceof Critter))
      a.removeSelfFromGrid();
```
