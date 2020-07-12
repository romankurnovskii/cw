import java.lang.reflect.Field;
import java.util.Arrays;

public class AnnotationTestAppl {

	public static void main(String[] args) {

		X obj = new X();
		printIdFieldNames(obj);
	}

	private static void printIdFieldNames(Object obj) {

		Class<?> clazz = obj.getClass();

		Arrays.stream(clazz.getDeclaredFields())
			.filter(f -> f.isAnnotationPresent(id.class))
			.map(Field::getName)
			.forEach(System.out::print);

	}

}
