package telran.measure;

public enum LengthUnit {
	
	MM(1f),CM(10f),IN(25.4f),FT(304.8f),M(1000f);
	
	LengthUnit(float value) {
		this.value = value;
	}

	float value; //number of millimeters
	
	
	
	public float between(Length length1, Length length2) {
		float number1 = length1.getNumber() * (length1.getUnit().getValue() / value);
		float number2 = length2.getNumber() * (length2.getUnit().getValue() / value);
		float res = number2 - number1;
		return  res;

	}
	
	
	public float getValue() {
		return this.value;
	}
	

	
	
	

}
