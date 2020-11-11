//imports needed classes from library
import java.util.*;
import java.io.*;
import java.text.*;

public class StaffDemo {
  
  public static void main(String[] args) throws FileNotFoundException {
    //creates the main StaffMember array and fills it by calling createStaff()
    StaffMember[] workArray = createStaff();
    //calls the payAll() method with workArray to print out payroll
    payAll(workArray);
  }
  
  public static StaffMember[] createStaff() throws FileNotFoundException {
    //initiliazes varibles
    int counter = 0;
    Scanner scanner = new Scanner(new File("Staff.txt"));
    int numWorkers = Integer.parseInt(scanner.next());
    StaffMember[] staffArray = new StaffMember[numWorkers];
    //while the scanner has more text, the loop will continue
    while (scanner.hasNext()) {
      //takes data from the text file and stores it in temp variables
      String workType = scanner.next();
      int id = Integer.parseInt(scanner.next());
      String firstName = scanner.next();
      String lastName = scanner.next();
      double rate = Double.parseDouble(scanner.next());
      //finds out which worker type each line is and calls the according constructor
      if (workType.equals("Salaried")) {
        staffArray[counter] = new Salaried(id, firstName, lastName, rate);
      } else if (workType.equals("Volunteer")) {
        staffArray[counter] = new Volunteer(id, firstName, lastName, rate);
      } else if (workType.equals("Hourly")) {
        staffArray[counter] = new Hourly(id, firstName, lastName, rate);
      } else if (workType.equals("Executive")) {
        staffArray[counter] = new Executive(id, firstName, lastName, rate);
      }
      //adds 1 to counter variable
      counter++;
    }
    //returns filled array
    return staffArray;
  }
  
  public static void payAll(StaffMember[] workArray) {
    //initiates currency formatting
    NumberFormat currency = NumberFormat.getCurrencyInstance();
    //each loop/line will print out in this fashion: FirstName LastName (WorkerType): AmountPayed
    for (int i = 0;i < workArray.length;i++) {
      System.out.println(workArray[i].getFirstName() + " " + workArray[i].getLastName() + " " + workArray[i].getWorkType() + ": " + currency.format(workArray[i].pay()));
    }
  }
}