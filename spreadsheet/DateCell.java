//VisiCalc
//Raghav Puri
//AP Computer Science
public class DateCell extends Cell{
	int day;
	int month;
	int year;
	public DateCell(String[] arr) {
		super(arr);
		this.setDateValues(arr);
	}
	
	public void setDateValues(String[] arr) {
		String[] date = arr[2].split("/");
		this.month = Integer.parseInt(date[0]);
		this.day = Integer.parseInt(date[1]);
		this.year = Integer.parseInt(date[2]);
	}
	
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}
}
