//VisiCalc
//Raghav Puri
//AP Computer Science

public class Cell implements Comparable<Cell>{
	//Command entered by user (tokenized)
	String[] command;
	//value in cell
	int cellNumber;
	//row number of cell
	int rowIndex;
	//column number of cell
	int colIndex;
	public Cell(String[] arr) {
		this.rowIndex = getRowIndex(arr[0]);
		this.colIndex = getColIndex(arr[0]);
		// System.out.println(arr[0] + " maps to [" + this.rowIndex + ", " + this.colIndex + "]");
		//^ This is for earlier checkpoint, not used anymore
		this.command = arr;
		setValues(arr);
	}
	
	public void setValues(String[] arr) {
		this.cellNumber = Integer.parseInt(arr[2]);
	}
	//returns the cell's column index
	public static int getColIndex(String cellAddress) {
		char location = cellAddress.charAt(0);
		location = Character.toUpperCase(location);
		//converts letter to number using unicode table
		
		return (int)(location) - 65;
	}
	//returns the cell's row index
	public static int getRowIndex(String cellAddress) {
		String location = cellAddress.substring(1);
		return Integer.parseInt(location) -1;
	}
	
	@Override
	//overriden compareTo method that is used to compare Cells
	public int compareTo(Cell other) {
		//Compare DateCell
		if(this instanceof FormulaCell) {
			//if both are FormulaCell's
			if(other instanceof FormulaCell) {
				return Integer.parseInt(this.toString()) - Integer.parseInt(other.toString());
			}
			//If anything other than FormulaCell
			return -1;
		} else if(this instanceof DateCell) {
			if(other instanceof DateCell) {
				//if both are DateCell
				if(DateCell.getYear((DateCell) this) - DateCell.getYear((DateCell) other) == 0) {
					//checks the months
					if(DateCell.getMonth((DateCell) this) - DateCell.getMonth((DateCell)other) == 0) {
						//returns the difference in the days
						return DateCell.getDay((DateCell) this) - DateCell.getDay((DateCell) other);
					} else {
						return DateCell.getMonth((DateCell) this) - DateCell.getMonth((DateCell) other);
					}
				} else {
					return DateCell.getYear((DateCell) this) - DateCell.getYear((DateCell) other);
				}
			} else if(other instanceof FormulaCell) {
				return 1;
			} else {
				//If Cell or TextCell
				return -1;
			}
		} else if(this instanceof TextCell) {
			if(other instanceof TextCell) {
				for(int i = 0; i < Math.min(this.toString().length(), other.toString().length()); i++) {
					if(this.toString().charAt(i) - other.toString().charAt(i) != 0) {
						return this.toString().charAt(i) - other.toString().charAt(i);
					}
				return 0;
				}
			} else if(other instanceof FormulaCell || other instanceof DateCell) {
				return -1;
			} else {
				//If Cell
				return 1;
			}
		} else {
			//Normal Cell
			if(other instanceof Cell) {
				return Integer.parseInt(this.toString()) - Integer.parseInt(other.toString());
			}
		}
		return 1;
	}

	public String toString() {
		//Mr. Mulvaney wanted it to be an integer so thats why I have to convert it to a string                                                unfortunately
		return cellNumber+"";
	}

}
