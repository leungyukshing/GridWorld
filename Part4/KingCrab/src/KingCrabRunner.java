import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public final class KingCrabRunner
{
    private KingCrabRunner() {}
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        // test whether the actors in the specifid position wil be push away.
        world.add(new Location(3, 4), new Bug());
        world.add(new Location(4, 4), new KingCrab());
        world.add(new Location(5, 4), new Rock());
        world.add(new Location(4, 3), new Flower());
        world.add(new Location(4,5 ), new Rock());
        world.show();
    }
}