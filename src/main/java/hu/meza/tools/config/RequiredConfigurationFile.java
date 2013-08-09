package hu.meza.tools.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RequiredConfigurationFile implements Configuration {
	private final Properties properties = new Properties();
	private String resourceName;
	private File file;
	private FileType type;

	public RequiredConfigurationFile(String resourceName) {
		this.resourceName = resourceName;
		type = FileType.RESOURCE;
	}


	public RequiredConfigurationFile(File file) {
		this.file = file;
		type = FileType.FILE;
	}

	@Override
	public boolean load() {

		switch (type) {
			case RESOURCE:
				return loadResource();
			case FILE:
				return loadFile();
		}
		throw new RuntimeException("This shouldn't happen");
	}

	@Override
	public Properties properties() {
		return properties;
	}

	private boolean loadFile() {
		try {
			InputStream is = new FileInputStream(file);
			properties.load(is);
			return true;
		} catch (Exception e) {
			throw new RuntimeException("Could not load required configuration: " + file.getAbsolutePath());
		}
	}

	private boolean loadResource() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}

		try {
			properties.load(inputStream);
			return true;
		} catch (IOException e) {
			throw new RuntimeException("Could not load required configuration: " + resourceName);
		}
	}
}
