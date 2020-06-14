
public class Printer extends Thread {

	public Printer(char symbol, int nSymbols) {
		this.symbol = symbol;
		this.nSymbols = nSymbols;
	}

	@Override
	public void run() {
		for(int i = 0; i < nSymbols; i++) {
			System.out.println(symbol);
		}
		super.run();
	}

	char symbol;
	int nSymbols;

}
