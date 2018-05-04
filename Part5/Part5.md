# Set 10
The source code for the AbstractGrid class is in Appendix D.

1. Where is the isValid method specified? Which classes provide an implementation of this method?

*Answer*: The `isValid()` method is specified in Grid class. BoundedGrid and UnboundedGrid provide implementations of this methods.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Grid.java
// @line: 50
boolean isValid(Location loc);
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 60
public boolean isValid(Location loc)
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 53
public boolean isValid(Location loc)
```

2. Which AbstractGrid methods call the isValid method? Why don’t the other methods need to call it?

*Answer*: The `getValidAdjacentLocations()` in AbstractGrid call the `isValid()` method. Because `getValidAdjacentLocations()` need to use `isValid()` to test whether a given location is valid. And other methods use `getValidAdjacentLocations()`, so actually any method that invoke `getValidAdjacentLocations()` also invoke `isValid()`.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
// @line: 44
if (isValid(neighborLoc))
```

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

*Answer*: The `get()` and `getOccupiedLocations()` methods are called in the `getNeighbors()`. BoundedGrid and UnboundedGrid implement `get()` and  AbstractGrid implements the `getOccupiedLocations()`.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
// @line: 62
public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 85
public E get(Location loc)
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
// @line: 66
public E get(Location loc)
```

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

*Answer*: We can use `get()` to find out whether a neighboring location is empty. If it returns a null value, that means the location is empty, and we can add this location to the return list.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 85
public E get(Location loc)
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
// @line: 66
public E get(Location loc)
```

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

*Answer*: If such a replacement is done, the `getValidAdjacentLocations()` method will only consider four locations as the neighbor, which are NORTH, SOUTH, WEST and EAST. So if you replace it, that means you define the **neighbor** as four location.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\AbstractGrid.java
// @line: 41
for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
```


# Set 11
The source code for the BoundedGrid class is in Appendix D.


1. What ensures that a grid has at least one valid location?

*Answer*: In the constructor of BoundedGrid, it will throw exception if rows <= 0 or columns <= 0. This ensures that a grid has at least one valid location.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 41~44
if (rows <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (cols <= 0)
            throw new IllegalArgumentException("cols <= 0");
```


2. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

*Answer*: The number of columns is determined by `occupantArray[0].length`. Because the constructor ensures at least one valid location, the `occupantArray[0].length` must >= 0.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 57
return occupantArray[0].length;
```

3. What are the requirements for a Location to be valid in a BoundedGrid?

*Answer*: The column and row in the Location should be no more than the column's number and row'number respectively in the grid.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 62~63
return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
```


In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

1. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

*Answer*: The type `ArrayList<Location>`. The time complexity is *O(r×c)*, because it has a nested loop.
  + source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 66
public ArrayList<Location> getOccupiedLocations()
```

2. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

*Answer*: The type `E`. A Location type is needed for a parameter. The time complexity is *O(1)*. Because we can access the array directly by using index.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 85
public E get(Location loc)
```

3. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

*Answer*: If the location is not in the grid or the object is null, the `put()` will throw an exception. The time complexity is *O(1)*. Because we can use index to access array directly.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 95~99
if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");
```

4. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

*Answer*: The type `E`. If you attempt to remove an item from an empty location, the `remove()` method will throw exception. The time complexity is *O(1)*. Because we can use index to access array directly.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\BoundedGrid.java
// @line: 107~117
public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
```

5. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

*Answer*: Yes. Based on the complexity of each method, only `getOccupiedLocations()` has a complexity of *O(n×c)*, the other methods are *O(1)*. So this is an efficient implementation.

# Set 12

The source code for the UnboundedGrid class is in Appendix D.

1. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

*Answer*: The `hashCode()` and `equals()` methods must be implemented by Location. So the grid can use HashMap for storage. The Location may implement `Comparable` interface and implement `compareTo()` method. Yes, Location satisfies all these requirements.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\Location.java
// @line: 205
public boolean equals(Object other)
// @line: 218
public int hashCode()
// @line: 234
public int compareTo(Object other)
```

2. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

*Answer*: Because we may access the HashMap in these methods. Keys in HashMap cannot be null. And the `isValid()` method in UnboundedGrid return true if the location's column and row is non-negative. So checks for null is necessary. The BoundedGrid has the `isValid()` method to tell whether a location is null.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
// @line: 53~56
public boolean isValid(Location loc)
{
    return true;
}
```

3. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

*Answer*: The time complexity of `get()`, `put()`, `remove()` are all *O(1)*. Because hash table is used. If TreeMap were used, all the time complexity will be *O(log n)*.
+ source code:
```Java
// @file:Desktop\GridWorld\grid world\Gridworld\GridWorldCode\framework\info\gridworld\grid\UnboundedGrid.java
// @line: 66~87
public E get(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    public E put(Location loc, E obj)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    public E remove(Location loc)
    {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
```

4. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

*Answer*: In `getOccupiedLocations()` method, the result'order may be differ from each other depending on which traverse order we use in finding the locations from a TreeMap.

5. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

*Answer*: Yes. If the grid has many empty locations, using a map will be better. However, it the grid is nearly full, a two-dimensional arrya will be more suitable. Because a map has to store more things(Location, Object) than a two-dimensional array.


# Coding

Consider using a HashMap or TreeMap to implement the SparseBoundedGrid. How could you use the UnboundedGrid class to accomplish this task? Which methods of UnboundedGrid could be used without change?

*Answer*: The `get()`, `put()`, `remove()`, `getOccupiedLocations()`could be used without change. I can just modify the other methods by referencing the BoundedGrid。


| Methods | SparseGridNode version | LinkedList<OccupantInCol> version | HashMap version | TreeMap version |
| ------- | ----------------- |  | | |
| getNeighbors       |          O(c)    | O(c) | O(1) | O(log n) |
| getEmptyAdjacentLocations      | O(c) | O(c) | O(1) | O(log n) |
| getOccupiedAdjacentLocations      |    O(c)   | O(c) | O(1) | O(log n) |
| getOccupiedLocations     |     O(r + n)     | O(r + n) | O(n) | O(n) |
| get     |      O(c)        | O(c) | O(1) | O(log n) |
| put     |       O(c)   | O(c) | O(1) | O(log n)|
| remove     |       O(c)        | O(c) | O(1) | O(log n)|

Map中使用下标直接访问的时间复杂度为O(1)，Tree中获取某个值得时间复杂度为O(log n)，这是二叉树搜索的特性。
对于`getOccupiedLocations()`的复杂度，由于总共只有n个非空位置，因此复杂度应该是O(r + n)，实际中取较大的一个即可。
注意：在时间复杂度的估计中，默认ArrayList的add是非扩容的，时间复杂度为O(1)。

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

*Answer*: The time complexity of `get()` is *O(1)*. If the row and column index values are within the current array bounds, the efficiency is *O(1)*. If the array needs to be resized, the efficiency will be *O(size×size)*.
  + source code:
  ```Java
  // @file:Part5\3\UnboundedGrid2.java
  // @line: 49~57
  public E get(Location loc)
  {
      if (loc == null)
          throw new NullPointerException("loc == null");
      if (loc.getRow() >= gridSize || loc.getCol() >= gridSize)
        return null;

      return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
  }
  ```
