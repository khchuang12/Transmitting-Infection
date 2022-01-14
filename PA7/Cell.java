/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a Cell.java file that stores the parent information for
 * the rest of the files in the game. The goal of this is to apply
 * inheritance to defining the relationship among different types of cells.
 * This file is used to help simulate the Conway's Game of Life which is a
 * cellular automaton that is simple to understand but has interesting
 * properties.
*/
import java.util.*;
/**
* This Cell class is used as the parent class for the rest of the classes
* in this game. It contains intance variables to define position and mass
* of the pieces in the game. It contains a constructor method to be
* initialized later on in the constructor. It defines the attributes of a
* cell for the game.
* @param currRow stores the current row as an integer
* @param currCol store the current column as an integer
* @param mass stores the mass of the cell as an integer
*/
public abstract class Cell{
  public int currRow;
  public int currCol;
  public int mass;
/**
* This is the constructor for Cell. It Initializes all instance variables
* with the values passed in as arguments.If an argument would make the
* appropriate instance variable invalid, it sets the appropriate instance
* variable to 0 instead.It is an abstract class that nees the constructor
* because all subclasses should use this constructor to initialize the
* instance variables inherited from Cell .
* @param currRow stores the current row as an integer
* @param currCol store the current column as an integer
* @param mass stores the mass of the cell as an integer
* @param 0 checks the validity of the input if it is less that 0
* @return no return statement in constructor
*/
  public Cell(int currRow, int currCol, int mass){
    if(currRow<0){
      this.currRow = 0;
    }else{
      this.currRow = currRow;
    }
    if(currCol<0){
      this.currCol = 0;
    }else{
      this.currCol = currCol;
    }
    if(mass<0){
      this.currCol = 0;
    }else{
      this.mass = mass;
    }
  }
  /**
  * This is a copy constructor for Cell. You can assume that otherCell
  * is non- null (usually, if it were null , we would handle it by throwing a
  * NullPointerException ).Initializes all instance variables with the
  * instance variables of the Cell object passed in as an argument.
  * Even though Cell is an abstract class, we need this copy constructor
  * because all subclasses should use this
  * copy constructor to copy the instance variables inherited from Cell .
  * @param otherCell a Cell object
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @param mass stores the mass of the cell as an integer
  * @param newCurrRow stores the current row of otherCell as an integer
  * @param newCurrCol store the current column of otherCell as an integer
  * @param newMass stores the mass of the cell of otherCell as an integer
  * @return no return statement in constructor
  */
  public Cell(Cell otherCell){
    int newCurrRow = otherCell.getCurrRow();
    int newCurrCol = otherCell.getCurrCol();
    int newMass = otherCell.getMass();
    this.currRow = newCurrRow;
    this.currCol = newCurrCol;
    this.mass = newMass;
  }
  /**
  * This method will be called on a Cell when apoptosis happens.
  * Sets currRow , currCol , and mass to -1 to indicate cell death.
  * @param currRow stores the current row as an integer
  * @param currCol store the current column as an integer
  * @param mass stores the mass of the cell as an integer
  * @return no return statement in void
  */
  public void apoptosis(){
    this.currRow = -1;
    this.currCol = -1;
    this.mass = -1;
  }
  /**
  * This getter method returns the current value of the appropriate instance
  * variable and in this case it is the currRow
  * @param currRow stores the current row as an integer
  * @return no return statement in getter method
  */
  public int getCurrRow(){
    return this.currRow;
  }
  /**
  * This getter method returns the current value of the appropriate instance
  * variable and in this case it is the currCol
  * @param currCol stores the current column as an integer
  * @return no return statement in getter method
  */
  public int getCurrCol(){
    return this.currCol;
  }
  /**
  * This getter method returns the current value of the appropriate instance
  * variable and in this case it is the getMass
  * @param mass stores the mass of the cell as an integer
  * @return no return statement in getter method
  */
  public int getMass(){
    return this.mass;
  }
  /**
  * This setter method sets the current value of the appropriate instance
  * variable to the parameter given and returns a boolean dependent on
  * whether it successfully set it or not
  * @param currRow stores the current row of the cell as an integer
  * @param newRow the value that will be set to currRow
  * @param 0 checks the validity of the input if it is less that 0
  * @return boolean whether the variable was set properly or not
  */
  public boolean setCurrRow(int newRow){
    if(newRow<0){
      return false;
    }
    this.currRow = newRow;
    return true;
  }
  /**
  * This setter method sets the current value of the appropriate instance
  * variable to the parameter given and returns a boolean dependent on
  * whether it successfully set it or not
  * @param newCol the value that will be set to currCol
  * @param currCol stores the current column of the cell as an integer
  * @param 0 checks the validity of the input if it is less that 0
  * @return boolean whether the variable was set properly or not
  */
  public boolean setCurrCol(int newCol){
    if(newCol<0){
      return false;
    }
    this.currCol = newCol;
    return true;
  }
  /**
  * This setter method sets the current value of the appropriate instance
  * variable to the parameter given and returns a boolean dependent on
  * whether it successfully set it or not
  * @param newMass the value that will be set to mass
  * @param mass stores the mass of the cell as an integer
  * @param 0 checks the validity of the input if it is less that 0
  * @return boolean whether the variable was set properly or not
  */
  public boolean setMass(int newMass){
    if(newMass<0){
      return false;
    }
    this.mass = newMass;
    return true;
  }
  /**
  * Given a List of Cell s, determine if this cell should initiate
  * apoptosis or not. Return true if the condition
  * being checked for is satisfied and false otherwise (more details below).
  * This is an abstract method that each concrete subclass will implement
  * with its own behavior.
  */
  public abstract boolean checkApoptosis(List<Cell> neighbors);
}
