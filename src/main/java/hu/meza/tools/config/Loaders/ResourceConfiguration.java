package hu.meza.tools.config.Loaders;

import hu.meza.tools.config.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ResourceConfiguration implements Configuration {

	private final Properties properties = new Properties();
	private final String resourceName;

	public ResourceConfiguration(String resourceName) {
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

	@Override
	public String source() {
		return resourceName;
	}
}
