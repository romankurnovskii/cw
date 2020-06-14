package telran.menu;

import java.time.LocalDate;
import java.util.function.Function;

public interface InputOutput {
	String inputString(String prompt);

	void display(Object obj);

	default <T> T inputObject(String prompt, String errorMessage, Function<String, T> mapper) {
		T res;
		while (true) {
			String str = inputString(prompt);
			try {
				res = mapper.apply(str);
				if (res != null) {
					break;
				}
			} catch (Exception e) {

			}
			displayLine(errorMessage);
		}
		return res;
	}

	default Integer inputInteger(String prompt, Integer min, Integer max) {

		return inputObject(prompt, String.format("No number in the range [%d-%d]", min, max), s -> {
			Integer res = Integer.parseInt(s);
			return res >= min && res <= max ? res : null;
		});
	}

	default Integer inputInteger(String prompt) {
		String str = "It is not a number";
		Integer res = inputObject(prompt, str, s -> {
			return Integer.parseInt(s);
		});
		return res;
	}

	default void displayLine(Object obj) {
		display(obj.toString() + "\n");
	}

	default LocalDate inputDate(String prompt) {
		String str = "Wrong date";
		LocalDate res = inputObject(prompt, str, data -> {
			return LocalDate.parse(data);
		});

		return res;
	}

	

}
