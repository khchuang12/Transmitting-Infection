/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a CellDivide.java file that stores extends to the parent
 * Cell class. The goal of this is to apply
 * inheritance to defining the relationship among different types of cells.
 * This file is used to help simulate the Conway's Game of Life which is a
 * cellular automaton that is simple to understand but has interesting
 * properties.
*/
import java.util.*;
/**
* In each class there will be two constructors (one taking the three int
* parameters in the order specified here and one being a copy constructor
* for the current class), override the toString() method, and override the
* checkApoptosis() method. It adjusts the method parameter
* types (specifically for the constructors) appropriately for each class.
* this class takes in CellDivide objects and uses their attributes to
* describe a situation in the game where the cell is divided
* @param direction direction the cell is divided
*/
public class CellDivide extends Cell implements Divisible{
  public int direction;
  /**
   * This is the constructor for the CellDivide subclass.
   * it Invokes the parent class's constructor to initialize all instance
   * variables with the values passed in as arguments.
   * @param direction direction the cell is divided
   * @param currRow stores the current row as an integer
   * @param currCol store the current column as an integer
   * @param mass stores the mass of the cell as an integer
   * @return no return statement in constructor
  */
  public CellDivide(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    this.direction = 1;
  }
  /**
   * This is the copy constructor for the CellDivide subclass.
   * it can be assumed that the argument is non- null
   * it will Invoke the parent class's copy constructor to initialize all
   * instance variables with the instance variables of
   * the otherCellDivide object passed in as an argument.
   * @param otherCellDivide CellDivide object that is being copied
   * @return no return statement in constructor
  */
  public CellDivide(CellDivide otherCellDivide){
    super(otherCellDivide);
    this.direction = otherCellDivide.direction;
  }
  /**
  * the String representation of the current object. Each class will have a
  * different representation, but each representation will be a single
  * character.
  * @return returns "+" as the string representation of CellDivide
  */
  public String toString(){
    return "+";
  }
  /**
  * Return true or false based on neighbors depending on the conditions for
  * apoptosis. This method does NOT call apoptosis() . This method is only
  * for checking whether apoptosis() should be called later.
  * Checks for whether this cell has  3 neighbors.
  * @param numNeighbors condition of number of neighbors
  * @param neighbors list of neighbors around the cell
  * @return boolean based on neighbors depending on the conditions for
  * apoptosis
  */
  public boolean checkApoptosis(List<Cell> neighbors){
    int numNeighbors = 3;
    if(neighbors.size()==numNeighbors){
      return true;
    }
    return false;
  }
  /**
  * In this method, simply return a deep copy of the calling object.
  * @param cellCopy copy of Cell
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @param mass stores the mass of the cell as an integer
  * @return cellCopy as a cell
  */
  public Cell newCellCopy(){
    Cell cellCopy = new CellDivide(this.currRow, this.currCol, this.mass);
    return cellCopy;
  }
  /**
  * This method defines where a new cell that is divided from
  * (spawned by) the original calling cell will be
  * placed. It will return an int[] that represents the intended
  * position of the spawned cell, where the first
  * element in the array is the row and the second element in the
  * array is the column of the intended position of the spawned cell.
  * @param getDivCell integer array representing location of the divided cell
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @param direction direction the cell is divided
  * @return getDivCell new position based on direction
  */
  public int[] getDivision(){
    int[] getDivCell = new int[2];
    // down
    if(this.direction == 0){
      getDivCell[0] = this.currRow+1;
      getDivCell[1] = this.currCol;
    // up
    } else if(this.direction == 1){
      getDivCell[0] = this.currRow-1;
      getDivCell[1] = this.currCol;
    //left
    } else if(this.direction == 2){
      getDivCell[0] = this.currRow;
      getDivCell[1] = this.currCol-1;
    // right
    } else if(this.direction == 3){
      getDivCell[0] = this.currRow;
      getDivCell[1] = this.currCol+1;
    }
    return getDivCell;
  }
}
