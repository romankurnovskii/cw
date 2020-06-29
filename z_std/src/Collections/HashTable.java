package Collections;

import java.util.Hashtable;

public class HashTable {

	public static void main(String[] args) {
		
		Hashtable<Integer, String> table=new Hashtable<>();
		table.put(513 , "Alice");
		table.put(401 , "Bob");
		table.put(513 , "Mike");
		table.put(401 , "Kate");
		table.put(513 , "John");
		

		System.out.println(table.entrySet());
		

		System.out.println(table);
	}

}
