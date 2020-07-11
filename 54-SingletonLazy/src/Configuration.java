
public class Configuration {

	private int x;
	private static Configuration config;

	private Configuration(int x) throws Exception {
		setX(x);
	}

	private void setX(int x) throws Exception {
		if (x < 0) {
			throw new Exception("negative x value");
		}
		this.x = x;
	}

	private int getX(int x) {
		return x;
	}

	public static Configuration getConfiguration(int x) throws Exception {
		if (config == null) {
			synchronized (Configuration.class) {
				config = new Configuration(x);
			}

			return config;
		}
		return config;

	}
}
