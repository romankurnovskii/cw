package z_std;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		int[] a = { 9, 8, 3, 1, 5, 4 };
		int[] b = { 6, 3, 2, 9, 0 };
		int i;


		
		Application1 application1 = new Application1();
		Application2 application2 = new Application2();
		
		System.out.println("done");


	}



}







class Worker extends Thread {
	
    private final String str;
    
    public Worker(String str) {
        this.str = str;
    }
    
    @Override
    public void run() {
        System.out.println(str);
    }
}



    
    
    






