import telran.messaging.MessageBox;

public class SenderReceiverAppl {
    private static final int N_MESSAGES = 20;
    private static final int N_RECEIVERS = 10;

    public static void main(String[] args) throws InterruptedException {
        MessageBox messageBoxEven = new MessageBox();
        MessageBox messageBoxOdd = new MessageBox();
        Sender sender = new Sender(messageBoxEven, messageBoxOdd, N_MESSAGES);
        Receiver[] receivers = new Receiver[N_RECEIVERS];
        for (int i = 0; i < N_RECEIVERS; i++) {
            receivers[i] = new Receiver();
            receivers[i].setMessageBox(receivers[i].getId() % 2 == 0 ? messageBoxEven : messageBoxOdd);
            receivers[i].start();
        }
        sender.start();
        sender.join();

        for (Receiver receiver : receivers) {
            receiver.setFinished();
            receiver.interrupt();
        }
    }
}
