package practice;

import java.util.ArrayList;
import java.util.List;

public class DataStructure {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add(null);
		list.add("1");
		System.out.println(list.get(0));
		System.out.println(list.get(1));
	}
}
