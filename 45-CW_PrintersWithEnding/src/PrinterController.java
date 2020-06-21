import java.util.Scanner;

public class PrinterController {
	


	public static void main(String[] args) {
		String[] strings = { "Hello", "Java", "12345" };
		Printer[] printers = new Printer[strings.length];
		startPrinters(printers, strings);

		Scanner scanner = new Scanner(System.in);
		String line = null;
		while (true) {
			line = scanner.nextLine();
			if (line.equals("q")) {
				break;
			}
			Integer threadIndex = getThreadIndex(line, printers.length);
			if (threadIndex == null) {
				System.out.println("wrong thread index " + line);
				continue;
			}
			printers[threadIndex].interrupt();
		}
		finishAllThreads(printers);

	}

	private static Integer getThreadIndex(String line, int length) {
		Integer res = null;
		try {
			res = Integer.valueOf(line);
			if (res < 0 || res >= length) {
				throw new RuntimeException();
			}
		} catch (Exception e) {
			res = null;
		}
		return res;
	}

	private static void finishAllThreads(Printer[] printers) {
		for (Printer printer : printers) {
			printer.setRunning(false);
		}
	}

	private static void startPrinters(Printer[] printers, String[] strings) {
		for (int i = 0; i < printers.length; i++) {
			printers[i] = new Printer(strings[i]);
			printers[i].start();
		}

	}

}
