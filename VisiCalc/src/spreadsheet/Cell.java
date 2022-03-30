package spreadsheet;

public class Cell {
	//value in cell
	Object value;
	//row number of cell
	int rowIndex;
	//column number of cell
	int colIndex;
	public Cell(String[] arr) {
		this.value = arr[2];
		this.rowIndex = getRowIndex(arr[0]);
		this.colIndex = getColIndex(arr[0]);
		System.out.println(arr[0] + " maps to [" + this.rowIndex + ", " + this.colIndex + "]");
	}
	
	public int getColIndex(String cellAddress) {
		char location = cellAddress.charAt(0);
		//converts letter to number using unicode table
		return (int)(location) - 65;
	}
	
	public int getRowIndex(String cellAddress) {
		String location = cellAddress.substring(1);
		return Integer.parseInt(location) -1;
	}
	
	public String toString() {
		return value.toString();
	}
}