package telran.numbers;

import telran.exceptions.RuleException;

public class Generator {

int min;
int max;
Rule rule;
public Rule getRule() {
	return rule;
}
public void setRule(Rule rule) {
	this.rule = rule;
}
public Generator(int min, int max, Rule rule) {
	if (max <= min) {
		throw new IllegalArgumentException(" max should be greater than min");
	}
	this.min = min;
	this.max = max;
	this.rule = rule;
}
public int[] generate(int nNumbers) {
	int [] res = new int[nNumbers];
	for (int i = 0; i < nNumbers; i++) {
		res[i] = getNumber();
	}
	return res;
}
private int getNumber() {
	int res = (int) (min + Math.random() * (max - min + 1));
	try {
		rule.checkRule(res, min, max);
	} catch (RuleException e) {
		res += e.getDelta();
	}
	return res;
}

}
