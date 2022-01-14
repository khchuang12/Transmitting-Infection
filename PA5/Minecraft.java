/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This program is simulate possible occurances that might happen during
 * a minecraft world adventure with you and your friends.There are two
 * parts to this program: the first part rotates the floor plan of your
 * beach biome home overlooking an ocean cliff 90 degrees clockwise
 * so the beach is facing the sunset. The second part of this program
 * takes a list of all the mobs that could possibly be infected by The
 * infected mob and returns them in an ArrayList.
**/
import java.util.*;
/**
* This class contains all of the methods to simulate the hypothetical
* Minecraft world simulation. These methods include a rotateFloorPlan
* method as well as a getMobsToTest method
**/
public class Minecraft{
  /**
  * This class simulates the hypothetical rotation of your house floor
  * plan with a rotation of 90 degrees. This method takes in a 2D
  * array and returns another 2D array with the values of the Array
  * situated as such that it would appear to be rotated 90 degrees clockwise
  *
  * @param originalFloorPlan - 2D array of the originalFloorPlan
  * @param col - columns of original 2D Array
  * @param row - rows of original 2D Array
  * @param inner - counter used to balance the rotated sequence
  *
  * returns 2D array of rotated floorplan
  **/
  public static int[][] rotateFloorPlan(int[][]originalFloorPlan){
    // checks to see if valid
    if(originalFloorPlan==null){
      int[][] rotateFloorPlan = null;
      return rotateFloorPlan;
    }
    // creates new empty 2D array
    int[][] rotateFloorPlan = new int[originalFloorPlan[0].length]
    [originalFloorPlan.length];
    int inner = originalFloorPlan.length+1;
    // loops through rows and columns, updating the new array values
    for(int row=0;row<originalFloorPlan.length;row++){
      inner = inner-2;
      for(int col=0;col<originalFloorPlan[0].length;col++){
        rotateFloorPlan[col][row+inner]=originalFloorPlan[row][col];
      }
    }
    return rotateFloorPlan;
  }
  /**
* This method takes in the parameters of a 2D array called groups and a
* string called infected to create a list of the other mobs at the party
* who need to be tested for the virus that the infected mob has. This is
* based upon the location of each mob in the 2D array. It checks whether
* some input is valid or not and returns a list of mobs that need to be
* tested.
*
* @param infected - infected mob
* @param groups - 2D orray of all other mobs at party
*
*returns arraylist of mobs that need to test
**/
  public static ArrayList<String> getMobsToTest
  (String[][] groups, String infected){
    //checks validity of inputs
    if(groups==null||infected==null){
      return null;
    }
    // creates new empty arraylist to store mobs
    ArrayList<String> getMobsToTest = new ArrayList<String>();
    //loops through colums
    for(int i=0;i<groups.length;i++){
      if(groups[i]==null){
        continue;
      }
      //loops through rows checking null
      for(int j=0;j<groups[i].length;j++){
        if(groups[i][j]==null){
          continue;
        }
      // checks if infected mob is in that row
        if(groups[i][j].equals(infected)){
      // if infected mob is in row, loops back through entire row and
      // adds mobs to array list
          for(int k=0;k<groups[i].length;k++){
            if(!getMobsToTest.contains(groups[i][k])&&
            !groups[i][k].equals(infected)){
              getMobsToTest.add(groups[i][k]);
            }
          }
        }
      }
    }
    return getMobsToTest;
  }
}
