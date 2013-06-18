package com.gu.config;

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
	public Properties properties() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}
		return properties;
	}
}
