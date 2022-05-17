//VisiCalc
//Raghav Puri
//AP Computer Science
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Grid {
	public int CELL_SIZE = 7;
	public static int CELL_ROWS = 10;
	public static int CELL_COLUMNS = 7;
	static Cell[][] spreadsheet = new Cell[CELL_ROWS][CELL_COLUMNS];
	//method to print out the grid
	public void printGrid() {

		  boolean addedTop = false;
		  //default dashed lines
		  String dashes = "";
		  //default blank spaces
		  String blanks = "";
		  for(int i = 0; i < CELL_SIZE; i++) {
			  blanks += " ";
		  }
		  for(int i = 0; i < CELL_SIZE + 1; i++) {
			  dashes += "-";
		  }
		  //nested loops to iterate through spreadsheet array
		  for(int r = 0; r < spreadsheet.length; r++) {
			  for(int c = 0; c < spreadsheet[r].length; c++) {
				  if(!addedTop && r == 0) {
					  //print the top left blank box
					  if(c == 0) {
						  System.out.print(blanks.substring(1) + " |");
					  }
					  //prints column headers using unicode values
					  System.out.print(blanks.substring(0, blanks.length()/2) + (char)(c + 65) + blanks.substring(blanks.length()/2+1) + "|");
				  } else {
					  //prints rows headers
					  	if(c == 0) {
					  		System.out.printf(blanks.substring(0, blanks.length()/2-1) + "%2d" + blanks.substring(blanks.length()/2+1) + "|", (r+1));
					  	} 
					  	//gets element at the index in the spreadsheet
					  	Cell element = spreadsheet[r][c];
						if(element == null) {
							System.out.print(blanks + "|");
						} else {
							element.setValues(element.command);
							if(element.toString().length() >= CELL_SIZE) {
								System.out.printf(element.toString().substring(0, CELL_SIZE) + "|");
							} else {
								System.out.printf("%6s |", element);
							}
						}
				  }
			  }
			  System.out.println("");
			  // adds the dashed lines under each cell in the grid
			  for(int i = 0; i <= spreadsheet[r].length; i++) {
				  System.out.print(dashes);
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
	public void addCell(String[] arr, String message) {
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
	
	public void sortCells(String[] arr) {
		int iteration = 0;
		boolean ascending = false;
		if(Character.toUpperCase(arr[0].charAt(arr[0].length() - 1)) == 'A')
			ascending = true;
		Cell[] values = new Cell[(Cell.getColIndex(arr[3]) - Cell.getColIndex(arr[1]) + 1) * (Cell.getRowIndex(arr[3]) - Cell.getRowIndex(arr[1]) + 1)];
		for(int i = Cell.getRowIndex(arr[1]); i < Cell.getRowIndex(arr[3])+1; i++) {
			for(int j = Cell.getColIndex(arr[1]); j < Cell.getColIndex(arr[3])+1; j++) {
				values[iteration] = spreadsheet[i][j];
				iteration++;
			}
		}
		iteration = 0;
		Arrays.sort(values);
		if(ascending) {
			for(int i = Cell.getColIndex(arr[1]); i <= Cell.getColIndex(arr[3]); i++) {
				for(int j = Cell.getRowIndex(arr[1]); j <= Cell.getRowIndex(arr[3]); j++) {
					spreadsheet[j][i] = values[iteration];
					iteration++;
				}
			}
		} else {
			for(int i = Cell.getColIndex(arr[3]); i >= Cell.getColIndex(arr[1]); i--) {
				for(int j = Cell.getRowIndex(arr[3]); j >= Cell.getRowIndex(arr[1]); j--) {
					spreadsheet[j][i] = values[iteration];
					iteration++;
				}
			}
		}
	}
	
	public void setRows(int num) {
		CELL_ROWS = num;
		Cell[][] temp = new Cell[CELL_ROWS][CELL_COLUMNS];
		for(int i = 0; i < Math.min(temp.length, spreadsheet.length); i++) {
			for(int j = 0; j < Math.min(temp[i].length, spreadsheet.length); j++) {
				temp[i][j] = spreadsheet[i][j];
			}
		}
		spreadsheet = temp;
	}
	public void setCols(int num) {
		CELL_COLUMNS = num;
		Cell[][] temp = new Cell[CELL_ROWS][CELL_COLUMNS];
		for(int i = 0; i < Math.min(temp.length, spreadsheet.length); i++) {
			for(int j = 0; j < Math.min(temp[i].length, spreadsheet[i].length); j++) {
				temp[i][j] = spreadsheet[i][j];
			}
		}
		spreadsheet = temp;
	}
}
