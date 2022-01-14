/**
 * Name:Kody Chuang
 * ID: A16350587
 * Email: khchuang@ucsd.edu
 * Sources Used: "None"
 */
/**
 * This class calculates the risk factor of individuals who have been in contact.
 * It calculates the risk factor by calculating the amount of minutes two individuals
 * have been in contact.
 * This class takes input from the user and stores them as 6 individual values
 * The values represent the date, hour, and minutes of the start of exposure as
 * well as end of exposure.
 */
import java.util.Scanner;
import java.lang.Math;
import java.lang.reflect.Array;
public class CovidTransmission {
  public static void main(String [] args) {
    Scanner input = new Scanner(System.in);
    String time = input.nextLine();
    String[] arrayOfStr = time.split(" ", 6);
    int[] intArray = new int[arrayOfStr.length];
       for (int i = 0; i < arrayOfStr.length; i++) {
          String num = arrayOfStr[i];
          intArray[i] = Integer.parseInt(num);
       }
    int startDate = (int)Array.get(intArray, 0);
    int startHour = (int)Array.get(intArray, 1);
    int startMin = (int)Array.get(intArray, 2);
    int endDate = (int)Array.get(intArray, 3);
    int endHour = (int)Array.get(intArray, 4);
    int endMin = (int)Array.get(intArray, 5);

    int dateInMin = 0;
    int hourInMin = 0;
    int min = 0;
    int totalMin = 0;
    if(1 <= startDate && startDate <=31 && 1<= endDate && endDate <= 31 && startDate<=endDate) {
      dateInMin = (endDate - startDate)*1440;
    //  System.out.println(dateInMin);
    }
    else {
      System.out.println("-1 -1");
      return;
      //need a break but break displays error
    }
    if(0 <= startHour && startHour <= 23 && 0 <= endHour && endHour <= 23){
      if(startDate == endDate && endHour<startHour){
        System.out.println("-1 -1");
        return;
      }
      else if(startHour > endHour) {
        hourInMin = Math.abs(24 -(startHour - endHour))*60 - 1440;
    //    System.out.println(hourInMin);
      }

      else {
        hourInMin = (endHour - startHour)*60;
      }
    }
    else {
      System.out.println("-1 -1");
      return;
    }
    if(0 <= startMin && startMin <= 59 && 0<= endMin && endMin <= 59){
        if(startDate<=endDate && endHour == startHour && endMin<startMin){
          System.out.println("-1 -1");
          return;
        }
        min = endMin-startMin;
    }
    else {
      System.out.println("-1 -1");
      return;
    }
    totalMin = dateInMin + hourInMin + min;
    System.out.print(totalMin);
    if(0<=totalMin&&totalMin<=60){
      System.out.print(" low");
    }
    else if(60<totalMin&&totalMin<=180){
      System.out.print(" medium");
    }
    else if (180<totalMin&&totalMin<=360){
      System.out.print(" high");
    }
    else if(totalMin>360) {
      System.out.print(" HIGH");
    }
  }
}
