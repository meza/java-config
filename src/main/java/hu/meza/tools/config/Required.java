package hu.meza.tools.config;

import hu.meza.tools.config.Loaders.FileConfiguration;
import hu.meza.tools.config.Loaders.ResourceConfiguration;

import java.io.File;
import java.util.Properties;

public class Required implements Configuration {
	private final Configuration config;

	public Required(String resourceName) {
		this(new ResourceConfiguration(resourceName));
	}


	public Required(File file) {
		this(new FileConfiguration(file));
	}

	public Required(Configuration config) {
		this.config = config;
	}

	@Override
	public boolean load() {
		boolean success = config.load();

		return success;
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
