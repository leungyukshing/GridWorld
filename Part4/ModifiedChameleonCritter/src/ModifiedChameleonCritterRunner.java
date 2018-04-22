import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import java.awt.Color;

public final class ModifiedChameleonCritterRunner {
    private ModifiedChameleonCritterRunner() {}
  public static void main(String[] args) {
    ActorWorld world = new ActorWorld();
    ModifiedChameleonCritter alice = new ModifiedChameleonCritter();
    alice.setColor(Color.ORANGE);
    ModifiedChameleonCritter bob = new ModifiedChameleonCritter();
    bob.setColor(Color.GREEN);
    Bug bug1 = new Bug();
    bug1.setColor(Color.RED);
    Bug bug2 = new Bug();
    bug2.setColor(Color.PINK);

    // condition1
    // The Critter will change color to bug1 or bug2
    world.add(new Location(3, 2), alice);
    world.add(new Location(3, 3), bug1);
    world.add(new Location(3, 1), bug2);

    // condition2
    // The Critter will darken
    world.add(new Location(5, 5), bob);

    world.show();
  }
}