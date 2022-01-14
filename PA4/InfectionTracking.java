/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This program is used to simulate hypothetical COVID-19 transmission
 * simulations as well as collect data regarding how the virus would
 * spread if all students went about their lives without taking any
 * precautionary measures. This simulation includes the following
 * factors: populating lists of who is infected, their names,
 * their movement, the world size, how many people they infected,
 * the average number of infections, as well as the name of the
 * individual with the most infections after a certain number of days
**/
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
/**
* This class contains all of the methods to simulate the hypothetical
* COVID-19 transmission simulation. These methods include a couple
* isValid methods, populateArrays method, updateLocations, updateInfections,
* countInfectionsByStudent, findRNaught, and findSuperSpreader.
**/
public class InfectionTracking{
/**
* This method checks the validity of each value in the different arrays.
* The method checks to see if the inputs for each array are valid or not
* and returns a boolean statement to ensure validity or not.
* The method checks to see if the lengths of each array match each other,
* if any of the input arrays is null, and if the infection array values
* are either a 1 or 0.
*
* @param names - array for student names
* @param locations - array for locations
* @param movements - array for movement between locations
* @param infections - array for infections
* @param worldSize - size of 1-dimensional world
* @param pathToFile - file path
*
**/
  public static boolean isValid(String[] names, int[] locations,
  int[] movements, int[] infections){
    if(names==null||locations==null||movements==null||infections==null){
      return false;
    }
    for(int i=0;i<locations.length;i++){
      if(locations[i]<0){
        return false;
      }
    }
    if(names.length!=locations.length||locations.length!=movements.length||
    movements.length!=infections.length||infections.length!=names.length){
      return false;
    }
    boolean infectionBool = false;
    for(int j=0;j<infections.length;j++){
      if(infections[j]==0||infections[j]==1){
        infectionBool = true;
      }else{
      infectionBool=false;
      }
    }
    return infectionBool;
  }
/**
* This method checks the validity with the introduciton of a new
* parameter: worldSize
* This method makes sure none of the arrays are null, the locations
* array and the movements array have the same length, the worldSize
* is between 0 and worldSize-1
* @param locations - array of locations as integers
* @param movements - array of movements between locations as integers
* @param worldSize - int indicating size of world
* returns boolean checking validity of these tests
**/
  public static boolean isValid(int[] locations,int[] movements,
  int worldSize){
    if(locations==null||movements==null||
    locations.length!=movements.length||worldSize<0){
      return false;
    }
    for(int i=0;i<locations.length;i++){
      if(locations[i]<0||locations[i]>(worldSize-1)){
        return false;
      }
    }
    return true;
  }
  /**
  * This method checks the validity with respect to different
  * parameters: locations, worldSize, and infecttions
  * This method makes sure none of the arrays are null, the locations
  * array and the infections array have the same length, the worldSize
  * is between 0 and worldSize-1
  * @param locations - array of locations as integers
  * @param infections - array of infections either 1 or 0
  * @param worldSize - int indicating size of world
  * returns boolean checking validity of these tests
  **/
  public static boolean isValid(int[] locations,
  int worldSize,int[] infections){
    if(locations==null||infections==null){
      return false;
    }
    if(worldSize<0){
       return false;
    }
    for(int i=0;i<locations.length;i++){
      if(locations[i]<0||locations[i]>(worldSize-1)){
        return false;
      }
    }
    boolean sameLength = locations.length==infections.length;
    for(int j=0;j<infections.length;j++){
      if(infections[j]!=0&&infections[j]!=1){
        return false;
      }
    }
    return sameLength;
  }
  /**
  * This method is used to populate empty arrays from the text file
  * that is imported by this method also. The method imports a file
  * and scans through it getting the individual values from it
  * and populating arrays according to those values. These arrays
  * include names, locations, movements, infections
  * The method returns -1 if the arrays are not properly populated
  * and returns the max locations value+1 if properly populated
  *
  * @param pathToFile - file path
  * @param names - array of names
  * @param locations - array of locations as integers
  * @param infections - array of infections either 1 or 0
  * @param worldSize - int indicating size of world
  * @param movements - array of movements between locations as integers
  * returns max locations value+1 if populated correctly
  * returns -1 if populated incorrectly
  **/
  public static int populateArrays(String pathToFile, String[] names,
  int[] locations, int[] movements, int[] infections) throws IOException{
    if(pathToFile!=null&&isValid(names,locations,movements,infections)){
      FileInputStream filePath = new FileInputStream(pathToFile);
      Scanner sc = new Scanner(filePath);
      int i =0;
      while(sc.hasNextLine()){
      // goes thru each line in text file splitting strings by the ","
      // this gets the individual values to go in each array
        String str[]=sc.nextLine().split(",");
        String name = str[0];
        int location = Integer.parseInt(str[1]);
        int movement = Integer.parseInt(str[2]);
        int infection = Integer.parseInt(str[3]);
        names[i]=name;
        locations[i] = location;
        movements[i] = movement;
        infections[i] = infection;
        i=i+1;
      }
      sc.close();
      // loops thru finding max value
      int maxVal = locations[0];
      for(int j=0;j<locations.length;j++){
        if(maxVal<locations[j]){
          maxVal = locations[j];
        }
      }
      int max = maxVal+1;
      return max;
    }else{
      return -1;
    }
  }
  /**
  * This method updates the locations of the individuals based on their
  * movements and updates the current location after the movement. It
  * also checks that the movements stay within the worldsize and wraps
  * back the location using the modulus operator to ensure the movement
  * stays within the worldSize
  *
  * @param modulo - modulus of the new location divided by the worldSize
  * @param worldSize - int indicating size of world
  * @param movements - array of movements between locations as integers
  * @param locations - array of locations as integers
  *
  **/
  public static void updateLocations(int worldSize, int[] locations,
  int[] movements){
    if(isValid(locations,movements,worldSize)==true){
      for(int i=0;i<locations.length;i++){
        // check validity of input
        if(locations[i]+movements[i]<0||locations[i]+movements[i]>=worldSize){
          int modulo = (locations[i]+movements[i])%worldSize;
          // if new location is negative
          if(modulo<0){
            locations[i]=modulo+worldSize;
          // if new location is not neg and > worldSize
          }else{
            locations[i]=modulo;
          }
          // if new location is not negative and < worldSize
        }else{
          locations[i] = locations[i]+movements[i];
        }
      }
    }
  }
  /**
  * This method updates the infections of students who come into contact
  * with each other or are in the same location at one point. The method
  * takes the locations and worldSize ensureing not to go out of bounds,
  * and counds how many individuals a student has infected based upon
  * their contact at their shared location. The method creates a new array
  * keeping track of all the students and the number of other students
  * they infect.
  *
  * @param modulo - modulus of the new location divided by the worldSize
  * @param worldSize - int indicating size of world
  * @param infections - array of infections either 1 or 0
  * @param locations - array of locations as integers
  * returns the newly created array that keeps track of the number of
  * students that were infected by the student at the index
  *
  **/
  public static int[] updateInfections(int worldSize, int[] locations,
  int[] infections){
    if(isValid(locations,worldSize,infections)==true){
      int[] numStudentsInfected= new int[infections.length];
      // loops thru adding to numStudentsInfected for each student infected
      for(int i=0;i<infections.length;i++){
        if(infections[i]==1){
          for(int j=0;j<locations.length;j++){
            if(locations[i]==locations[j]&&infections[j]==0){
              numStudentsInfected[i] = numStudentsInfected[i]+1;
            }
          }
        }
      }
      // loops through changing all the infected to 1's
      for(int i=0;i<infections.length;i++){
        if(infections[i]==1){
          for(int j=0;j<infections.length;j++){
            if(locations[i]==locations[j]&&infections[j]==0){
              infections[j]=1;
            }
          }
        }
      }
      return numStudentsInfected;
    }
    return null;
  }
  /**
  * This method creates a new array called infectionRecord and updates
  * the locations of students after their movements each day and
  * records the number of people they have infected or got infected in
  * the shared location
  *
  * @param worldSize - int indicating size of world
  * @param infections - array of infections either 1 or 0
  * @param locations - array of locations as integers
  * @param movements - array of movements between locations as integers
  * @param infectionRecord - array of infections over a number of days
  * returns the newly created array that keeps track of the number of
  * students that were infected by the student at the index after
  * a certain number of days
  *
  **/
  public static int[] countInfectionsByStudent(int days, int worldSize,
  int[] locations, int[] movements, int[]infections){
    // checks validity of inputs
    if(isValid(locations,worldSize,infections)==true&&
    isValid(locations, movements, worldSize)==true&&days>=0){
      int[] infectionRecord = new int[locations.length];
      for(int i=0;i<days;i++){
        updateLocations(worldSize,locations,movements);
    // create new array to store new values to add infectionRecord
        int[] parallelArr = updateInfections(worldSize, locations, infections);
        for(int j=0;j<infections.length;j++){
          infectionRecord[j] = infectionRecord[j]+parallelArr[j];
        }
      }
      return infectionRecord;
    }else{
      return null;
    }
  }
  /**
  * This method takes the newly created array from countInfectionsByStudent
  * and checks to see its validity if it is not null and not equal to 0.
  * It also checks to see none of the values in the array are negative and
  * takes the values in the array and adds them up to find the average
  * number of people infected by a single individual student
  *
  * @param infectionRecord - array of infections over a number of days
  * returns -1 if input is invalid
  * returns average number of infections by a single student
  **/
  public static int findRNaught(int[] infectionRecord){
    // checks validity of input
    if(infectionRecord==null||infectionRecord.length==0){
      return -1;
    }
    for(int i=0;i<infectionRecord.length;i++){
      if(infectionRecord[i]<0){
        return -1;
      }
    }
    int sum=0;
    for(int j=0; j<infectionRecord.length;j++){
      sum = sum+infectionRecord[j];
    }
    // math ceiling allows the value to always be rounded up
    int avg = (int)Math.ceil(((double)sum)/infectionRecord.length);
    return avg;
  }
  /**
  * This method takes the array from countInfectionsByStudent
  * and checks to see its validity if it is not null and not equal to 0.
  * It also checks to see none of the values in the array are negative and
  * also makes sure the names array and infectionRecord array are equal
  * in length. It also loops through the infectionRecord array
  * and finds the highest infection number and uses that index to
  * locate the name of the individual responsible for the highest
  * spread of COVID-19
  *
  * @param infectionRecord - array of infections over a number of days
  * returns null if input is invalid
  * returns name of SuperSpreader
  **/
  public static String findSuperSpreader(int[] infectionRecord,
   String[] names){
     // checks validity of input
     if(infectionRecord==null||infectionRecord.length==0||
     names==null||names.length==0){
       return null;
     }
     if(infectionRecord.length!=names.length){
       return null;
     }
     for(int i=0;i<infectionRecord.length;i++){
       if(infectionRecord[i]<0){
         return null;
       }
     }
    // loops thru to find max infeciton value
    int maxInfect=infectionRecord[0];
    int index = 0;
    for(int j=0;j<infectionRecord.length;j++){
      if(maxInfect<infectionRecord[j]){
        maxInfect = infectionRecord[j];
        index = j;
      }
    }
    // uses max infection value index to find name
    String namesIndex=names[index];
    return namesIndex;
  }
}
