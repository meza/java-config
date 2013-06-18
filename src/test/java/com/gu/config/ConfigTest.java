package com.gu.config;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Properties;
import java.util.UUID;

public class ConfigTest {

	private Config config;
	private Configuration configuration;

	@Before
	public void setUp() {
		config = new Config();
		configuration = Mockito.mock(Configuration.class);
	}

	@Test
	public void basicsShouldFuntcion() {

		String testKey = randomString();
		String testValue = randomString();

		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration);

		String actual = config.get(testKey);

		Assert.assertEquals(testValue, actual);

	}

	@Test
	public void shouldHandleNamespaces() {
		String testKey = randomString();
		String testValue = randomString();
		String namespace = randomString();

		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration, namespace);

		String actual = config.get(namespace + "." + testKey);

		Assert.assertEquals(testValue, actual);
	}

	@Test
	public void testAddNonOverriding() {
		String testKey = randomString();
		String testValue = randomString();
		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration);

		Configuration secondConfiguration = Mockito.mock(Configuration.class);
		final Properties secondProperties = testProperties(testKey, randomString());

		String secondTestKey = randomString();
		String secondTestValue = randomString();
		secondProperties.setProperty(secondTestKey, secondTestValue);

		Mockito.when(secondConfiguration.properties()).thenReturn(secondProperties);

		config.addNonOverriding(secondConfiguration);

		String actual = config.get(testKey);
		Assert.assertEquals(testValue, actual);

		String secondActual = config.get(secondTestKey);
		Assert.assertEquals(secondTestValue, secondActual);

	}

	@Test
	public void testAddNonOverridingWithNamespace() {
		String testKey = randomString();
		String testValue = randomString();
		String namespace = randomString();
		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration, namespace);

		Configuration secondConfiguration = Mockito.mock(Configuration.class);
		final Properties secondProperties = testProperties(testKey, randomString());

		String secondTestKey = randomString();
		String secondTestValue = randomString();
		secondProperties.setProperty(secondTestKey, secondTestValue);

		Mockito.when(secondConfiguration.properties()).thenReturn(secondProperties);

		config.addNonOverriding(secondConfiguration, namespace);

		String actual = config.get(namespace + "." + testKey);
		Assert.assertEquals(testValue, actual);

		String secondActual = config.get(namespace + "." + secondTestKey);
		Assert.assertEquals(secondTestValue, secondActual);
	}

	@Test
	public void testAddOverriding() {
		String testKey = randomString();
		String testValue = randomString();
		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration);

		Configuration secondConfiguration = Mockito.mock(Configuration.class);
		final String overridedValue = randomString();
		final Properties secondProperties = testProperties(testKey, overridedValue);

		String secondTestKey = randomString();
		String secondTestValue = randomString();
		secondProperties.setProperty(secondTestKey, secondTestValue);

		Mockito.when(secondConfiguration.properties()).thenReturn(secondProperties);

		config.addOverriding(secondConfiguration);

		String actual = config.get(testKey);
		Assert.assertEquals(overridedValue, actual);

		String secondActual = config.get(secondTestKey);
		Assert.assertEquals(secondTestValue, secondActual);
	}

	@Test
	public void testAddOverridingWithNamespace() {
		String testKey = randomString();
		String testValue = randomString();
		String namespace = randomString();
		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));

		config.add(configuration, namespace);

		Configuration secondConfiguration = Mockito.mock(Configuration.class);
		final String overridedValue = randomString();
		final Properties secondProperties = testProperties(testKey, overridedValue);

		String secondTestKey = randomString();
		String secondTestValue = randomString();
		secondProperties.setProperty(secondTestKey, secondTestValue);

		Mockito.when(secondConfiguration.properties()).thenReturn(secondProperties);

		config.addOverriding(secondConfiguration, namespace);

		String actual = config.get(namespace + "." + testKey);
		Assert.assertEquals(overridedValue, actual);

		String secondActual = config.get(namespace + "." + secondTestKey);
		Assert.assertEquals(secondTestValue, secondActual);

	}

	@Test
	public void testAddHighOrder() {
		String testKey = randomString();
		String testValue = randomString();
		Configuration highOrderSettings = Mockito.mock(Configuration.class);

		Properties highOrderProps = new Properties();
		highOrderProps.setProperty(testKey, testValue);

		Configuration secondConfiguration = Mockito.mock(Configuration.class);

		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, randomString()));
		Mockito.when(secondConfiguration.properties()).thenReturn(testProperties(testKey, randomString()));
		Mockito.when(highOrderSettings.properties()).thenReturn(highOrderProps);

		config.add(configuration);
		config.addHighOrder(highOrderSettings);
		config.addOverriding(secondConfiguration);

		String actual = config.get(testKey);
		Assert.assertEquals(testValue, actual);

	}

	@Test
	public void localConfigShouldDecorateTheGlobalConfig() {
		String testKey = randomString();
		String testValue = randomString();
		Mockito.when(configuration.properties()).thenReturn(testProperties(testKey, testValue));
		config.add(configuration);
		Config localConfig = new Config(config);

		String actual = localConfig.get(testKey);
		Assert.assertEquals(testValue, actual);

	}

	@Test
	public void whatHappensWhenEmpty() {
		final String configKey = "something";
		try {
			config.get(configKey);
			Assert.fail("Exception was not thrown");
		} catch (ConfigurationNotFoundException e) {
			String expected = String.format("Could not find configuration key: %s", configKey);
			Assert.assertEquals(expected, e.getMessage());
		}
	}

	private Properties testProperties(String testKey, String testValue) {
		Properties properties = new Properties();
		properties.setProperty(testKey, testValue);
		return properties;
	}

	private String randomString() {
		return UUID.randomUUID().toString();
	}
}
