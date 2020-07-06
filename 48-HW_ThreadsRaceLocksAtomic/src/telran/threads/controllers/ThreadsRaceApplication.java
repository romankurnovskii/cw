package telran.threads.controllers;

import telran.menu.*;
import telran.threads.items.ThreadRaceSimpleItem;
import telran.threads.items.ThreadRaceTableViewResultItem;

public class ThreadsRaceApplication {
	public static void main(String[] args) {
		InputOutput io = new ConsoleInputOutput();
		Item[] items = {
				new ThreadRaceSimpleItem(io),
				new ThreadRaceTableViewResultItem(io),
				new ExitItem(),
		};
		Menu menu = new Menu(items, io);
		menu.menuRun();
	}
}
