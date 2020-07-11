
public class Main {

	public static void main(String[] args) {

		Configuration config1, config2;
		config1 = SingletonEager.getConfiguration(10);
		config2 = SingletonEager.getConfiguration(5);

		System.out.println(config1.equals(config2));
	}

}
