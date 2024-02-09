package Includes;
import java.util.*;

public class MyDate {
    public int month;
    public int year;
    public MyDate(){
        Calendar c= Calendar.getInstance();
        this.year=c.get(Calendar.YEAR);
        this.month=c.get(Calendar.MONTH);
    }
}
