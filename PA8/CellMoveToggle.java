/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a CellMoveToggle.java file that stores extends to the parent
 * CellMoveUp class. The goal of this is to apply
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
* this class takes in CellMoveToggle objects and uses their attributes to
* describe a situation in the game where the cell moves toggle
* @param toggled  if the cell is currently "toggled" or not.
*/
public class CellMoveToggle extends CellMoveUp {
  public boolean toggled;
  /**
   * This is the constructor for the CellMoveToggle subclass.
   * it Invokes the parent class's constructor to initialize all instance
   * variables with the values passed in as arguments.
   * @param toggled  if the cell is currently "toggled" or not.
   * @param currRow stores the current row as an integer
   * @param currCol store the current column as an integer
   * @param mass stores the mass of the cell as an integer
   * @return no return statement in constructor
  */
  public CellMoveToggle(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    this.toggled = true;
  }
  /**
   * This is the copy constructor for the CellMoveToggle subclass.
   * it can be assumed that the argument is non- null
   * it will Invoke the parent class's copy constructor to initialize all
   * instance variables with the instance variables of
   * the otherCellMoveToggle object passed in as an argument.
   * @param toggled  if the cell is currently "toggled" or not.
   * @param otherCellMoveToggle CellMoveToggle object that is being copied
   * @return no return statement in constructor
  */
  public CellMoveToggle(CellMoveToggle otherCellMoveToggle){
    super(otherCellMoveToggle);
    this.toggled = otherCellMoveToggle.toggled;
  }
  /**
  * the String representation of the current object. Each class will have a
  * different representation, but each representation will be a single
  * character. dependent on whether it is  toggled or not
  * @param toggled  if the cell is currently "toggled" or not.
  * @return returns "T" or "t" as the string representation of CellMoveUp
  */
  public String toString(){
    if(this.toggled==true){
      return "T";
    }
    return "t";
  }
  /**
  * Return true or false based on neighbors depending on the conditions for
  * apoptosis. This method does NOT call apoptosis() . This method is only
  * for checking whether apoptosis() should be called later.
  * Checks for whether this cell has fewer than 2 or greater than 5 neighbors
  * @param neighborsMax max number of neighbors to be true
  * @param neighborsMin min number of neighbors to be true
  * @param neighbors list of neighbors around the cell
  * @return boolean based on neighbors depending on the conditions for
  * apoptosis
  */
  public boolean checkApoptosis(List<Cell> neighbors){
    int neighborsMin = 5;
    int neighborsMax = 2;
    if(neighbors.size()<neighborsMax||neighbors.size()>neighborsMin){
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
    Cell cellCopy = new CellMoveToggle(this.currRow, this.currCol, this.mass);
    return cellCopy;
  }
  /**
  * This method defines how the cells that are capable of moving
  * will move around the petri dish. Return an
  * int[] of length 2 that represents the intended new position of
  * the cell, where the first element in the array is the row and the
  * second element in the array is the column of the new position
  * @param getMoveCell integer array of intended move location
  * @param toggled if the cell is currently "toggled" or not.
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @return getMoveCell Cell of new position
  */
  public int[] getMove(){
    int[] getMoveCell = new int[2];
    // if toggled move up
    if(this.toggled == true){
      getMoveCell[0] = this.currRow-1;
      getMoveCell[1] = this.currCol;
    } else{
    // stay stationary
      getMoveCell[0] = this.currRow;
      getMoveCell[1] = this.currCol;
    }
    //change toggled
    this.toggled = !toggled;
    return getMoveCell;
  }
}
