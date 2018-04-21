import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;

public class ChameleonKidRunner {
  public static void main(String[] args) {
    ActorWorld world = new ActorWorld();
    ChameleonKid alice = new ChameleonKid();
    alice.setColor(Color.ORANGE);
    ChameleonKid bob = new ChameleonKid();
    bob.setColor(Color.GREEN);
    Bug bug1 = new Bug();
    bug1.setColor(Color.RED);
    Bug bug2 = new Bug();
    bug2.setColor(Color.PINK);

    // condition1
    world.add(new Location(3, 2), alice);
    world.add(new Location(2, 2), bug1);
    world.add(new Location(4, 2), bug2);

    // condition2
    Rock rock = new Rock();
    rock.setColor(Color.BLACK);
    world.add(new Location(5, 5), bob);
    world.add(new Location(6, 5), rock);

    world.show();
  }
}