package telran.net.server;

import java.io.IOException;
import java.net.*;

public class ServerJava implements Runnable {

	ProtocolJava protocol;
	ServerSocket serverSocket;
	int port;

	@Override
	public void run() {
		try {
			while (true) {
				Socket socket = serverSocket.accept();
				ServerClientJava client = new ServerClientJava(socket, protocol);
				client.run();
			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

	public ServerJava(ProtocolJava protocol, int port) {

		this.protocol = protocol;
		this.port = port;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
