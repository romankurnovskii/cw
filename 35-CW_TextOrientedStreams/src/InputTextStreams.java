import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class InputTextStreams {

	public static void main(String[] args) throws IOException {

		BufferedReader reader = Files.newBufferedReader(Paths.get("pw_file2"), Charset.forName("utf-8"));

		
		while(true) {
			String line = reader.readLine();
			if(line == null) {
				break;
			}
			System.out.println(line);
		}
		
		
		System.out.println("Enter password\n");
		String password = getPassword();
		System.out.println("Entered: " + password);
		
		
	}

	private static String getPassword() {
		Scanner scanner = new Scanner(System.in);
		
		
		
		
		Console console = System.console();
		
		return console == null ? scanner.nextLine() : new String(console.readPassword());
	}

}
