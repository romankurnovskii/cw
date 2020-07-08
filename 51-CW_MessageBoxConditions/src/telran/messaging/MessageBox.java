package telran.messaging;

import java.util.concurrent.locks.*;

public class MessageBox {
	private String message;
	private Lock lock;
	private Condition messageFull;
	private Condition messageEmpty;
	
	//	private Condition consumerWait;
	//	private Condition producerWait;
	
	public MessageBox() {
		lock = new ReentrantLock();
		messageFull = lock.newCondition();
		messageEmpty = lock.newCondition();

	}

	public void put(String message) {
		try {
			lock.lock();
			while(this.message != null) {
				try {
					messageFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			this.message = message;
			messageEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public String take() throws InterruptedException {
		try {
			lock.lock();
			while(message == null) {
				messageEmpty.await();
			}
			String res = message;
			message = null;
			messageFull.signal();
			return res;
			
		} finally {
			lock.unlock();
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
