import static org.junit.Assert.*;
import org.junit.Test;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;


public class JumperTest {
  @Test
  public void testJumper1() {
    ActorWorld world = new ActorWorld();
    Jumper jumper = new Jumper();

    world.add(new Location(3, 0), jumper);
    jumper.act();
    assertEquals(1, jumper.getLocation().getRow());
    assertEquals(0, jumper.getLocation().getCol());
  }

  @Test
  public void testJumper2() {
    ActorWorld world = new ActorWorld();
    Jumper jumper = new Jumper();
    Rock rock = new Rock();

    world.add(new Location(2, 1), rock);
    world.add(new Location(4, 1), jumper);
    jumper.act();
    rock.act();
    assertEquals(4, jumper.getLocation().getRow());
    assertEquals(1, jumper.getLocation().getCol());
  }

  @Test
  public void testJumper3() {
    ActorWorld world = new ActorWorld();
    Jumper jumper1 = new Jumper();
    Jumper jumper2 = new Jumper();

    jumper1.setDirection(Location.EAST);
    jumper2.setDirection(Location.WEST);
    world.add(new Location(5, 0), jumper1);
    world.add(new Location(5, 4), jumper2);
    jumper1.act();
    jumper2.act();
    assertEquals(5, jumper1.getLocation().getRow());
    assertEquals(2, jumper1.getLocation().getCol());
    assertEquals(5, jumper2.getLocation().getRow());
    assertEquals(4, jumper2.getLocation().getCol());
  }
}
