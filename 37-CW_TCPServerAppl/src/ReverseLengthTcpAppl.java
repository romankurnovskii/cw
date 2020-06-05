import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ReverseLengthTcpAppl {

	private static final int PORT = 4000;

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(PORT);
		System.out.println("Server is listening");
		while (true) {
			Socket socket = serverSocket.accept();
			runClient(socket);
		}
	}

	private static void runClient(Socket socket) throws IOException {
		InputStream in = socket.getInputStream();

		try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
				PrintStream writer = new PrintStream(socket.getOutputStream());) {
			while(true) {
				String request = bufferedReader.readLine();
				if(request == null) {
					break;
				}
				String response = getResponse(request);
				writer.println(response);
			}

		} catch (Exception e) {
			// client closed connection by any illegal way
			System.out.println("illegal way of closing connection");
			return;
		}
		System.out.println("client closed protocol");

	}

	private static String getResponse(String request) {
		// request <header># <payload>
		String headersPayload[] = request.split("#");
		
		if(headersPayload.length != 2) {
			return "Inknown req";
		}
		String headers = headersPayload[0];
		String payload = headersPayload[1];
		switch (headers) {
		case "reverse": {
			return getReverse(payload);
		}
		case "length": {
			return getLength(payload);
		}
		default:
			return "Unknown Req";
		}
		
	}

	private static String getLength(String payload) {
		return Integer.toString(payload.length());
	}

	private static String getReverse(String payload) {
		StringBuilder builder = new StringBuilder(payload);
		return builder.reverse().toString();
	}

}
