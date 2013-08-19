package hu.meza.tools.config.Loaders;

import hu.meza.tools.config.Configuration;

import java.util.Properties;

public class EnvironmentVariablesConfiguration implements Configuration {
	private Properties properties = new Properties();

	@Override
	public boolean load() {
		properties.putAll(System.getenv());
		return true;
	}

	@Override
	public Properties properties() {
		return properties;
	}

	@Override
	public String source() {
		return "Environment variables";
	}
}
