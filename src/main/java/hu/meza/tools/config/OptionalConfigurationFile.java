package hu.meza.tools.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class OptionalConfigurationFile implements Configuration {
	private String resourceName;
	private final Properties properties = new Properties();
	private File file;
	private FileType type;

	public OptionalConfigurationFile(File file) {
		this.file = file;
		type = FileType.FILE;

	}

	public OptionalConfigurationFile(String resourceName) {
		this.resourceName = resourceName;
		type = FileType.RESOURCE;
	}

	@Override
	public boolean load() {
		switch (type) {
			case FILE:
				return loadFile();
			case RESOURCE:
				return loadResource();
		}
		return false;
	}

	private boolean loadResource() {
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(resourceName);
		if (inputStream == null) {
			return false;
		}
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			return false;
		}
		return true;
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
			return false;
		}
	}

}
