package z_std;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int a = scanner.nextInt();
		int b = scanner.nextInt();
		
		
		int var1 = 100;
		int var2 = 0;

		switch (var1) {            
	    case 100:
	        var2 += var1;
	    case 200:
	        var2 += var1 / 4;
	    case 300:
	        var2 += var1 / 10;
	    default:
	        var2 = 500;
	}
		
		System.out.println(var2);

		System.out.println(var1);
		
	}
//d
}
