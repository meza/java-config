package hu.meza.tools.config;

import hu.meza.tools.config.Loaders.FileConfiguration;
import hu.meza.tools.config.Loaders.ResourceConfiguration;

import java.io.File;
import java.util.Properties;

public class Optional implements Configuration {


	private final Configuration config;

	public Optional(File file) {
		this(new FileConfiguration(file));

	}

	public Optional(String resourceName) {
		this(new ResourceConfiguration(resourceName));
	}

	public Optional(Configuration config) {
		this.config = config;
	}

	@Override
	public boolean load() {
		return config.load();
	}

	@Override
	public Properties properties() {
		return config.properties();
	}

	@Override
	public String source() {
		return config.source();
	}

}
