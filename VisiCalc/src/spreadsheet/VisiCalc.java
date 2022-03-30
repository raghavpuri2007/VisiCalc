package spreadsheet;
import java.util.Arrays;
import java.util.Scanner;  // Import the Scanner class

class VisiCalc {
	static Grid grid = new Grid();
	
  public static void main(String[] args) {
	  //welcome message
	  System.out.println("Welcome to VisiCalc!");
	  
	  String message = "";
	  //run loop while input is not quit
	  while(!message.equalsIgnoreCase("quit")) {
		  System.out.print("Enter: ");
		  message = new Scanner(System.in).nextLine();
		  //help message
		  if(message.equalsIgnoreCase("help")) {
			  System.out.println("""
			  This application is called VisiCalc. It is an electronic spreadsheet that can store values at a particular row and column.
			  Current commands are \"help\", \"quit\" (to quit the program), and \"print\" (to print out the spreadsheet). If you still don't 
			  understand, then go on google.
			  """);
		  } else if(message.equalsIgnoreCase("print")){
			  //prints grid
			  grid.printGrid();
		  } else {
			  String[] split = message.split(" ");
			  grid.addCell(split);
		  }
	  }
	  System.out.println("Thank you for using VisiCalc. Have a good day.");
  }
}