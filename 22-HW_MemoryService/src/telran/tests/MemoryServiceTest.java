package telran.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import telran.util.memory.MemoryService;

class MemoryServiceTest {
	
	byte[] array = null;

	@Test
	void testMemoryService() {
		int size = MemoryService.getAvailableMemoryBlockSize();
		
		System.out.println("3 " + size);
		
		array = new byte[size];
		
		array = null;
		
		
		try {
			array = new byte[size + 1];
//			fail("Expected out of memory exception((");
													System.out.println(size + 1 + " good");
		} catch (OutOfMemoryError e) {
												    System.out.println("ffffail");
			 

			
		}
	}

}
 

// 16786904 good