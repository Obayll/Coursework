/**
 * @authot Jordan Ditzler
 * @version 1.8.0_101
 */

import java.util.*;
import java.io.*;
import java.text.*;

/**
 * This class consists of methods to take data from a .txt filed and output it into proper form
 * Methods include: Constructor, createStaff(), payAll()
 */
public class StaffDemo {
  
  /**
   * Main method of StaffDemo
   * 
   * Creates array of StaffMember and fills it with data from .txt file
   * Outputs data using payAll() method
   */
  public static void main(String[] args) throws FileNotFoundException {
    StaffMember[] workArray = createStaff();
    payAll(workArray);
  }
  
  /**
   * Method that fills given array with StaffMember data
   * 
   * Scans .txt file for data
   * Reads line and puts data into temp variables
   * Assigns data to specific StaffMember subclass for each element
   * 
   * @return filled StaffMember array
   */
  public static StaffMember[] createStaff() throws FileNotFoundException {
    int counter = 0;
    Scanner scanner = new Scanner(new File("Staff.txt"));
    int numWorkers = Integer.parseInt(scanner.next());
    StaffMember[] staffArray = new StaffMember[numWorkers];
    while (scanner.hasNext()) {
      String workType = scanner.next();
      int id = Integer.parseInt(scanner.next());
      String firstName = scanner.next();
      String lastName = scanner.next();
      double rate = Double.parseDouble(scanner.next());
      if (workType.equals("Salaried")) {
        staffArray[counter] = new Salaried(id, firstName, lastName, rate);
      } else if (workType.equals("Volunteer")) {
        staffArray[counter] = new Volunteer(id, firstName, lastName, rate);
      } else if (workType.equals("Hourly")) {
        staffArray[counter] = new Hourly(id, firstName, lastName, rate);
      } else if (workType.equals("Executive")) {
        staffArray[counter] = new Executive(id, firstName, lastName, rate);
      }
      counter++;
    }
    return staffArray;
  }
  
  /**
   * Method that outputs StaffMember array in a specific format
   * 
   * Initiates currency format
   * Outputs each element in the array to one line formatted
   * 
   * @param StaffMember filled array
   */
  public static void payAll(StaffMember[] workArray) {
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    for (int i = 0;i < workArray.length;i++) {
      System.out.println(workArray[i].getFirstName() + " " + workArray[i].getLastName() + " " + workArray[i].getWorkType() + ": " + currency.format(workArray[i].pay()));
    }
  }
}