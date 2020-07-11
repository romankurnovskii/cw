import java.lang.reflect.Constructor;

public class ClassReflectionScenarioius {

	public static void main(String[] args) {
		if (args.length == 0) {
			System.out.print("it should contain a class name");
			return;
		}
		X x = null;

//		if (args[0].equals("y")) {
//			x = new Y();
//
//		} else {
//			x = new Z();
//		}

		try {
			Class<?> clazz = Class.forName(args[0]);
			System.out.println(clazz.getName());
			Constructor constructor = clazz.getConstructor();
			x = (X) constructor.newInstance();
			x.action();

		} catch (ClassNotFoundException e) {
			System.out.println(args[0] + " not found");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
