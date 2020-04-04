package telran;

import telran.util.TreeSet;

public class TreeSetTestAppl {

	private static final int N_NUMBERS = 10;

	public static void main(String[] args) {

		TreeSet<Integer> tree = new TreeSet<>();

		fillTree(tree, N_NUMBERS, 1, 15);

		System.out.printf("w=%d h=%d \n\n", tree.width(), tree.height());

		tree.rotatedTreeDisplay();

		System.out.println("------------------------------------ \n\n\n");

		tree.addToList();


		
		System.out.println("------------------------------------ \n\n\n");
		
		
		

	}

	private static void fillTree(TreeSet<Integer> tree, int nNumbers, int min, int max) {
		if (max - min + 1 < nNumbers) {
			System.out.println("Wrong input data");
			return;
		}
		int number = 0;
		boolean res = false;
		for (int i = 0; i < nNumbers; i++) {
			do {
				number = getRandomNumber(min, max);
				res = tree.add(number);

			} while (!res);
		}

	}

	private static int getRandomNumber(int min, int max) {

		return (int) (min + Math.random() * (max - min + 1));
	}

}
