package telran.messaging;

import java.util.concurrent.*;

public class MessageBox {
	BlockingQueue<String> messages;

	public MessageBox(int capacity) {
		this.messages = new ArrayBlockingQueue<String>(capacity);
	}
	
	public void put(String message) {
		try {
			messages.put(message);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String take() {
		String message = "";
		try {
			message = messages.take();
			 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return message;
		
	}
	
	public String get() {
		
		String res = null;
		res = messages.poll();
		return res;
	}
	
	
}
