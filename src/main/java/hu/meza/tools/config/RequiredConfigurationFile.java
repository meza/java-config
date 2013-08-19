package hu.meza.tools.config;

import java.io.File;

/**
 * @deprecated use Required instead
 */
@Deprecated
public class RequiredConfigurationFile extends Required {
	public RequiredConfigurationFile(String resourceName) {
		super(resourceName);
	}

	public RequiredConfigurationFile(File file) {
		super(file);
	}

	public RequiredConfigurationFile(Configuration config) {
		super(config);
	}
}
