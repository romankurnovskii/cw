package telran.net.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import telran.net.RequestJava;
import telran.net.ResponseJava;

public class Server implements Runnable, ProtocolJava {

	int port;
	ProtocolJava protocol;
	ServerSocket serverSocket;

	@Override
	public ResponseJava getResponse(RequestJava request) {
		// TODO Auto-generated method stub
		return null;
	}

	public Server(int port, ProtocolJava protocol) {
		this.port = port;
		this.protocol = protocol;
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	@Override
	public void run() {

		try {
			while (true) {
				Socket socket =  serverSocket.accept();
				ServerClientJava clientJava = new ServerClientJava(socket, protocol);
				clientJava.run();

			}
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}

	}

}
