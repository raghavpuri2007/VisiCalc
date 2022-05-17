//VisiCalc
//Raghav Puri
//AP Computer Science


import java.io.File; //Import the File class to use files
import java.io.FileNotFoundException; //Import the FileNotFoundException
import java.io.PrintStream; //Import PrintStream class to print to files
import java.util.*; //Import utility class for Arrays, ArrayList, and Scanner

class VisiCalc {
	static Grid grid = new Grid();
	static ArrayList<String> commands = new ArrayList<>();
  public static void main(String[] args) throws FileNotFoundException {
	  //welcome message
	  int j;
	  System.out.print("Welcome to VisiCalc!\nEnter: ");
	  //run loop while input is not quit
	  String message = new Scanner(System.in).nextLine();
	  while(!message.equalsIgnoreCase("quit")) {
		  processCommand(message);
		  System.out.print("Enter: ");
		  message = new Scanner(System.in).nextLine();
	  }
	  System.out.println("Thank you for using VisiCalc. Have a good day.");
  }
  //method to process the user or files commands
  public static void processCommand(String message) throws FileNotFoundException {
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
		  //splits userInput into tokens, to 
		  String[] split = message.split(" ");
		  if(split.length == 1) {
			  if(split[0].equalsIgnoreCase("clear")) {
				  //clears all cells in the spreadsheet
				  grid.clearGrid();
			  } else {
				  //gets cell at specific index
				  System.out.println(grid.getCell(split));					  
			  }
		  } else if(split.length == 2){
			  if(split[0].equalsIgnoreCase("save")) {
				  createFile(split[1]);
			  } else if(split[0].equalsIgnoreCase("load")) {
				  loadFile(split[1]);
			  } else {
				  grid.clearCell(split);
			  }
		  }else if(split.length == 4){
			  //sort cells
			  grid.sortCells(split);
		  } else {
			  if(split[1].equalsIgnoreCase("Size")) {
				 grid.CELL_SIZE = Integer.parseInt(split[2]);
			  } else if(split[0].equalsIgnoreCase("Number")){
				  if(split[1].equalsIgnoreCase("Rows")) {
					  grid.setRows(Integer.parseInt(split[2]));
				  } else {
					  grid.setCols(Integer.parseInt(split[2]));
				  }
			  }else {
				//pass tokenized message and the actual message
				 grid.addCell(split, message);
				 commands.add(message); 
			  }
		  }
	  }
  }
  
  public static void createFile(String fileName) throws FileNotFoundException {
	  File f = new File(fileName);
	  PrintStream ps = new PrintStream(f);
	  for(String command : commands) {
		  ps.println(command);
	  }
  }
  
  public static void loadFile(String fileName) throws FileNotFoundException {
	  File f = new File(fileName);
	  Scanner fileReader = new Scanner(f);
	  while(fileReader.hasNextLine()) {
		  processCommand(fileReader.nextLine());
	  }
  }
}
