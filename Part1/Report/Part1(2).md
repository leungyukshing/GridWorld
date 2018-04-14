1.

| Degress | Compass Direction |
| ------- | ----------------- |
| 0       | North             |
| 45      | Northeast         |
| 90      | East              |
| 135     | Southeast         |
| 180     | South             |
| 225     | Southwest         |
| 270     | West              |
| 315     | Northwest         |
| 360     | North             |

1. Move a bug to a different location using the moveTo method. In which directions can you move it? How far can you move it? What happens if you try to move the bug outside the grid?
*Answer*: I can use the `moveTo()` method to move a bug to any location that both is in the grid and is empty. If I try to move the bug outside the grid, the system may throw `java.lang.illegalArgumentException`.

2. Change the color of a bug, a flower, and a rock. Which method did you use?
*Answer*: Each of these actor has a `setColor()` method, which can change the color itself.

3. Move a rock on top of a bug and then move the rock again. What happened to the bug?
*Answer*: When moving a rock on top of a bug, the bug is covered and I can only see the rock in the grid. After moving the rock to another grid, the bug is not in the position where it used to be. That means the bug is crushed by the rock.
