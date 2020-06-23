
public class PrinterControler {

	protected static int N_PRINTERS = 5;
	protected static int N_NUMBERS = 100;
	protected static int N_PORTIONS = 9;
	private static Printer[] printers;

	public static void main(String[] args) throws Exception {

		createPrinters(N_PRINTERS, N_PORTIONS, N_NUMBERS);

		startPrinters();

	}

	private static void createPrinters(int nPrinters, int portions, int numbers) {
		printers = new Printer[nPrinters];
		for (int i = 0; i < nPrinters; i++) {
			printers[i] = new Printer(i + 1, portions, numbers);
		}
	}

	private static void startPrinters() throws InterruptedException {

		for (Printer printer : printers) {
			printer.start();
		}

		int i;		
		int iterations = N_NUMBERS / N_PORTIONS;
		for (i = 0 ; i < iterations; i++) {
			for (Printer printer : printers) {
				
				Thread.sleep(1);
				
				printer.interrupt();
			}
		}


	}



}
