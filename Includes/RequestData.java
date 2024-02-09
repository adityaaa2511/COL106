package Includes;

public class RequestData {
	public int ISBN;
	public int UserID;
	public MyDate RequestDate;
	public RequestData(int ISBN,int UserID){
		this.ISBN=ISBN;
		this.UserID=UserID;
		MyDate date= new MyDate();
		RequestDate=date;
	}
	public String toString() {
        return "ISBN: " + ISBN + "\nUserID: " + UserID + "\n";
    }
}
