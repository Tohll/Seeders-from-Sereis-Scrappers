package controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import interfaces.ObsInterface;
import tools.ConfigFileReader;

public class DataControler {

	private static class SingletonHolder {
		private static final DataControler INSTANCE = new DataControler();
	}

	public static DataControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final List<ObsInterface> playerObservers;
	private Properties properties;

	private DataControler() {
		this.playerObservers = new ArrayList<>();
		this.initProperties();
	}

	public String getConfigProperty(final String key) {
		return this.properties.getProperty(key);
	}

	private void initProperties() {
		final ConfigFileReader configFileReader = new ConfigFileReader();
		try {
			this.properties = configFileReader.getProperties();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public void observePlayer(final ObsInterface observer) {
		this.playerObservers.add(observer);
	}

	public void updatePlayerObservers() {
		for (final ObsInterface observer : this.playerObservers) {
			observer.update();
		}
	}
}
