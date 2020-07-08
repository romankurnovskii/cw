import telran.messaging.MessageBox;

public class Sender extends Thread {
	MessageBox msgBoxOdd;
	MessageBox msgBoxEven;
	private int nMessages;
	
	public Sender(MessageBox msgBoxOdd, MessageBox msgBoxEven, int nMessages) {
		this.msgBoxOdd = msgBoxOdd;
		this.msgBoxEven = msgBoxEven;
		this.nMessages = nMessages;
	}

	@Override
	public void run() {
		for (int i = 1; i < nMessages+1; i++) {
			if(i % 2 == 0) {
				msgBoxEven.put("Message " + i);
			}else {
				
				msgBoxOdd.put("Message " + i);
			}
//			try {
//				sleep(10);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
		}
	}
}
