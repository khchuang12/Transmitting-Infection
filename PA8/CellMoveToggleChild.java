/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a CellMoveToggleChild.java file that stores extends to
 * the parent CellMoveToggle class. The goal of this is to apply
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
* this class takes in CellMoveToggleChild objects and uses their attributes to
* describe a situation in the game where the cell moves toggle child
* @param numAlive number of cells alive
*/
public class CellMoveToggleChild extends CellMoveToggle{
  public static int numAlive = 0;
  /**
   * This is the constructor for the CellMoveDiagonal subclass.
   * it Invokes the parent class's constructor to initialize all instance
   * variables with the values passed in as arguments. It increases the value
   * of numAlive everytime it is used
   * @param numAlive number of cells alive
   * @param currRow stores the current row as an integer
   * @param currCol store the current column as an integer
   * @param mass stores the mass of the cell as an integer
   * @return no return statement in constructor
  */
  public CellMoveToggleChild(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    this.numAlive++;
  }
  /**
   * This is the copy constructor for the CellMoveToggleChild subclass.
   * it can be assumed that the argument is non- null
   * it will Invoke the parent class's copy constructor to initialize all
   * instance variables with the instance variables of
   * the otherCellMoveToggleChild object passed in as an argument. It
   * increases the value of numAlive everytime it is used
   * @param numAlive number of cells alive
   * @param otherCellMoveToggleChild CellMoveToggleChild object that is
   * being copied
   * @return no return statement in constructor
  */
  public CellMoveToggleChild(CellMoveToggleChild otherCellMoveToggleChild){
    super(otherCellMoveToggleChild);
    this.numAlive++;
  }
  /**
  * the String representation of the current object. Each class will have a
  * different representation, but each representation will be a single
  * character.
  * @return returns its parent's toString CellMoveToggle
  */
  public String toString(){
    return super.toString();
  }
  /**
  * Return true or false based on neighbors depending on the conditions for
  * apoptosis. This method does NOT call apoptosis() . This method is only
  * for checking whether apoptosis() should be called later.
  * Checks for whether CellMoveToggle 's conditions for apoptosis are
  * satisfied AND there are fewer than 10 CellMoveToggleChild s alive.
  * @param maxAlive max number of child alive to return true
  * @param numAlive number of cells alive
  * @param neighbors list of neighbors around the cell
  * @return boolean based on neighbors depending on the conditions for
  * apoptosis
  */
  public boolean checkApoptosis(List<Cell> neighbors){
    int maxAlive = 10;
    if(super.checkApoptosis(neighbors)==true&&numAlive<maxAlive){
      return true;
    }
    return false;
  }
  /**
  * This method is a void apoptosis method that overrides the apoptosis
  * method from the Cell.java file and class. It calls the apoptosis method
  * and overrides it as well as decrements the numAlive variable
  * @param numAlive number of cells alive
  * @return no return for void methods
  */
  public void apoptosis(){
    super.apoptosis();
    this.numAlive--;
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
    Cell cellCopy = new CellMoveToggleChild
    (this.currRow, this.currCol, this.mass);
    return cellCopy;
  }
  /**
  * This method defines how the cells that are capable of moving
  * will move around the petri dish. Return an
  * int[] of length 2 that represents the intended new position of
  * the cell, where the first element in the array is the row and the
  * second element in the array is the column of the new position
  * It calls upon its parent getMove method.
  * @param getMoveCell integer array of intended move location
  * @param toggled if the cell is currently "toggled" or not.
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @return getMoveCell Cell of new position
  */
  public int[] getMove(){
    return super.getMove();
  }
}
