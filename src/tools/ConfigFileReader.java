package tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import interfaces.ExternalDataProvider;

public class ConfigFileReader implements Serializable, ExternalDataProvider {

	private static final long serialVersionUID = 1L;

	public ConfigFileReader() {
		// no implementatioin needed
	}

	@Override
	public String getPropertieValue(final String key) throws IOException {
		String result = null;
		InputStream inputStream = null;

		final Properties properties = new Properties();
		final String fileName = "config/config.properties";
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName);

			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				throw new FileNotFoundException("Property file " + fileName + " not found in classpath.");
			}
			result = properties.getProperty(key);
		} catch (final Exception e) {
			System.out.println(e);
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return result;
	}
}
