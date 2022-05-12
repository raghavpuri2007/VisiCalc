//VisiCalc
//Raghav Puri
//AP Computer Science

public class FormulaCell extends Cell {
	String returnValue;
	double formulaValue;
	public FormulaCell(String[] arr, String message) {
		super(arr);
	}
	//stores a formula
	
	public void setValues(String[] arr) {
		this.formulaValue = Double.parseDouble(calculateCell(arr));
		this.returnValue = formulaValue + "";
		if(Math.round(formulaValue) == formulaValue) {
			returnValue = returnValue.substring(0, returnValue.indexOf('.'));
		}
	}
	
	public String calculateCell(String[] arr) {
		double total = 0;
		String finalTotal = "";
		if(arr[3].equalsIgnoreCase("sum")) {
			total = sumCell(arr);
		} else if(arr[3].equalsIgnoreCase("avg")) {
			total = avgCell(arr); 
		} else {
			String currentOperator = "+";
			String[] operators = {"+", "-", "*", "/"};
			for(int i = 3; i < arr.length-1; i++) {
				if(Character.isDigit(arr[i].charAt(0))) {
					total = operation(total, currentOperator, Integer.parseInt(arr[i]));
				} else if(Character.isLetter(arr[i].charAt(0))) {
					total = operation(total, currentOperator, Integer.parseInt(Grid.spreadsheet[getRowIndex(arr[i])][getColIndex(arr[i])].toString()));
				} else {
					for(String operator : operators) {
						if(arr[i].equals(operator)) {
							currentOperator = operator;
						}
					}
				}
			}
		}
		//janky solution to convert int to String
		finalTotal = total + "";
		if(finalTotal.equals(total + "")) {
			finalTotal = finalTotal.substring(0, finalTotal.length() - 2);
		}
		return finalTotal;
	}
	
	public double operation(double sum, String currentOperator, int value) {
		if(currentOperator.equals("+")) {
			return sum + value;
		} else if(currentOperator.equals("-")) {
			return sum - value;
		} else if(currentOperator.equals("*")) {
			return sum * value;
		} else {
			return sum / value;
		}
	}
	//method that returns the sum of a rectangular region
	public double sumCell(String[] arr) {
		double sum = 0;
		for(int r = Cell.getRowIndex(arr[4]); r <= Cell.getRowIndex(arr[6]); r++) {
			for(int c = Cell.getColIndex(arr[4]); c <= Cell.getColIndex(arr[6]); c++) {
				sum += Integer.parseInt(Grid.spreadsheet[r][c].toString());
			}
		}
		return sum;
	}
	//method that returns average of a rectangular region
	public double avgCell(String[] arr) {
		double sum = 0;
		int iteration = 0;
		for(int r = Cell.getRowIndex(arr[4]); r <= Cell.getRowIndex(arr[6]); r++) {
			for(int c = Cell.getColIndex(arr[4]); c <= Cell.getColIndex(arr[6]); c++) {
				sum += Integer.parseInt(Grid.spreadsheet[r][c].toString());
				iteration++;
			}
		}
		//formula to return average
		return sum / iteration;
	}
	
	public String toString() {
		return returnValue;
	}
}
