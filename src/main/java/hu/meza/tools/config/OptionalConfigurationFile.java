package hu.meza.tools.config;

import java.io.File;

/**
 * @deprecated use Optional instead
 */
@Deprecated
public class OptionalConfigurationFile extends Optional {
	public OptionalConfigurationFile(File file) {
		super(file);
	}

	public OptionalConfigurationFile(String resourceName) {
		super(resourceName);
	}

	public OptionalConfigurationFile(Configuration config) {
		super(config);
	}
}
