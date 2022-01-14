/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This Server class is a class  to represent the server that stores all
 * recent IDs from COVID-19 positive users. The server will only support two
 * operations: adding new IDs and getting all stored IDs.This class works in
 * conjunction with the ContactInfo class and Student class to simulate an
 * Exposure Notifications System that can quickly notify registered users
 * about their recent close contact with COVID-19 infected people
**/
import java.util.*;
public class Server {
/**
* This main Server class is used to act as a server in which all the most
* recent IDs from COVID-19 positive users are stored. This is a no-arg
* constructor for Server and initializes infectedIds with a new ArrayList
*
* @param infectedIds stores the IDs in the order that they are added to the
* server, with the first being at index 0 and the latest being at the end
* of the list.
* @param id stores the (random) ID that is sent (to the other student).
*/
  public ArrayList<Integer> infectedIds;
  public Server(){
    this.infectedIds = new ArrayList<Integer>();
  }
/**
* This method adds every ID from ids into infectedIds in the order they
* appear (first at index 0, last at the end) and return true to indicate
* adding is successful. If ids is null , return false to indicate adding
* failed without modifying anything. Do not do anythingspecial for null
* values inside ids .
*
* @return boolean if the arraylist populated correctly
*/
  public boolean addInfectedIds(ArrayList<Integer> ids){
    if(ids ==null){
      return false;
    }
    infectedIds.addAll(ids);
    return true;
  }
/**
* this method returns a deep copy of infectedIds . This means that you
* should create a new ArrayList and fill it with the exact elements in
* infectedIds - this way, if someone modifies the returned list, the
* instance variable will not be affected.
*
* @return an arraylist copy of the infectedIds

*/
  public ArrayList<Integer> getInfectedIds(){
    ArrayList<Integer> deepCopy = new ArrayList<Integer>();
    for(int i:infectedIds){
      deepCopy.add(i);
    }
    return deepCopy;
  }
}
