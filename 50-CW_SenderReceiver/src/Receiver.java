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
			try {
				String message = msgBox.take();
				System.out.printf("Thread with ID: %d - %s \n", getId(), message);
			} catch (InterruptedException e) {
				break ;
			}
			
		}
		String tempMsg = msgBox.get();
		if(tempMsg != null ) {
			System.out.printf("Thread with ID: %d - %s \n", getId(), tempMsg);
		}
	}
}
