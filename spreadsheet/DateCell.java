//VisiCalc
//Raghav Puri
//AP Computer Science

public class DateCell extends Cell{
	int day;
	int month;
	int year;
	public DateCell(String[] arr) {
		super(arr);
	}
	
	public void setValues(String[] arr) {
		String[] date = arr[2].split("/");
		this.month = Integer.parseInt(date[0]);
		this.day = Integer.parseInt(date[1]);
		this.year = Integer.parseInt(date[2]);
	}
	
	public static int getDay(DateCell cell) {
		return cell.day;
	}
	
	public static int getMonth(DateCell cell) {
		return cell.month;
	}
	
	public static int getYear(DateCell cell) {
		return cell.year;
	}
	
	public String toString() {
		return this.month + "/" + this.day + "/" + this.year;
	}
	
}
