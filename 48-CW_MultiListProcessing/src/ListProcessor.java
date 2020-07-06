import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.*;

public class ListProcessor extends Thread {
	List<Integer> list;
	int nRuns;
	static private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private Lock readLock = lock.readLock();
	private Lock writeLock = lock.writeLock();
	static AtomicLong countLockIterations = new AtomicLong(0);
	int probUpdate;
	
	public ListProcessor(List<Integer> list, int nRuns, int probUpdate) {
		super();
		this.list = list;
		this.nRuns = nRuns;
		this.probUpdate = probUpdate;
	}
	
	public  static long getCountLockIterations() {
		return countLockIterations.get();
	}
	@Override
	public void run() {
		for(int i = 0; i < nRuns; i++) {
			if(chance() < probUpdate) {
				updateList();
			} else {
				readList();
			}
		}
	}
	private int chance() {
		return (int) (Math.random() * 100);
	}
	
	private void updateList() {
		try {
			tryLockCounting(writeLock);
			list.remove(list.size() - 1);
			list.add(100);
		} finally {
			writeLock.unlock();
		}
	}

	private void tryLockCounting(Lock lock) {
		while(!lock.tryLock()){
			countLockIterations.getAndIncrement();
		}
	}

	private void readList() {
		try {
			tryLockCounting(readLock);
			list.get(list.size() - 1);
			list.get(list.size() - 1);
			
		} finally {
			readLock.unlock();
		}
	}

}
