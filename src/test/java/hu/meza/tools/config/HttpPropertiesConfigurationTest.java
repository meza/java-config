package hu.meza.tools.config;

import hu.meza.tools.HttpCall;
import hu.meza.tools.HttpClientWrapper;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.UUID;

public class HttpPropertiesConfigurationTest {

	@Test
	public void testConnection() {
		String configString = "something=anything";

		Config config = new Config();

		final String url = UUID.randomUUID().toString();

		HttpCall result = Mockito.mock(HttpCall.class);
		Mockito.when(result.body()).thenReturn(configString);

		HttpClientWrapper client = Mockito.mock(HttpClientWrapper.class);
		Mockito.when(client.getFrom(Mockito.anyString())).thenReturn(result);

		config.add(new HttpPropertiesConfiguration(url, client));

		Assert.assertEquals("anything", config.get("something"));

	}

}
