package tests.performance.controller;

import tests.performance.PerformanceCopyFiles;
import util.files.CopyFilesJava;
import util.files.CopyFilesStreams;

public class PerformanceCopyFilesAppl {

	private static final String DEST_FILE = "/Users/User/Videos/2019-12-10 18-08-54.flv";
	private static final String SOURCE_FILE = "2019-12-10 18-08-54.flv";

	public static void main(String[] args) {
		int [] sizes = {
			100,
			100 * 1024 * 1024,
			1024 * 1024 * 1024,
			(int)Runtime.getRuntime().freeMemory()
				
		};
		
		
		
		new PerformanceCopyFiles
		("Java standard copy", 1, new CopyFilesJava(), SOURCE_FILE, DEST_FILE).run();
		for (int size: sizes) {
			new PerformanceCopyFiles
			("Streams with buffer size=" + size, 1, new CopyFilesStreams(size),
					SOURCE_FILE, DEST_FILE).run();
			
		}
		

	}

}
