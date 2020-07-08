import telran.messaging.MessageBox;

public class Sender extends Thread {
	MessageBox msgBox;
	private int nMessages;
	
	public Sender(MessageBox msgBox, int nMessages) {
		this.msgBox = msgBox;
		this.nMessages = nMessages;
	}

	@Override
	public void run() {
		for (int i = 1; i < nMessages+1; i++) {
				msgBox.put("Message " + i);
//			try {
//				sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
