import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class OutputTextStreams {

	public static void main(String[] args) throws FileNotFoundException {

		PrintStream printStream = new PrintStream("ps_file");
		PrintWriter printWriter = new PrintWriter("pw_file");

		printStream.println("Hello");
		printWriter.println("Doesn't write to file");

		
		try (PrintStream printStream2 = new PrintStream("ps_file2");
				PrintWriter printWriter2 = new PrintWriter("pw_file2");) {

			printStream2.println("Hello");
			printWriter2.println("works, because stream is closed by try method");

		}

	}

}
