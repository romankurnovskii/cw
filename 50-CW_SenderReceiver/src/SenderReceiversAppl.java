import telran.messaging.MessageBox;

public class SenderReceiversAppl {

	private static final int N_MESSAGES = 20;
	private static final int N_RECEIVERS = 10;

	public static void main(String[] args) throws InterruptedException {
		MessageBox msgBox = new MessageBox();
		Sender sender = new Sender(msgBox, N_MESSAGES);
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		
		for(int i = 0; i < receivers.length; i++) {
			receivers[i] = new Receiver(msgBox);
									
			receivers[i].start();
		}
		
		sender.start();   
			
		sender.join();
		finishReceivers(receivers);
		
	}

	private static void finishReceivers(Receiver[] receivers) {
		for(Receiver receiver : receivers) {
			receiver.setRunning(false);
			receiver.interrupt();
		}
		
	}

}
