//VisiCalc
//Raghav Puri
//AP Computer Science
import java.io.FileNotFoundException;

public class Grid {
	static Cell[][] spreadsheet = new Cell[10][7];
	//method to print out the grid
	public void printGrid() {

		  boolean addedTop = false;
		  //default dashed lines
		  String nineDashes = "--------";
		  //default blank spaces
		  String sevenBlanks = "       ";
		  //nested loops to iterate through spreadhseet array
		  for(int r = 0; r < spreadsheet.length; r++) {
			  for(int c = 0; c < spreadsheet[r].length; c++) {
				  if(!addedTop && r == 0) {
					  //print the top left blank box
					  if(c == 0) {
						  System.out.print(sevenBlanks.substring(1) + " |");
					  }
					  //prints column headers using unicode values
					  System.out.print(sevenBlanks.substring(0, 3) + (char)(c + 65) + sevenBlanks.substring(4) + "|");
				  } else {
					  //prints rows headers
					  	if(c == 0) {
					  		System.out.printf(sevenBlanks.substring(0, 2) + "%2d" + sevenBlanks.substring(4) + "|", (r+1));
					  	} 
					  	//gets element at the index in the spreadsheet
					  	Cell element = spreadsheet[r][c];
						if(element == null) {
							System.out.print(sevenBlanks + "|");
						} else {
							if(element.toString().length() >= 7) {
								System.out.printf(element.toString().substring(0, 7) + "|");
							} else {
								System.out.printf("%6s |", element);
							}
						}
				  }
			  }
			  System.out.println("");
			  // adds the dashed lines under each cell in the grid
			  for(int i = 0; i <= spreadsheet[r].length; i++) {
				  System.out.print(nineDashes);
			  }
			  System.out.println("");
			  //resets the row back to the first row
			  if(!addedTop) {
				  addedTop = true;
				  r--;
			  }
		  }
	  }
	//adds specific type of cell to the spreadsheet list
	public void addCell(String[] arr, String message) throws FileNotFoundException {
		Cell cell;
		//checks if added Cell was a DateCell
		if(arr[2].contains("/")) {
			cell = new DateCell(arr);
		//checks if added Cell was a TextCell
		} else if(arr[2].equals("\"")) {
			cell = new TextCell(arr);
		//checks if added Cell was a FormulaCell
		} else if(arr[2].equals("(")){
			cell = new FormulaCell(arr, message);
		//checks if added Cell was a normal integer Cell
		}else {
			cell = new Cell(arr);
		}
		spreadsheet[cell.rowIndex][cell.colIndex] = cell;
		//updateFormulaCell(arr[0]);
	}
	//returns the value at the cell location
	public Cell getCell(String[] arr) {
		return spreadsheet[Cell.getRowIndex(arr[0])][Cell.getColIndex(arr[0])];
	}
	//method clears all cells in spreadsheet
	public void clearGrid() {
		for(int r = 0; r < spreadsheet.length; r++) {
			for(int c = 0; c < spreadsheet[r].length; c++) {
				spreadsheet[r][c] = null;
			}
		}
		System.out.println("Grid successfully cleared");
	}
	//method clears cell at specific spot
	public void clearCell(String[] arr) {
		spreadsheet[Cell.getRowIndex(arr[1])][Cell.getColIndex(arr[1])] = null;
		System.out.println("Cell successfully cleared");
	}
	
	/*public void updateFormulaCell(String location) throws FileNotFoundException {
		for(int r = 0; r < spreadsheet.length; r++) {
			for(int c = 0; c < spreadsheet[r].length; c++) {
				if(spreadsheet[r][c] instanceof FormulaCell) {
					FormulaCell fc = (FormulaCell) spreadsheet[r][c];
					if(fc.message.contains(location)) {
						VisiCalc.processCommand(fc.message);
					}
				}
			}
		}
	}
	*/
}
