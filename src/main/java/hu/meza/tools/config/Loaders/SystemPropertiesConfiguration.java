package hu.meza.tools.config.Loaders;

import hu.meza.tools.config.Configuration;

import java.util.Properties;

public class SystemPropertiesConfiguration implements Configuration {

	private Properties properties = new Properties();

	@Override
	public boolean load() {
		properties = System.getProperties();
		return true;
	}

	@Override
	public Properties properties() {
		return properties;
	}

	@Override
	public String source() {
		return "system properties";
	}
}
