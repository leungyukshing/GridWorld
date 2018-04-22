/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Cay Horstmann
 */


import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;

import java.awt.Color;

/**
 * A <code>Jumper</code> is an actor that can move and turn. It drops flowers as
 * it moves. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class Jumper extends Actor
{
    /**
     * Constructs a red Jumper.
     */
    public Jumper()
    {
        setColor(Color.RED);
    }

    /**
     * Constructs a Jumper of a given color.
     * @param JumperColor the color for this Jumper
     */
    public Jumper(Color jumperColor)
    {
        setColor(jumperColor);
    }

    /**
     * Moves if it can move, turns otherwise.
     */
    public void act()
    {
        if (canMove()) {
            move();
        }
        else {
            turn();
        }
    }

    /**
     * Turns the Jumper 45 degrees to the right without changing its location.
     */
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }

    /**
     * Moves the Jumper forward, putting a flower into the location it previously
     * occupied.
     */
    public void move()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return;
        }
        // Find the location that adjacent to the current location
        Location loc = getLocation();
        // Find the location that the jumper supposed to jump into
        Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
        if (gr.isValid(next)) {
            moveTo(next);
        }
        else {
            removeSelfFromGrid();
        }
    }

    /**
     * Tests whether this Jumper can move forward into a location that is empty or
     * contains a flower.
     * @return true if this Jumper can move.
     */
    public boolean canMove()
    {
        Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location loc = getLocation();
        Location next = (loc.getAdjacentLocation(getDirection())).getAdjacentLocation(getDirection());
        // Test whethet the next location is valid
        if (!gr.isValid(next)) {
            return false;
        }
        Actor neighbor = gr.get(next);
        return (neighbor == null) || (neighbor instanceof Flower) || (neighbor instanceof Bug);
        // ok to move into empty location or onto flower or onto a bug
        // not ok to move onto any other actor
    }
}
