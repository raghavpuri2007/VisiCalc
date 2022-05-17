/*
 * Make header
 * 
 */
public class TextCell extends Cell{
	String textValue = "";
	public TextCell(String[] arr) {
		super(arr);
	}
	
	public void setValues(String[] arr) {
		String value = "";
		for(int i = 3; i < arr.length-1; i++) {
			value += arr[i] + " ";
		}
		this.textValue = value.substring(0, value.length() -1);
	}

	public String toString() {
		return this.textValue;
	}
}
