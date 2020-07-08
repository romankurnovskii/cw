package telran.messaging;

public class MessageBox {
	private String message;

	public void put(String message) {
		synchronized (this) {
			while(this.message != null) {
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.message = message;
			this.notifyAll();
		}
	}
	
	public String take() throws InterruptedException {
		synchronized (this) {
			while(message == null) {
				this.wait();
			}
			String res = message;
			message = null;
			this.notify();
			return res;
		}
	}
	
	public String get() {
		synchronized (this) {
			String res = message;
			message = null;
			return res;
		}
	}
}
