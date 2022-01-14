/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a Divisible.java file that stores a single abstract method.
 * This file is used to help simulate the Conway's Game of Life which is a
 * cellular automaton that is simple to understand but has interesting
 * properties.
*/
/**
* This interface has a single method, public abstract int[] getDivision()
* which will be used to determine where
* Divisible objects should divide to.
*/
public interface Divisible{
  /**
  * This method is an abstract method meaning it has no instance variables
  * and no return statements.
  * It is used to determine where divisible objects should move to
  */
  public abstract int[] getDivision();
}
