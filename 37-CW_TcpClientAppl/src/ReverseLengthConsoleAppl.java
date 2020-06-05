import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReverseLengthConsoleAppl {

	private static final String HOST = "localhost";
	private static final int PORT = 4000;

	public static void main(String[] args) throws UnknownHostException, IOException {

		Socket socket = new Socket(HOST, PORT);
		Scanner scanner = new Scanner(System.in);
		
		try (PrintStream writer = new PrintStream(socket.getOutputStream());){
			InputStream in = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			while(true) {
				System.out.println("enter request in the format <type>#<string> or exit");
				String request = scanner.nextLine();
				
				if (request.equalsIgnoreCase("exit")) {
					break;
				}
				
				writer.println(request);
				String response = reader.readLine();
				if(response == null) {
					break;
				}
				System.out.println(response);
			}
		}
	}

}
