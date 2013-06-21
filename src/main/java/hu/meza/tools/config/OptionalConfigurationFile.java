package hu.meza.tools.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OptionalConfigurationFile implements Configuration {
	private final String resourceName;
	private final Properties properties = new Properties();

	public OptionalConfigurationFile(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public boolean load() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			return false;
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	@Override
	public Properties properties() {
		return properties;
	}
}
