/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This ContactInfo class is a class to hold all the information used for
 * the exchange of information via Bluetooth. This class works in
 * conjunction with the Server class and Student class to simulate an
 * Exposure Notifications System that can quickly notify registered users
 * about their recent close contact with COVID-19 infected people
**/
import java.util.*;
/**
* This class holds all of the information needed to exchange information
* via bluetooth for the app this includes: id, distance, time, used
*/
public class ContactInfo {
/**
* These are the instance variables for the class
*
* @param id stores the (random) ID that is sent (to the other student).
* @param distance stores the distance between the two students and should
* always be non-negative.
* @param time stores the time this contact happens.
* @param used stores whether this contact information has been used to
* send out an exposure notification.
*/
  public int id;
  public int distance;
  public int time;
  public boolean used;

/**
* This is the main constructor method that initializes the values of the
* instance variables
*
* @param id stores the (random) ID that is sent (to the other student).
* @param distance stores the distance between the two students and should
* always be non-negative.
* @param time stores the time this contact happens.
* @param used stores whether this contact information has been used to
* send out an exposure notification.
*/
  public ContactInfo(int id, int distance, int time){
    this.id = id;
    this.distance = distance;
    this.time = time;
    this.used = false;
  }
/**
* This isValid method is a method that checks if the instance variables are
* valid
*
* @param distance stores the distance between the two students and should
* always be non-negative.
* @param time stores the time this contact happens.
* @return whether the instance variables are valid
*/
  public boolean isValid(){
    if(distance<0||time<0){
      return false;
    }
    return true;
  }
}
