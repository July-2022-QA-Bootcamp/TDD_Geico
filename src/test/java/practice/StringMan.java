package practice;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;

public class StringMan {

	@Test
	public void stringManupulation() {
		String string = "My name is Nasir";
		System.out.println(string.substring(0, 2));
	}
	
	@Test
	public void dateMan() {
		Date date = new Date();
		SimpleDateFormat dateFormat =new SimpleDateFormat("_MMddyyyy_hhmmss");
		System.out.println(dateFormat.format(date));
	}
}
