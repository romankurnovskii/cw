
public class PrinterControler {

	protected static int N_PRINTERS = 4;
	protected static int N_NUMBERS = 100;
	protected static int N_PORTIONS = 9;

	public static void main(String[] args) {

		startPrinters(N_PRINTERS, N_PORTIONS, N_NUMBERS);

	}

	private static void startPrinters(int count, int portions, int numbers) {
		for (int i = 1; i <= count; i++) {
			Printer printer = new Printer(i, portions, numbers);
			printer.start();
		}

	}

}
