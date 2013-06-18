package hu.meza.tools.config;

import java.util.Properties;

public class SystemPropertiesConfiguration implements Configuration {

	private final Properties properties;

	public SystemPropertiesConfiguration() {
		properties = System.getProperties();
	}

	@Override
	public Properties properties() {
		return properties;
	}
}
