package telran.util.memory;

public class MemoryService {

	public static void main(String[] args) {
		
		getAvailableMemoryBlockSize();

	}

	public static int getAvailableMemoryBlockSize() {
//		int size = Integer.MAX_VALUE;
//															System.out.println("0 " + size);


	int left = 0, right = Integer.MAX_VALUE;
	int middle = 0, size = 0;
	byte[]ar ;
	while (left <= right) {
		try {
			middle = (int) (((long)right + left)/2);
			ar = new byte[middle];
			ar = null;
			
			size = middle;
			left = middle + 1;
		} catch (OutOfMemoryError e) {
			right = middle - 1;
		} 
	}
				System.out.println("s " + size);
	return size;
															
															
//															return getMemoryRecurs(size);													
	}




	
	
	
	
	
	
	
	
	// bad realization
	
static int lastgood = 0;
	

static private int getMemoryRecurs(int size) {
	
	byte[] ar = null;
	
	try {
		 ar = new byte[size];
		
		 lastgood = size;
																			System.out.println("try " + size + " last " + lastgood);
		 getMemoryRecurs(size + size / 2);
					
		
		
	} catch (OutOfMemoryError e) {
		if (size <= lastgood) {
																			System.out.println("RESULT " + lastgood);
			return lastgood;
		}																	System.out.println("catch " + size + " last " + lastgood);
		getMemoryRecurs(size / 2);
		

	}
	return lastgood;
	
	
	
}





}
