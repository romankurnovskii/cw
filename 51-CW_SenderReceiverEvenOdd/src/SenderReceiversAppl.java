import telran.messaging.MessageBox;

public class SenderReceiversAppl {

	private static final int N_MESSAGES = 200;
	private static final int N_RECEIVERS = 10;
	private static final int CAPACITY = 200;

	public static void main(String[] args) throws InterruptedException {
		MessageBox msgBoxOdd = new MessageBox(CAPACITY);
		MessageBox msgBoxEven = new MessageBox(CAPACITY);
		Sender sender = new Sender(msgBoxOdd, msgBoxEven, N_MESSAGES);
		Receiver[] receivers = new Receiver[N_RECEIVERS];
		
		for(int i = 0; i < receivers.length; i++) {
			receivers[i] = new Receiver();
			if(receivers[i].getId() % 2 == 0) {
				receivers[i].setMessageBox(msgBoxEven);
			}else {				
				receivers[i].setMessageBox(msgBoxOdd);
			}
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
