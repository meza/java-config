package com.gu.config;

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
	public Properties properties() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			return properties;
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			return properties;
		}
		return properties;
	}
}
