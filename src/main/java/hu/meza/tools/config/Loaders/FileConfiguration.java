package hu.meza.tools.config.Loaders;

import hu.meza.tools.config.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class FileConfiguration implements Configuration {

	private final Properties properties = new Properties();
	private File file;

	public FileConfiguration(File file) {
		this.file = file;
	}

	@Override
	public boolean load() {
		try {
			InputStream is = new FileInputStream(file);
			properties.load(is);
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	@Override
	public Properties properties() {
		return properties;
	}

	@Override
	public String source() {
		return file.getAbsolutePath();
	}
}
