package telran;

import telran.util.TreePresentation;
import telran.util.TreeSet;

public class TreeSetTestAppl {

	private static final int N_NUMBERS = 7;

	public static void main(String[] args) {
		TreeSet<Integer> tree = new TreeSet<>();
		fillTree(tree, N_NUMBERS, 1, 7);
		tree.rotatedTreeDisplay();
		System.out.printf("width = %d; height = %d\n",tree.width(),
				tree.height());
		
		
		System.out.println("\n------- print onj treePresentation \n\n");
		TreePresentation<Integer> treePresentation = tree.getTreePresentation();
		System.out.println(treePresentation);
		
		
		
		System.out.println("\n------- start tree balance \n\n");
		tree.balance();
		

}

	
	private static void fillTree(TreeSet<Integer> tree, int nNumbers, int min, int max) {
		if(max - min + 1 < nNumbers) {
			System.out.println("Wrong input data");
			return;
		}
		int number = 0;
		boolean res = false;
		for(int i = 0; i < nNumbers; i++) {
			do {
				number = getRandomNumber(min, max);
				res = tree.add(number);
				
			}while(!res);
		}
	
	}

	
	
	private static int getRandomNumber(int min, int max) {
		return (int) (min + Math.random() * (max - min + 1));
	}

}
