package telran.games.controllers;

import telran.games.impl.BullsCowsGameImpl;
import telran.games.interfaces.GuessGame;
import telran.games.net.GuessGameProtocol;
import telran.net.server.ProtocolJava;
import telran.net.server.ServerJava;

public class GuessGameTcpAppl {

	private static final int PORT = 4000;

	public static void main(String[] args) {
		GuessGame game = new BullsCowsGameImpl();
		ProtocolJava protocol = new GuessGameProtocol(game );
		ServerJava server = new ServerJava(protocol , PORT);
		server.run();

	}

}
