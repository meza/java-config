package hu.meza.tools.config.Loaders;

import hu.meza.tools.config.Configuration;

import java.util.Map;
import java.util.Properties;

public class SystemPropertiesConfiguration implements Configuration {

	private Properties properties;

	@Override
	public boolean load() {
		properties = System.getProperties();

		Map<String, String> env = System.getenv();
		for (Map.Entry<String, String> var : env.entrySet()) {
			if (properties.contains(var.getKey())) {
				continue;
			}

			properties.put(var.getKey(), var.getValue());

		}

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
