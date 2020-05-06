package telran.measure;

public class Length {

	public float getNumber() {
		return number;
	}

	public void setNumber(float number) {
		this.number = number;
	}

	public LengthUnit getUnit() {
		return unit;
	}

	public void setUnit(LengthUnit unit) {
		this.unit = unit;
	}

	float number;
	LengthUnit unit;

	public static void main(String[] args) {

		Length length1 = new Length(10.f, LengthUnit.M);
		Length length2 = new Length(1000.f, LengthUnit.CM);

	}

	public Length(float number, LengthUnit unit) {
		this.number = number;
		this.unit = unit;

	}

	public Length plus(Length length) {

		Length length_new = length.convert(this.unit);

		Length res = new Length(this.number + length_new.number, this.unit);

		return res;
	}

	public Length minus(Length length) {

		Length length_new = length.convert(this.unit);

		Length res = new Length(this.number - length_new.number, this.unit);

		return res;
	}

	public Length convert(LengthUnit unit) {

		float a, b, c, d, e;

		b = this.number;
		c = this.unit.getValue();
		d = unit.getValue();

		// переведу
		e = c * b / d;

		Length res = new Length(e, unit);

		a = res.number;

		return res;
	}

	// by all fields
	public boolean equals(Object obj) {

		if (obj instanceof Length) {

			Length compLength = (Length) obj;

			if (this.number == compLength.number && this.unit == compLength.unit) {

				return true;
			}

		}

		return false;
	}

	// format example, 20.0M means 20 meters
	public String toString() {
		return number + unit.toString();
	}

}
