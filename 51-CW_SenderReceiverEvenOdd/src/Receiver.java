import telran.messaging.MessageBox;

public class Receiver extends Thread {
	MessageBox msgBox;
	volatile boolean running;
	
	
	public Receiver(MessageBox msgBox) {
		this.msgBox = msgBox;
	}

	public Receiver() {
		
	}
	
	public void setMessageBox(MessageBox msgBox) {
		this.msgBox = msgBox;
	}


	public void setRunning(boolean value) {
		this.running = value; 
	}
	
	@Override
	public void run() {
		running = true;
		while(running) {
			String message = msgBox.take();
			System.out.printf("Thread with ID: %d - %s \n", getId(), message);
			
		}
		String tempMsg = null;
		while((tempMsg = msgBox.get()) != null) {
			System.out.printf("Thread with ID: %d - %s \n", getId(), tempMsg);
		}
	}
}
