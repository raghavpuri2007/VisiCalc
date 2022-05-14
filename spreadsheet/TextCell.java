/*
 * Make header
 * 
 */
public class TextCell extends Cell{
	String textValue;
	public TextCell(String[] arr) {
		super(arr);
	}
	
	public void setValues(String[] arr) {
		this.textValue = arr[3];
	}

	public String toString() {
		return this.textValue;
	}
}
