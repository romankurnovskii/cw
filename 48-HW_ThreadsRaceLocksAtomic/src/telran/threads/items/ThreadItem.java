package telran.threads.items;

import telran.menu.InputOutput;
import telran.menu.Item;

public abstract class ThreadItem implements Item {
	InputOutput io;
	
	public ThreadItem(InputOutput io) {
		super();
		this.io = io;
	}


}
