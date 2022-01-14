/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 *
 * This Student class is a class to handle both ID exchanges and student
 * related functionality within the app. This class works in
 * conjunction with the Server class and ContactInfo class to simulate an
 * Exposure Notifications System that can quickly notify registered users
 * about their recent close contact with COVID-19 infected people
**/
import java.util.ArrayList;
import java.util.Random;
/**
* This is the main class that all the methods and constructors will be in.
* In a real world we would create a class representing phones to hangle ID
* exchange and a class representing students to handle movements, but This
* class does what we need it to do
*
* @param id stores the (random) current ID of the student.
* @param location stores the current location of the student.
* @param covidPositive stores an indicator for if the student has
* tested positive
* @param inQuarantine stores an indicator for if the student is in
* quarantine (and therefore cannot move).
* @param usedIds stores all the random IDs that the student has used so far,
* in order, with the first ID at index 0 and the most recent one
* (the one currently stored in id ) at the end of the list.
* @param contactHistory stores the ContactInfo objects that were sent
* to this student in the order they were received, with the first received
* at index 0 and the most recent one at the end of the list.
*/
public class Student {
  public int id;
  public int location;
  public boolean covidPositive;
  public boolean inQuarantine;
  public ArrayList<Integer> usedIds;
  public ArrayList<ContactInfo> contactHistory;
/**
* this class is the main constructor class that initializes all of the values
* of all the instance variables. it is a no-arg constructor for Student
* it initializes all instance variables properly in this constructor
* id and location should both be -1 . We intentionally set id and location to
* an invalid value to indicate that the student is not ready for simulation.
* covidPostive and inQuarantine should be false , since we are assuming that
* all students are not infected at the beginning of the simulation. usedIds
* and contactHistory should each be initialized with a new (empty) ArrayList
*
*/
  public Student(){
    this.id = -1;
    this.location = -1;
    this.covidPositive = false;
    this.inQuarantine = false;
    this.usedIds = new ArrayList<Integer>();
    this.contactHistory = new ArrayList<ContactInfo>();
  }
/**
* these instance methods for the Student class to facilitate updating each
* object and for doing some computations based on the current state of each
*  object. Each method should have the behavior that is specified and all
* instance variables should be updated or left unmodified as necessary so
* that future method calls operate on correct instance variables.
*
* This method sets the location of the student to the new location that is
* passed into the method as a paraeter. The method then returns true or
* false whether it was able to set the new location.
*
* @param newLocation the new location as an integer value
* @param inQuarantine stores an indicator for if the student is in
* quarantine (and therefore cannot move).
* @return boolean whether it set the new location or not
*/
  public boolean setLocation(int newLocation){
    if(newLocation>=0&& inQuarantine==false){
      this.location = newLocation;
      return true;
    }
    return false;
  }
/**
* This method Updates id with a new random integer within the range
* [0, Integer.MAX_VALUE ). The method should also store this new id in
* the usedIds list appropriately. It is a void method so it does not return
* anything
*/
  public void updateId(){
    Random r = new Random();
    id = r.nextInt(Integer.MAX_VALUE);
    usedIds.add(id);
  }
/**
* This method adds contact information of a ContactInfo object into the
* arraylist contactHistory. it makes sure the input parameter for the
* ContactInfo object is valid
*
* @param info a ContactInfo object that stores instance values that is stored
* in this method
* @param contactHistory arraylist storing ContactInfo objects
* @return boolean whether it added the contact info to the list
*/
  public boolean addContactInfo(ContactInfo info){
    if(info!=null&&info.isValid()){
      contactHistory.add(info);
      return true;
    }
    return false;
  }
/**
* This method uploads all used ids from a server object into an arraylist
* This method checks to see if the server object is null
*
* @param server Server object that holds the student information and simulates
* the app running and user-actions
* @return boolean whether it uploaded the usedIds or not
*/
  public boolean uploadAllUsedIds(Server server){
    if(server!=null){
      return server.addInfectedIds(usedIds);
    }
    return false;
  }
/**
* This method takes the server objects information and updates the
* covidPostive variable and inQuarantine variable and changes them to true
* it uploads the usedIds to the server if the student is positive
*
* @param covidPostive boolean if student is covid positive
* @param isPositive boolean if student is positive
* @param inQuarantine if student is in quarantine
*/
  public boolean testPositive(Server server){
    covidPositive = true;
    inQuarantine = true;
    boolean isPositive = uploadAllUsedIds(server); //note:
    return isPositive;
  }
/**
* This method gets the recent positive contacts of a user based on
* the server and the time of contact. it is dont by  calling getInfectedIds()
* in the Server class , and check this Student 's contactHistory against them.
* Returning a sublist of contactHistory
*
* @param server Server object that holds the student information and simulates
* the app running and user-actions
* @param fromTime time from which the user was exposed to contact
* @param sublist the sublist of recent positive contacts that is returned
* @param infectedIdsList a list of infectedIds based on the server info
* @param used stores whether this contact information has been used to
* send out an exposure notification.
* @param id stores the (random) ID that is sent (to the other student).
* @param time stores the time this contact happens.
* @return sublist of recent positive contacts
*/
  public ArrayList<ContactInfo> getRecentPositiveContacts(Server server,
  int fromTime){
    ArrayList<ContactInfo> sublist = new ArrayList<ContactInfo>();
    ArrayList<Integer> infectedIdsList = new ArrayList<Integer>();
    infectedIdsList = server.getInfectedIds();
    if(server==null||fromTime<0||server.getInfectedIds()==null){//note: server
      return null;
    }
    for(ContactInfo i:contactHistory){
      if(i.used==false&&infectedIdsList.contains(i.id)&&i.time>=fromTime){
        sublist.add(i);
      }
    }
    return sublist;
  }
/**
* This method Assesses the student's risk of having COVID-19 and simulate
* notifiying the student by letting them choose to self-quarantine. it
* Gets all recent contacts with positive cases by calling
* getRecentPositiveContacts() with the appropriate arguments. it also
* analyzes this student's risk of having COVID-19 based on the recent
* positive contacts
*
* @param server Server object that holds the student information and simulates
* the app running and user-actions
* @param fromTime time from which the user was exposed to contact
* @param quarantineChoice boolean if student is quarantine
* @param posContacts arraylist of ContactInfo objects that are recent
* positive contacts
* @param maxRecentContacts the maximum number of recent contacts before user
* is deemed high risk
* @param maxDistance maximum distance before student is deemed high risk
* @param inQuarantine if student is in quarantine
* @return returns 1 or 0 depending if the student is deemed high risk
*/
  public int riskCheck(Server server, int fromTime, boolean quarantineChoice){
    ArrayList<ContactInfo> posContacts =
    getRecentPositiveContacts(server, fromTime);
    int maxRecentContacts = 3;
    int maxDistance = 1;
    boolean isHighRisk = false;
    if(posContacts==null){
      return -1;
    }
    //loop through all ContactInfo objects in posContacts
    for(ContactInfo j:posContacts){
      if(j.distance<=maxDistance&&posContacts.size()>=1
      ||posContacts.size()>=maxRecentContacts){
        j.used = true;
        isHighRisk = true;
      }
    }
    if(isHighRisk==true){
      if(quarantineChoice==true){
        this.inQuarantine=true;
      }
      return 1;
    }
    return 0;
  }
}
