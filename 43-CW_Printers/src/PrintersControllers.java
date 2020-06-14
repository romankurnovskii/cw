
public class PrintersControllers {

	private static final int N_SYMBOLS = 100;

	public static void main(String[] args) {
		Printer printer1 = new Printer('#', N_SYMBOLS);
		Printer printer2 = new Printer('+', N_SYMBOLS);
		
		printer1.start();
		printer2.start();
		
		
		
	}

}
