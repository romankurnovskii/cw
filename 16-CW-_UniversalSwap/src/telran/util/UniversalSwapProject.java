package telran.util;

public class UniversalSwapProject {

	public static void main(String[] args) {


		int a =10, b=20;
		
		swap(a,b);
		System.out.printf("a=%d,b=%d",a, b);
		
	}
	
	

	private static <T> void swap(T a, T b) {
		
		
		T tmp =a;
		a = b;
		b = tmp;




	}

}
