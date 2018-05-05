package info.gridworld.maze;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;
	private Location last;
	private boolean isEnd = false;
	private Stack<ArrayList<Location>> crossLocation = new Stack<ArrayList<Location>>();
	private Integer stepCount = 0;
	private boolean hasShown = false;//final message has been shown
	
	private int []probability = new int[4]; // 0 for North, 1 for East, 2 for South, 3 for West

	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
		next = null;
		// Initialize the stack
		crossLocation = new Stack<ArrayList<Location>>();
		
		// Initialize the probability
		for (int i = 0; i < 4; i++) {
			probability[i] = 1;
		}
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		if (stepCount == 0) {
			ArrayList<Location> parent = new ArrayList<Location>();
			parent.add(getLocation());
			crossLocation.push(parent);
		}
		boolean willMove = canMove();
		if (isEnd) {
		//to show step count when reach the goal		
			if (!hasShown) {
				String msg = "Total: " + stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
		    last = getLocation();
			move();
			//increase step count when move 
			stepCount++;
			
			// If a position can move, put it into its parent path
			ArrayList<Location> parent = crossLocation.pop();
			parent.add(getLocation());
			crossLocation.push(parent);
						
			// Save the present locaiton as a new node
			ArrayList<Location>  present = new ArrayList<Location>();
			present.add(getLocation());
			crossLocation.push(present);
		}
		else {
			// Move to the last location
		      if (!crossLocation.empty()) {
		        // pop the position that can't move
		        crossLocation.pop();
		        if (!crossLocation.empty()) {
		          Grid<Actor> grid = getGrid();
		          if (grid == null) {
		            return;
		          }
		          ArrayList<Location> parent = crossLocation.peek();
		          Location parentLocation = parent.get(0);

		          // Set the bug direction, same as the move direction
		          int dir = getLocation().getDirectionToward(parentLocation);
		          setDirection(dir);
		          
		          // Update probability
		          int temp = dir/90;
		          updateProbability(temp);
		          
		          last = getLocation();
		          next = parentLocation;
		          
		          move();
		          stepCount++;
		        }
		      }
		}
	}
	private void updateProbability(int temp) {
		if (temp == 0) {
		    probability[2]--;
		}
		else if (temp == 1) {
		    probability[3]--;
		}
		else if (temp == 2) {
		    probability[0]--;
		}
		else {
		    probability[1]--;
		}
	}
	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> grid = getGrid();
		if (grid == null) {
			return null;
		}
		ArrayList<Location> validLocations = new ArrayList<Location>();

	    // Search four neighboring positions
	    for (int i = 0; i < 4; i++) {
	      Location nextLoc = loc.getAdjacentLocation(getDirection() + i * 90); // North, East, South, West
	      
	      if (grid.isValid(nextLoc)) {
	        Actor actor = grid.get(nextLoc);

	        // Next location is end point
	        if (actor instanceof Rock && actor.getColor().equals(Color.RED)) {
	          isEnd = true;
	          validLocations.add(nextLoc);
	          
	          Location present = getLocation();
	          
	          // Set the bug direction, same as the move direction
	          int dir = getLocation().getDirectionToward(nextLoc);
	          setDirection(dir);
	          
	          moveTo(nextLoc);
	          
	          Flower flower = new Flower(getColor());
	  		  flower.putSelfInGrid(grid, present);
	  		  setColor(Color.RED);
	          
	          break;
	        }
	        
	        // Next location is empty
	        else if (actor == null) {
	          validLocations.add(nextLoc);
	        }
	      }
	    }
	    return validLocations;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		Grid<Actor> grid = getGrid();
	    if (grid == null) {
	      return false;
	    }
	    // Get the valid next locaitons of the present location
	    ArrayList<Location> nextLocations = getValid(getLocation());
	    // No valid next location to move
	    if (nextLocations.size() == 0) {
	      return false;
	    }
	    else {
	    	ArrayList<Location> temp = new ArrayList<Location>();
	      for (int i = 0; i < nextLocations.size(); i++) {
	        // If the actor is a rock, that is the end point
	        if (grid.get(nextLocations.get(i)) instanceof Rock) {
	          next = nextLocations.get(i);
	          return true;
	        }
	        // Choose the next location randomly
	        else {
	          temp.add(nextLocations.get(i));
	        }
	      }
	      next = findBestLocation(temp);
	      return true;
	    }
	}
	
	private Location findBestLocation(ArrayList<Location> temp) {
		Location bestLocation = null;
		int maxProbability = 0;
		int dirToChoose = 0;
		int indexToChoose = 0;
		
		// Only one
		if (temp.size() == 1) {
			int dir = getLocation().getDirectionToward(temp.get(0));
			probability[dir/90]++;
			bestLocation = temp.get(0);
		}
		// Many Location to choose
		else {
			boolean haveNorth = false;
			boolean haveEast = false;
			boolean haveSouth = false;
			boolean haveWest = false;
			
			Location []four = new Location[4];
			for (int i = 0; i < temp.size(); i++) {
				int dir = getLocation().getDirectionToward(temp.get(i));
				// mark each direction
				switch (dir) {
				case 0: haveNorth = true;
						four[0] = temp.get(i);
						break;
				case 1: haveEast = true;
						four[1] = temp.get(i);
						break;
				case 2: haveSouth = true;
						four[2] = temp.get(i);
						break;
				case 3: haveWest = true;
						four[3] = temp.get(i);
						break;
				default:
					break;
				}
			}
			
			// Randomly choose
			int sum = 0;
			for (int i = 0 ; i < 4; i++) {
				sum += probability[i];
			}

			int random = (int) (Math.random() * sum);

			// Choose North
			if  (random >= 0 && random < probability[0]) {
				dirToChoose = 0;
				if (haveNorth) {
					bestLocation = four[0];
					probability[0]++;
				}
			}
			// Choose East
			else if (random >= probability[0] && random < (probability[0] + probability[1])) {
				dirToChoose = 1;
				if (haveEast) {
					bestLocation = four[1];
					probability[1]++;
				}
			}
			// Choose South
			else if (random >= (probability[0] + probability[1]) && random < (probability[0] + probability[1] + probability[2])) {
				dirToChoose = 2;
				if (haveSouth) {
					bestLocation = four[2];
					probability[2]++;
				}
			}
			// Choose West
			else if (random >= (probability[0] + probability[1] + probability[2]) && random < sum) {
				dirToChoose = 3;
				if (haveWest) {
					bestLocation = four[3];
					probability[3]++;
				}
			}
		}
		
		/* not good */
		if (bestLocation == null) {
			for (int i = 0; i < temp.size(); i++) {
				int dir = getLocation().getDirectionToward(temp.get(i));
				if (probability[dir/90] > maxProbability) {
					maxProbability = probability[dir/90];
					dirToChoose = dir/90;
					indexToChoose = i;
				}
			}
			bestLocation = temp.get(indexToChoose);
			probability[dirToChoose]++;
		}
		return bestLocation;
	}
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		last = getLocation();
		if (gr.isValid(next)) {
			int dir = getLocation().getDirectionToward(next);
			setDirection(dir);
			probability[dir/90]++;
			moveTo(next);
		} else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, last);
	}
}
