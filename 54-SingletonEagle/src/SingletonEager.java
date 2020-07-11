
public class SingletonEager {
	static Configuration config;

	static {
		try {
			config = new Configuration(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SingletonEager() {

	}
	
	static public Configuration getConfiguration(int x) {
		config.setX(x);
		return config;
		
	}
}
