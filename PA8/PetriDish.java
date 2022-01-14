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
* @param newDish copy of dish with moved Cells
* @param movables list of Movables cells
* @param divisibles list of Divisible cells
*/
public class PetriDish {
  public Cell[][] dish;
  public List<Movable> movables;
  public List<Divisible> divisibles;
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
    this.movables = new ArrayList<Movable>();
    this.divisibles = new ArrayList<Divisible>();
    for(int i=0;i<board.length;i++){
      for(int j=0;j<board[i].length;j++){
// breaks up string input from 2D array into string that can be used
        String input = board[i][j];

        if(input.equals("null")){
          dish[i][j] = null;
        }else{
          String [] boardInfo = input.split(" ");
          String cellType = boardInfo[0];
          int cellMass = Integer.parseInt(boardInfo[1]);
          switch(cellType) {
            case "CellStationary":
              dish[i][j] = new CellStationary(i, j, cellMass);
              break;
            case "CellDivide":
              dish[i][j] = new CellDivide(i, j, cellMass);
              break;
            case "CellMoveUp":
              dish[i][j] = new CellMoveUp(i, j, cellMass);
              break;
            case "CellMoveToggle":
              dish[i][j] = new CellMoveToggle(i, j, cellMass);
              break;
            case "CellMoveDiagonal":
              dish[i][j] = new CellMoveDiagonal(i, j, cellMass);
              break;
            case "CellMoveToggleChild":
              dish[i][j] = new CellMoveToggleChild(i, j, cellMass);
              break;
        }
        //repopulates movables
        if(dish[i][j] instanceof Movable){
          movables.add((Movable)dish[i][j]);
        }
        //repopulates divisibles
        if(dish[i][j] instanceof Divisible){
          divisibles.add((Divisible)dish[i][j]);
        }
        }
      }
    }
  }
  /**
  * This is a helper method used to wrap the positions of cells
  * to ensure none of their values are out of bounds
  * @param r the row taken from the getMove/getDivision method
  * @param c the column taken from the getMove/getDivision method
  * @param pos integer array of length 2 representing the wrapped position
  * @return pos integer array of length 2 representing the wrapped position
  */
  public int[] handleWrapping(int r, int c){
    int[] pos = new int[2];
    //if cell goes up too far
    if(r<0){
      pos[0]=dish.length-1;
      // if cell goes down to far
    }else if(r>dish.length-1){
      pos[0]=0;
    }else{
      pos[0]=r;
    }
    //if cell goes left too far
    if(c<0){
      pos[1]=dish[0].length-1;
    // if cell goes right too far
    }else if(c>dish[0].length-1){
      pos[1]=0;
    }else{
      pos[1]=c;
    }
    return pos;
	}
  /**
  * This is a getNeighborsOf method used to get a list of neighbors
  * that surround a certain cell position. Return a list of (non- null )
  * cells neighboring (accounting for wrapping) the input location
  * in the petri dish.Each cell has up to 8 neighboring cells (in the
  * cardinal and ordinal directions). The list should have
  * neighboring cells in the order of northwest, north, northeast, west,
  * east, southwest, south, and southeast.If either of row or col
  * is out of bounds (no wrapping for this one), return null instead.
  *
  * @param dish Cell object of a 2D array representing the PetriDish
  * @param neighborsList list of Cell type neighbors
  * @param rowBounds int representing length of dish
  * @param colBounds int represenging length of an array in dish
  * @param row input variable of row position of cell
  * @param col input variable of column position of cell
  * @param wrap integer array representing the position of neighbors
  * and also deals with wrapping if out of bounds
  * @return List of cells representing neighbors around the given cell
  */
  public List<Cell> getNeighborsOf(int row, int col){
    // create new neighborsList array
    List<Cell> neighborsList = new ArrayList<Cell>();
    int rowBounds = dish.length;
    int colBounds = dish[0].length;
    // dealing with out of bounds errors
    if(row > rowBounds||col>colBounds||row<0||col<0){
      return null;
    }
    //NW
    int[] wrap = handleWrapping(row-1, col-1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //N
    wrap = handleWrapping(row-1, col);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //NE
    wrap = handleWrapping(row-1, col+1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //W
    wrap = handleWrapping(row, col-1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //E
    wrap = handleWrapping(row, col+1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //SW
    wrap = handleWrapping(row+1, col-1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //S
    wrap = handleWrapping(row+1, col);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    //SE
    wrap = handleWrapping(row+1, col+1);
    if (dish[wrap[0]][wrap[1]]!=null){
      neighborsList.add(dish[wrap[0]][wrap[1]]);
    }
    return neighborsList;
  }

  /**
  * This is a void move method used to simulate what happens when
  * movable cells move in the PetriDish. Move all Movable cells in
  * the petri dish based on each cell's respective getMove() behavior.
  * If a cell's new position would be out of bounds, wrap the position
  * value around on the dish if a cell moves off of the dish by
  * moving too far up, it should wrap around to the bottom of the dish.
  * If there are Movable cells that move into the same position as
  * non- Movable cells, the non- Movable cell
  * will always die (and call its apoptosis() method).
  * If there are multiple Movable cells in the same position after
  * all the cells have moved, the Movable cell with the largest mass
  * will stay and all other cells at that position should die. If
  * there is a tie in the largest masses, all the cells at that position die.
  *
  * @param dish Cell object of a 2D array representing the PetriDish
  * @param newDish copy of the dish 2D array of Cell objects
  * @param ties boolean if there are any ties in mass in same cell
  * @param movableToRemove ArrayList of movables that need to be removed
  * @param rowArray the array of rows in the 2D array
  * @param cell Cell object in 2D array
  * @param movableCell Movable casted cell
  * @param moveArray array representing the position after calling get.Move
  * @param move arry represenging position after wrapping
  * @param rowMove position of row after wrapping
  * @param colMove position of col after wrapping
  * @param movable movable cell in movables list
  * @return no return because of void method
  */
  public void move(){
    //copies all cells that are not instances of Movable
    Cell[][] newDish = new Cell[dish.length][dish[0].length];
    for(int i=0;i<dish.length;i++){
      for(int j=0;j<dish[i].length;j++){
        if(!(dish[i][j]instanceof Movable)){
          newDish[i][j]=dish[i][j];
        }else{
          newDish[i][j]=null;
        }
      }
    }
    // creates necessary lists/2D arrays
    boolean[][] ties = new boolean[dish.length][dish[0].length];
    ArrayList<Movable> movableToRemove = new ArrayList<Movable>();
    //loops through the dish 2D array
    for(Cell[] rowArray:this.dish){
      for(Cell cell:rowArray){
        // if cell is instance of Movable and not null
        if(cell instanceof Movable&&cell!=null){
          Movable movableCell = (Movable)cell;
          int[] moveArray = movableCell.getMove();
          //wrapping method to deal with position wrapping
          int[]move = handleWrapping(moveArray[0],moveArray[1]);
          int rowMove = move[0];
          int colMove = move[1];
          // if intended position is null
          // move cell to intended position
          if(newDish[rowMove][colMove]==null){
            newDish[rowMove][colMove]=(Cell)movableCell;
            ((Cell)movableCell).setCurrRow(rowMove);
            ((Cell)movableCell).setCurrCol(colMove);
          // if intended position is not instance of Movable
          // move cell to intended position
          }else if(!(newDish[rowMove][colMove]instanceof Movable)){
            (newDish[rowMove][colMove]).apoptosis();
            newDish[rowMove][colMove]=(Cell)movableCell;
            ((Cell)movableCell).setCurrRow(rowMove);
            ((Cell)movableCell).setCurrCol(colMove);
          // if intended position is instance of Movable
          // checks whether mass is larger than cell in intended position
          // removes the lower mass cell and sets position to new cell
          }else if
          (((Cell)movableCell).compareTo(newDish[rowMove][colMove])>0){
            movableToRemove.add((Movable)newDish[rowMove][colMove]);
            newDish[rowMove][colMove] = (Cell)movableCell;
            ties[rowMove][colMove] = false;
            ((Cell)movableCell).setCurrRow(rowMove);
            ((Cell)movableCell).setCurrCol(colMove);
          // if intended position is instance of movable
          // if masses are same, both die and add them to be removed
          }else if
          (((Cell)movableCell).compareTo(newDish[rowMove][colMove])==0){
            ties[rowMove][colMove] = true;
            ((Cell)movableCell).apoptosis();
            ((Cell)newDish[rowMove][colMove]).apoptosis();
            movableToRemove.add((Movable)newDish[rowMove][colMove]);
            movableToRemove.add(movableCell);
          // if mass is less than intended position cell mass
          // kill the movableCell and remove movableCell
          }else{
            ((Cell)movableCell).apoptosis();
            movableToRemove.add(movableCell);
          }
        }
      }
    }
    // loops through movableToRemove
    // removes from movables
    for(Movable movable:movableToRemove){
      movables.remove(movable);
    }
    // deals with ties
    // if ties is true, it removes the cell in intended position and
    // calls apoptosis and sets cell to null
    for(int i=0;i<ties.length;i++){
      for(int j=0; j<ties[0].length;j++){
        if(ties[i][j]){
          movables.remove((Movable)newDish[i][j]);
          newDish[i][j].apoptosis();
          newDish[i][j]=null;
        }
      }
    }
    //set dish to newDish
    dish=newDish;
  }
  /**
  * This is a void divide method used to simulate what happens when
  * Divisible cells divide in the PetriDish.
  * "Divide" all Divisible cells in the petri dish based on each
  * cell's respective getDivision() behavior. If a newly created cell's
  * position would be out of bounds, wrap the position value around on
  * the dish. if a cell moves off of the dish by moving too far up,
  * it should wrap around to the bottom of the dish. Also, make sure to
  * handle wrapping for corners. Divisible cells can only divide onto
  * positions of the board that were empty prior to the call to divide() .If
  * the position is not empty, no division onto that position will occur.
  * If there are multiple Divisible cells on the same position after all the
  * cells have divided, the Divisible cell with the largest mass will stay
  * and all other cells at that position should die. If there is a tie in
  * the largest masses, all the cells at that position die.

  *
  * @param dish Cell object of a 2D array representing the PetriDish
  * @param dishCopy copy of the dish 2D array of Cell objects
  * @param divideTies boolean if there are any ties in mass in same cell
  * @param addDivisible ArrayList of divisibles that need to be added
  * @param multiples ArrayList of multiple cells occupying same space
  * @param divCell Divisible casted dish cell
  * @param cellPos array representing the position after calling get.Move
  * @param pos array represenging position after wrapping
  * @param row position of row after wrapping
  * @param col position of col after wrapping
  * @param newCell intended position of divisible cell
  * @param multCell cell sharing a space with other cell(s)
  * @return no return because of void method
  */
  public void divide(){
    //initializes necessary 2D arrays/ArrayLists
    boolean[][] divideTies =
    new boolean[this.dish.length][this.dish[0].length];
    ArrayList<Divisible> addDivisible = new ArrayList<Divisible>();
    ArrayList<Cell> multiples = new ArrayList<Cell>();
    Cell[][]dishCopy= new Cell[this.dish.length][this.dish[0].length];
    //loops through dish to create copy to dishCopy
    for(int i=0;i<this.dish.length;i++){
      for(int j=0;j<this.dish[0].length;j++){
        dishCopy[i][j]=this.dish[i][j];
      }
    }
    //loops through dish finding divisible cells
    for(int i=0;i<this.dish.length;i++){
      for(int j=0;j<this.dish[0].length;j++){
        // if cell in dish is Divisible type and not null
        if(this.dish[i][j]instanceof Divisible&&this.dish[i][j]!=null){
          Divisible divCell = (Divisible)this.dish[i][j];
          // call getDivision and deal with wrapping
          int[] cellPos = divCell.getDivision();
          int[] pos = handleWrapping(cellPos[0],cellPos[1]);
          int row = pos[0];
          int col = pos[1];
          // if intended position is not null
          if(dishCopy[row][col]!=null){
            // if intended position is Divisible type
            // and space in dish is empty
            if(dishCopy[row][col]instanceof Divisible
            && this.dish[row][col]==null){
              // if cell's mass is greater than intended cell's mass
              // kill celll in intended position and add new copy
              // to addDivisible to be added later
              // set the new instance variables
              if(this.dish[i][j].compareTo(dishCopy[row][col])>0){
                Cell newCell = dishCopy[row][col];
                newCell.apoptosis();
                addDivisible.remove((Divisible)newCell.newCellCopy());
                newCell=this.dish[i][j].newCellCopy();
                addDivisible.add((Divisible)newCell.newCellCopy());
                newCell.currRow = row;
                newCell.currCol = col;
              // if cells' masses are equal
              // add to multiples list to be dealt with later
              // set position to be empty
              }else if(this.dish[i][j].compareTo(dishCopy[row][col])==0){
                multiples.add(dishCopy[row][col]);
                dishCopy[row][col] = null;
              }else{
                continue;
              }
            }
            else{
              continue;
            }
          }
          else {
            // deals with multiple cells in multiples
            for(int mult=0;mult<multiples.size();mult++) {
              Cell multCell = multiples.get(mult);
            // if cell in multiples has rows and columns equal to
            // row and col
            // sets divideTies to true if true
              if(multCell.getCurrRow()==row && multCell.getCurrCol()==col
              && divideTies[row][col] == false) {
                dishCopy[row][col] = null;
                divideTies[row][col] = true;
              }
            }
            // deals with multiples in space
            // if divideTies is true indicating multiple cells in space
            // create copy to add and update instance variables
            if(divideTies[row][col] = true) {
              dishCopy[row][col] = dishCopy[i][j].newCellCopy();
              addDivisible.add((Divisible)dishCopy[row][col].newCellCopy());
              dishCopy[row][col].setCurrRow(row);
              dishCopy[row][col].setCurrCol(col);
            }
          }
        }
      }
    }
    // for every cell in addDivisible
    // add it to divisibles list
    for(Divisible cell: addDivisible){
      this.divisibles.add(cell);
    }
    // update dish to dishCopy

    this.dish=dishCopy;
  }
  /**
  * This is a void update method used to simulate what happens when
  * cells update in the PetriDish. Simultaneously initiate apoptosis for
  * all eligible cells and spawn new cells for eligible empty spaces. This
  * means that all conditions are based on the petri dish right before
  * this method is called.For the cells that will go into apoptosis, set
  * the value of their position on the dish to null .For spaces in the
  * petri dish that were initially empty, if there are between 2 to 3
  * alive cells around , spawn a copy of the cell that
  * appears first in the list returned from getNeighborsOf() .
  *
  * @param dish Cell object of a 2D array representing the PetriDish
  * @param dishCopy copy of the dish 2D array of Cell objects
  * @param movables list of Movable cells
  * @param divisibles list of Divisible cells
  * @param apoptosisCells cells that need to call apoptosis
  * @param neighbors list containing neighbors of cell
  * @param spawnCells list of cells to spawn
  * @param spawn deep copy of cell
  * @param currCell cell in spawnCells
  * @param cellRow row of cell
  * @param cellCol column of cell
  * @param apoptosisCell cell in apoptosisCells list
  * @param currCol current column of cell
  * @param currRow current row of cell
  * @param currMass current mass of cell
  * @return no return because of void method
  */
  public void update(){
    // initialize 2D array to copy dish
    Cell[][] dishCopy = new Cell[dish.length][dish[0].length];
    this.movables = new ArrayList<Movable>();
    this.divisibles = new ArrayList<Divisible>();
    ArrayList<Cell> apoptosisCells = new ArrayList<Cell>();
    int NUM1_NEIGHBORS = 2;
    int NUM2_NEIGHBORS = 3;
    // loop through dish to see which cells will go through apoptosis
    for(int i=0;i<dish.length;i++){
        for(int j=0;j<dish[i].length;j++){
          if(dish[i][j]==null){
            continue;
          }
          // if checkApoptosis is true, add cell to apoptosisCells
          List<Cell> neighbors = this.getNeighborsOf(i,j);
          if(dish[i][j].checkApoptosis(neighbors)==true){
            apoptosisCells.add(dish[i][j]);
          }
        }
    }
    //initialize list for cells that need to be added
    ArrayList<Cell> spawnCells = new ArrayList<Cell>();
    // loops through dish to check if cells need to be spawned
    for(int i=0;i<dish.length;i++){
        for(int j=0;j<dish[i].length;j++){
          // if cell is null, checks neighbors to determine if it cell needs
          // to be spawned or not
          if(dish[i][j]==null){
            List<Cell> neighbors = this.getNeighborsOf(i,j);
            if(neighbors.size()==NUM1_NEIGHBORS||
              neighbors.size()==NUM2_NEIGHBORS){
              Cell spawn = neighbors.get(0).newCellCopy();
              //initializes instance variables of spawn cell
              spawn.setCurrRow(i);
              spawn.setCurrCol(j);
              spawnCells.add(spawn);
            }
          }else{
            continue;
          }
        }
    }
    // goes through cells needing to call apoptosis
    // calls apoptosis and sets it to null
    for(Cell apoptosisCell:apoptosisCells){
      apoptosisCell.apoptosis();
      apoptosisCell=null;
    }
    // goes through cells in spawnCells and spawns them in dish
    for(Cell currCell:spawnCells){
      int cellRow = currCell.getCurrRow();
      int cellCol = currCell.getCurrCol();
      dish[cellRow][cellCol]=currCell;
    }
    //loops through dish
    for(int i=0;i<dish.length;i++){
      for(int j=0;j<dish[0].length;j++){
        if(dish[i][j]!=null){
          //gets currRow, currCol, currMass
          int currRow = this.dish[i][j].getCurrRow();
          int currCol = this.dish[i][j].getCurrCol();
          int currMass = this.dish[i][j].getMass();
          // makes sure cell has not gone through apoptosis
          if(currRow!=-1&&currMass!=-1&&currCol!=-1){
            // copies all alive cells to dishCopy
            dishCopy[currRow][currCol] = dish[i][j];
          }
        }
        //repopulates divisibles
        if(dish[i][j]instanceof Divisible){
          this.divisibles.add((Divisible)dish[i][j]);
        }
        //repopulates movables
        if(dish[i][j]instanceof Movable){
          this.movables.add((Movable)dish[i][j]);
        }
        if(dish[i][j]==null){
          continue;
        }
      }
    }
    //sets dish to dishCopy
    dish = dishCopy;
  }
  /**
  * This is a void  iterate method used to simulate what happens when
  * cells move, divide, and update in the PetriDish. One call of iterate()
  * contains one move() (first step), one divide() (second step),
  * and finally one update() of the petri dish, in order.
  *
  * @return no return because of void method
  */
  public void iterate(){
    //calls and executes move method
    this.move();
    // calls and executes divide method
		this.divide();
    // calls and executes update method
		this.update();
  }
}
