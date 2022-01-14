/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a CellMoveDiagonal.java file that stores extends to the parent
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
* this class takes in CellMoveDiagonal objects and uses their attributes to
* describe a situation in the game where the cell moves diagonal
* @param orientedRight boolean if cell is oriented right
* @param diagonalMoves number of diagonal move as an integer
*/
public class CellMoveDiagonal extends CellMoveUp{
  public boolean orientedRight;
  public int diagonalMoves;
  /**
   * This is the constructor for the CellMoveDiagonal subclass.
   * it Invokes the parent class's constructor to initialize all instance
   * variables with the values passed in as arguments.
   * @param orientedRight boolean if cell is oriented right
   * @param diagonalMoves number of diagonal move as an integer
   * @param currRow stores the current row as an integer
   * @param currCol store the current column as an integer
   * @param mass stores the mass of the cell as an integer
   * @return no return statement in constructor
  */
  public CellMoveDiagonal(int currRow, int currCol, int mass){
    super(currRow, currCol, mass);
    this.orientedRight = true;
    this.diagonalMoves = 0;
  }
  /**
   * This is the copy constructor for the CellMoveDiagonal subclass.
   * it can be assumed that the argument is non- null
   * it will Invoke the parent class's copy constructor to initialize all
   * instance variables with the instance variables of
   * the otherCellMoveDiagonal object passed in as an argument.
   * @param orientedRight boolean if cell is oriented right
   * @param diagonalMoves number of diagonal move as an integer
   * @param otherCellMoveDiagonal CellMoveDiagonal object that is being copied
   * @return no return statement in constructor
  */
  public CellMoveDiagonal(CellMoveDiagonal otherCellMoveDiagonal){
    super(otherCellMoveDiagonal);
    this.orientedRight = otherCellMoveDiagonal.orientedRight;
    this.diagonalMoves = otherCellMoveDiagonal.diagonalMoves;
  }
  /**
  * the String representation of the current object. Each class will have a
  * different representation, but each representation will be a single
  * character. dependent on whether it is oriented right or not
  * @param orientedRight boolean if cell is oriented right
  * @return returns "/" or "\" as the string representation of CellMoveDiagonal
  */
  public String toString(){
    if(this.orientedRight){
      return "/";
    }
    return "\\";
  }
  /**
  * Return true or false based on neighbors depending on the conditions for
  * apoptosis. This method does NOT call apoptosis() . This method is only
  * for checking whether apoptosis() should be called later.
  * Checks for whether this cell has greater than 3 neighbors.
  * @param CMDneighborMin CellMoveDiagonal minimum number of neighbors
  * to be true
  * @param neighbors list of neighbors around the cell
  * @return boolean based on neighbors depending on the conditions for
  * apoptosis
  */
  public boolean checkApoptosis(List<Cell> neighbors){
    int CMDneighborMin = 3;
    if(neighbors.size()>CMDneighborMin){
      return true;
    }
    return false;
  }
}
