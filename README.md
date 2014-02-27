Configuration
=============

the purpose and some advanced usage of this library is outlined
in [this post](http://www.meza.hu/2013/08/configuration-done-right.html) so I will not duplicate it here.

Basic usage
===========

To get the best value out of this lib with the least amount of effort, use this as it is:
(and add your own configuration directives)

```java
import hu.meza.tools.config.Config;
import hu.meza.tools.config.ConfigurationNotFoundException;
import hu.meza.tools.config.Loaders.SystemPropertiesConfiguration;
import hu.meza.tools.config.Required;

import java.io.File;

public class Configuration {

	private Config config = new Config();

	public Configuration() {
		config.addHighOrder(new SystemPropertiesConfiguration());

		String configFilename = config.get("config", "config.properties");
		File file = new File(configFilename);
		config.add(new Required(file));
	}

	public String someConfiguration() {
		return config.get("someConfiguration");
	}
}
```

This will require you to either have a config.properties next to the project, or to set a config parameter
and specify the location of the file.

It is not advised to expose the internal Config object to the rest of the code, so when you have to rename config
parameters, you can be sure that this will be the only place you need to look at.
