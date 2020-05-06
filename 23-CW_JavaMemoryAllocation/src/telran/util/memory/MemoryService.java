package telran.util.memory;

public class MemoryService {
public static int getAvailableMemoryBlockSize(int limit) {
	int left = 0, right = limit;
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
	return size;
	
}
}
