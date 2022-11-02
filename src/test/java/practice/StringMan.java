package practice;

import org.testng.annotations.Test;

public class StringMan {

	@Test
	public void stringManupulation() {
		String string = "My name is Nasir";
		System.out.println(string.substring(0, 2));
	}
}
