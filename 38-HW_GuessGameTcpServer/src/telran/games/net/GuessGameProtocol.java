package telran.games.net;

import java.io.Serializable;
import java.util.HashMap;
import java.util.function.Function;
import static telran.games.api.GuessGameApi.*;
import telran.games.interfaces.GuessGame;
import telran.net.RequestJava;
import telran.net.ResponseJava;
import telran.net.TcpResponseCode;
import telran.net.server.ProtocolJava;

public class GuessGameProtocol implements ProtocolJava {
GuessGame game;
HashMap<String, Function<Serializable, ResponseJava>> mapFunctions;
public GuessGameProtocol(GuessGame game) {
	this.game = game;
	fillMapFunctions();
}
	
	private void fillMapFunctions() {
	mapFunctions = new HashMap<>();
	mapFunctions.put(START, this::start);
	mapFunctions.put(MOVE, this::move);
	mapFunctions.put(PROMPT, this::prompt);
	mapFunctions.put(IS_FINISHED, this::isFinished);
	
}

	@Override
	public ResponseJava getResponse(RequestJava request) {
		Function<Serializable, ResponseJava> fn =
				mapFunctions.getOrDefault(request.requestType, this::wrongRequest);
		
		return fn.apply(request.requestData);
	}
	ResponseJava start(Serializable requestData) {
		try {
			String res = game.startGame();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	ResponseJava prompt(Serializable requestData) {
		try {
			String res = game.prompt();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	ResponseJava move(Serializable requestData) {
		try {
			String res = game.move((String)requestData);
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	ResponseJava isFinished(Serializable requestData) {
		try {
			boolean res = game.isFinished();
			ResponseJava response = new ResponseJava(TcpResponseCode.OK, res);
			return response;
		} catch (Exception e) {
			return new ResponseJava(TcpResponseCode.UNKNOWN, e.getMessage());
		}
	}
	ResponseJava wrongRequest(Serializable requestData) {
		return new ResponseJava(TcpResponseCode.WRONG_REQUEST, "Type of request not found" );
	}
	

}
