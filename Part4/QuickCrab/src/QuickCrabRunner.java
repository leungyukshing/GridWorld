import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class QuickCrabRunner
{
    private QuickCrabRunner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        // condition 1
        QuickCrab crab = new QuickCrab();
        crab.setDirection(Location.EAST);
        world.add(new Location(2, 1), new Rock());
        world.add(new Location(4, 1), crab);

        // condition 2
        world.add(new Location(3, 0), new Flower());
        world.add(new Location(5, 0), new QuickCrab());

        // condition 3
        QuickCrab crab2 = new QuickCrab();
        crab2.setDirection(Location.SOUTH);
        world.add(new Location(3, 4), new QuickCrab());
        world.add(new Location(4, 4), crab2);
        
        world.show();
    }
}