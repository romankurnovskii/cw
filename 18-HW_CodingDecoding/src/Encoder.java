
public class Encoder {
	
	
	public Encoder(String encodingString) {
		super();
		this.encodingString = encodingString;
	}

	String encodingString;
	int base;
	
	String code (int number) {
		String res = "";
		do {
			int remainder = number % base;
			res = encodingString.charAt(remainder) + res;
			number /= base; //number = number / base
		} while (number != 0);
		
		return res;
	}
	


	
	public boolean decode(String string) {
		int res = 0;
		boolean sign = false;
		if (string.charAt(0) == '-') {
			sign = true;
			string = string.substring(1);
		}
		for(char symb: string.toCharArray()) {
			int index = encodingString.indexOf(symb);
			if (index < 0) {
				return sign;
			}
			res = res * base + index;
		}
		return sign;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
