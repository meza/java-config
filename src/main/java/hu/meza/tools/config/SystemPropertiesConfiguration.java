package hu.meza.tools.config;

import java.util.Properties;

public class SystemPropertiesConfiguration implements Configuration {

	private Properties properties;

	@Override
	public boolean load() {
		properties = System.getProperties();
		return true;
	}

	@Override
	public Properties properties() {
		return properties;
	}
}