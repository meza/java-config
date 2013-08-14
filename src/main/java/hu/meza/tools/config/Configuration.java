package hu.meza.tools.config;

import java.util.Properties;

public interface Configuration {
	boolean load();

	Properties properties();

}
