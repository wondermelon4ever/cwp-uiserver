package example.cwp.uiserver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTest {

	public static void main(String[] args) {
		String s = "20200712";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		try {
			Date date = sdf.parse(s);
			System.out.println(date.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
