Part2

1.What is the role of the instance variable sideLength?

*Answer*: The **sideLength** defines the maximum distance that a bug can move in any side of the box.
  + source code：
    ```Java
     // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
     // @line: 28
     private int sideLength;
     // @line: 45~49
     if (steps < sideLength && canMove())
     {
         move();
         steps++;
     }
    ```

2.What is the role of the instance variable steps?

*Answer*: The **steps** marks how long the bug has moved on the side of a box, used to compare with the **sideLength** to judge whether the bug can move in the next step.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
   // @line: 27
   private int steps;
   // @line: 45~55
   if (steps < sideLength && canMove())
  {
      move();
      steps++;
  }
  else
  {
      turn();
      turn();
      steps = 0;
  }
  ```

3.Why is the turn method called twice when steps becomes equal to sideLength?

*Answer*: Because the bug cannot move out the box, the bug should turn right 45 degrees and then do it again to move to an empty position so that it can move to the other side of the box.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
   // @line: 50~55
        else
        {
            turn();
            turn();
            steps = 0;
        }
  ```

4.Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

*Answer*: Because the **BoxBug** class extends the **Bug** class. The **Bug** class has defined and implemented the `move()` method. So the **BoxBug** class inherits this method and there is no need to write again in the **BoxBug** class.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\framework\info\gridworld\actor
   // @line: 71~84
   public void move()
{
    Grid<Actor> gr = getGrid();
    if (gr == null)
        return;
    Location loc = getLocation();
    Location next = loc.getAdjacentLocation(getDirection());
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
}
  ```

5.After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

*Answer*: The size of its square pattern is always the same. Because a user can only determine the sideLength when constructing a **BoxBug**. And the **BoxBug** class does not provide a method like `setSideLength()` to modify the **sideLength** value. So once the **sideLength** is determined by the constructor, it cannot be modified.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
   // @line: 34~38
   public BoxBug(int length)
   {
        steps = 0;
        sideLength = length;
   }
  ```

6.Can the path a BoxBug travels ever change? Why or why not?

*Answer*: Yes. If I can place a rock on the path of the bug, the bug will change its direction and so as the path. However, if no other things is added, the path of a BoxBug will not change.
  + source code:
  ```Java
  @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
  // @line: 50~5
  else
       {
           turn();
           turn();
           steps = 0;
       }
  ```
7.When will the value of steps be zero?

*Answer*: When the bug face an edge or the bug has to change direction to move to the other side of the box, the value of steps will be set to zero. Of course, in the **BoxBug** constructor the value of steps will be also set to zero.
+ source code：
  ```Java
   // @file:Desktop\GridWorld\Part1\grid world\Gridworld\GridWorldCode\projects\boxBug
   // @line: 36
      steps = 0;
  // @line: 54
      steps = 0;
  ```
