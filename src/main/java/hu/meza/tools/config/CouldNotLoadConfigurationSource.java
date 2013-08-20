package hu.meza.tools.config;

public class CouldNotLoadConfigurationSource extends RuntimeException {
	public CouldNotLoadConfigurationSource(String source) {
		super(String.format("Could not load %s", source));
	}
}
