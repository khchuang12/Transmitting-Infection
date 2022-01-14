/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a Movable.java file that stores a single abstract method.
 * This file is used to help simulate the Conway's Game of Life which is a
 * cellular automaton that is simple to understand but has interesting
 * properties.
*/
/**
* This interface has a single method, public abstract int[] getMove() ,
* which will be used to determine where
* Movable objects should move to.
*/
public interface Movable {
  /**
  * This method is an abstract method meaning it has no instance variables
  * and no return statements.
  * It is used to determine where Movable objects should move to
  */
  public abstract int[] getMove();
}
