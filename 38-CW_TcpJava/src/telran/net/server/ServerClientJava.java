package telran.net.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import telran.net.RequestJava;
import telran.net.ResponseJava;

public class ServerClientJava implements Runnable {

	ObjectInputStream input;
	ObjectOutputStream output;
	Socket socket;
	ProtocolJava protocol;
	
	@Override
	public void run() {
		
		try {
			while(true) {
				RequestJava request = (RequestJava) input.readObject();
				ResponseJava response = protocol.getResponse(request);
				output.writeObject(response);
			}
		} catch (EOFException e) {
			System.out.println("client closed connection");
		} 
		catch (Exception e) {
			System.out.println("illegal closing exception");
		} 
		
	}
	
	public ServerClientJava(Socket socket, ProtocolJava protocolJava ) {
		this.socket = socket;
		this.protocol = protocolJava;
		try {
			this.input = new ObjectInputStream(socket.getInputStream());
			this.output = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

}
