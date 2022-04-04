//VisiCalc
//Raghav Puri
//AP Computer Science

public class Cell {
	//value in cell
	String value;
	//row number of cell
	int rowIndex;
	//column number of cell
	int colIndex;
	public Cell(String[] arr) {
		this.value = arr[2];
		this.rowIndex = getRowIndex(arr[0]);
		this.colIndex = getColIndex(arr[0]);
		// System.out.println(arr[0] + " maps to [" + this.rowIndex + ", " + this.colIndex + "]");
		//^ This is for earlier checkpoint, not used anymore
	}
	
	public static int getColIndex(String cellAddress) {
		char location = cellAddress.charAt(0);
		location = Character.toUpperCase(location);
		//converts letter to number using unicode table
		
		return (int)(location) - 65;
	}
	
	public static int getRowIndex(String cellAddress) {
		String location = cellAddress.substring(1);
		return Integer.parseInt(location) -1;
	}
	
	public String toString() {
		return value;
	}
}
