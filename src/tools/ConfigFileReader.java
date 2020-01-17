package tools;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import interfaces.ExternalDataProvider;

public class ConfigFileReader implements Serializable, ExternalDataProvider {

	private static final long serialVersionUID = 1L;
	private final String fileName;

	public ConfigFileReader(final String fileName) {
		this.fileName = fileName;
	}

	@Override
	public Properties getProperties() throws IOException {
		InputStream inputStream = null;

		final Properties properties = new Properties();
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(this.fileName);

			if (inputStream != null) {
				properties.loadFromXML(inputStream);
			} else {
				throw new FileNotFoundException("Property file " + this.fileName + " not found in classpath.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		return properties;
	}
}
