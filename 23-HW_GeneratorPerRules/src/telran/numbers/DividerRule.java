package telran.numbers;

import telran.exceptions.RangeException;
import telran.exceptions.RuleException;

public class DividerRule implements Rule {
public DividerRule(int divider) {
		super();
		this.divider = divider;
	}
int divider;
	@Override
	public void checkRule(int number, int min, int max) throws RuleException {
		if(number < min) {
			throw new RuleException(min - number + getDelta(min, min, max));
		}
		if(number > max) {
			throw new RuleException(max - number + getDelta(max, min, max));
		}
		int delta = getDelta(number, min, max);
		if (delta != 0) {
			throw new RuleException(delta);
		}

	}
	private int getDelta(int number, int min, int max) {
		int remainder = number % divider;
		if (remainder == 0) {
			return 0;
		}
		
		int deltaAfter = divider - remainder;
		if (number - remainder < min && number + deltaAfter > max) {
			throw new RangeException(String.format("impossible"
					+ " find a number divided by %d in range [%d - %d]",
					divider, min, max));
		}
		int delta = 0;
		if (number - remainder < min) {
			delta = deltaAfter; 
		} else if (number + deltaAfter > max) {
			delta = -remainder;
		} else {
			delta = remainder <= deltaAfter ? -remainder : deltaAfter;
		}
		return delta;
		
	}
	
	
	

}
