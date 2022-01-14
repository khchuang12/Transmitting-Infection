/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This file is a PetriDish.java file that contains a board that holds all
 * cells growing on the petri dish. It is used to simulate the Conway's Game
 * of Life and calls upon the other classes without extending them, in order
 * to play the game of life. It works in conjunction with the other classes
 * to fully simulate a moving game.This file is used to help simulate the
 * Conway's Game of Life which is a
 * cellular automaton that is simple to understand but has interesting
 * properties.
*/
import java.util.*;
/**
* In this class there is a constructor method that initializes all of the
* string board info, into the dish cell object. It is used to construct the
* game board that will be used for the game.
* @param dish Cell object of a 2D array representing the PetriDish
*/
public class PetriDish {
  public Cell[][] dish;
  /**
  * This is the constructor of the PetriDish class.
  * board is a 2D array of Strings representing what cells the dish
  * should be filled with.
  * Each String is the string "null" if that position in the petri
  * dish is an "empty space" and is of the format "
  * {CELL_TYPE} {MASS}" for "alive cells."
  * Populate each position of the instance variable dish with references
  * to unique objects corresponding to the
  * specific types and masses denoted in the 2D String array.
  * Assume that board is always valid and only contains object types specified in this writeup
  * @param dish Cell object of a 2D array representing the PetriDish
  * @param board String 2D array representing the before value of PetriDish
  * @param input string input from board
  * @param boardInfo the input string split into individual strings
  * @param cellType type of cell
  * @param cellMass mass of cell
  * @return no return because of constructor method
  */

  public PetriDish(String[][] board){
    dish = new Cell[board.length][board[0].length];
// loops through 2D array
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
// breaks up string input from 2D array into string that can be used
        String input = board[i][j];
        String [] boardInfo = input.split(" ");
        String cellType = boardInfo[0];
        int cellMass = Integer.parseInt(boardInfo[1]);
  // deals with different cases dependent on the cellType
        if(cellType =="CellStationary"){
          dish[i][j] = new CellStationary(i,j,cellMass);
        }else if(cellType =="CellDivide"){
          dish[i][j] = new CellDivide(i,j,cellMass);
        }else if(cellType =="CellMoveUp"){
          dish[i][j] = new CellMoveUp(i,j,cellMass);
        }else if(cellType =="CellMoveToggle"){
          dish[i][j] = new CellMoveToggle(i,j,cellMass);
        }else if(cellType =="CellMoveDiagonal"){
          dish[i][j] = new CellMoveDiagonal(i,j,cellMass);
        }else if (cellType =="CellMoveToggleChild"){
          dish[i][j] = new CellMoveToggleChild(i,j,cellMass);
        }
      }
    }
  }
}
