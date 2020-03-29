package telran;

public class TwoDimensionalArrayTestAppl {
public static void main(String[] args) {
	int ar[][] = new int[3][];
	ar[0] = new int[2];
	ar[0][0] = 10;
	ar[0][1] = -5;
	ar[1] = new int[1];
	ar[1][0] = 20;
	ar[2] = new int[3];
	ar[2][0] = 13;
	ar[2][1] = 8;
	ar[2][2] = 7;
	displayTwoDimensionalArray(ar);
	
}

private static void displayTwoDimensionalArray(int[][] ar) {
	for (int i = 0; i < ar.length; i++) {
		for (int j = 0; j < ar[i].length; j++) {
			System.out.print(ar[i][j] + " ");
		}
	}
	
}



}