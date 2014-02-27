package hu.meza.tools.config;

import java.util.Map;
import java.util.Properties;

public class Config {

	private final Properties props = new Properties();
	private final Config parentConfig;
	private final Properties higherOrder = new Properties();

	public Config() {
		parentConfig = null;
	}

	public Config(Config globalConfig) {
		parentConfig = globalConfig;
	}

	public void add(Configuration config) {
		config.load();
		Properties toBeAdded = config.properties();
		for (Map.Entry<Object, Object> entry : toBeAdded.entrySet()) {
			addNonOverriding((String) entry.getKey(), (String) entry.getValue());
		}

	}

	public void add(Configuration config, String namespace) {
		config.load();
		Properties toBeAdded = config.properties();
		for (Map.Entry<Object, Object> entry : toBeAdded.entrySet()) {
			final String key = String.format("%s.%s", namespace, entry.getKey());
			addNonOverriding(key, (String) entry.getValue());
		}
	}

	public void addNonOverriding(Configuration config) {
		add(config);
	}

	public void addNonOverriding(Configuration config, String namespace) {
		add(config, namespace);
	}

	public void addOverriding(Configuration config) {
		config.load();
		Properties toBeAdded = config.properties();

		for (Map.Entry<Object, Object> entry : toBeAdded.entrySet()) {
			addOverriding((String) entry.getValue(), (String) entry.getKey());
		}
	}

	public void addOverriding(Configuration config, String namespace) {
		config.load();
		Properties toBeAdded = config.properties();

		for (Map.Entry<Object, Object> entry : toBeAdded.entrySet()) {
			final String key = String.format("%s.%s", namespace, (String) entry.getKey());
			addOverriding((String) entry.getValue(), key);
		}
	}

	public void addHighOrder(Configuration config) {
		config.load();
		Properties toBeAdded = config.properties();

		for (Map.Entry<Object, Object> entry : toBeAdded.entrySet()) {
			higherOrder.setProperty((String) entry.getKey(), (String) entry.getValue());
		}

	}

	public String get(String someSetting) {

		if (higherOrderHas(someSetting)) {
			return higherOrder(someSetting);
		}

		if (!props.containsKey(someSetting)) {
			if (parentConfig != null) {
				return parentConfig.get(someSetting);
			}
		}
		return getProperty(someSetting);
	}

	public String get(String testKey, String defaultValue) {
		try {
			return get(testKey);
		} catch (ConfigurationNotFoundException notFound) {
			return defaultValue;
		}
	}

	protected String higherOrder(String someSetting) {
		if (parentConfig == null) {
			return higherOrder.getProperty(someSetting);
		}
		return parentConfig.higherOrder(someSetting);
	}

	protected boolean higherOrderHas(String someSetting) {
		if (parentConfig == null) {
			return higherOrder.containsKey(someSetting);
		}

		return parentConfig.higherOrderHas(someSetting);
	}

	private String getProperty(String someSetting) {
		final String result = props.getProperty(someSetting);
		if (result == null) {
			throw new ConfigurationNotFoundException(someSetting);
		}
		return result;
	}

	private synchronized void addOverriding(String value, String key) {
		props.setProperty(key, value);
	}

	private synchronized void addNonOverriding(String key, String value) {
		if (!props.containsKey(key)) {
			props.setProperty(key, value);
		}
	}

}
