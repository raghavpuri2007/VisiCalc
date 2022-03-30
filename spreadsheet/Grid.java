package spreadsheet;

public class Grid {
	static Cell[][] spreadsheet = new Cell[10][7];
	
	//method to print out the grid
	public void printGrid() {

		  boolean addedTop = false;
		  //default dashed lines
		  String sevenDashes = "-------";
		  //default blank spaces
		  String fiveBlanks = "     ";
		  for(int r = 0; r < spreadsheet.length; r++) {
			  for(int c = 0; c < spreadsheet[r].length; c++) {
				  if(!addedTop && r == 0) {
					  if(c == 0) {
						  System.out.print(fiveBlanks + " |");
					  }
					  //prints column headers
					  System.out.print(fiveBlanks.substring(0, 2) + (char)(c + 65) + fiveBlanks.substring(3) + "|");
				  } else {
					  //prints rows headers
					  	if(c == 0) {
					  		System.out.printf(fiveBlanks.substring(0, 2) + "%2d" + fiveBlanks.substring(3) + "|", (r+1));
					  	} 
					  	//gets element at the index in the spreadsheet
					  	Cell element = spreadsheet[r][c];
						if(element == null) {
							System.out.print(fiveBlanks + "|");
						} else {
							System.out.printf("%4s |", element);
						}
				  }
			  }
			  System.out.println("");
			  // adds the dashed lines under each cell in the grid
			  for(int i = 0; i < spreadsheet[r].length; i++) {
				  System.out.print(sevenDashes);
			  }
			  System.out.println("");
			  //resets the row back to the first row
			  if(!addedTop) {
				  addedTop = true;
				  r--;
			  }
		  }
	  }
	public void addCell(String[] arr) {
		Cell cell = new Cell(arr);
		spreadsheet[cell.rowIndex][cell.colIndex] = cell;
		
	}
}