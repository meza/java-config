package hu.meza.tools.config;

import java.util.Properties;

public class EnvironmentVariablesConfiguration implements Configuration {
	private Properties properties;

	@Override
	public boolean load() {
		properties = new Properties();
		properties.putAll(System.getenv());
		return true;
	}

	@Override
	public Properties properties() {
		return properties;
	}
}
