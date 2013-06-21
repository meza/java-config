package hu.meza.tools.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RequiredConfigurationFile implements Configuration {
	private final String resourceName;
	private final Properties properties = new Properties();


	public RequiredConfigurationFile(String resourceName) {
		this.resourceName = resourceName;
	}

	@Override
	public boolean load() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}
		try {
			properties.load(inputStream);
			return true;
		} catch (IOException e) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}

	}

	@Override
	public Properties properties() {
		return properties;
	}
}
