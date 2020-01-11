package controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import interfaces.ExternalDataProvider;
import interfaces.ObsInterface;
import tools.ConfigFileReader;

public class DataControler {

	private static class SingletonHolder {
		private static final DataControler INSTANCE = new DataControler();
	}

	public static DataControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final Map<String, String> config;
	private final ExternalDataProvider configFileReader;
	private final List<ObsInterface> playerObservers;

	private DataControler() {
		this.playerObservers = new ArrayList<>();
		this.config = new HashMap<>();
		this.configFileReader = new ConfigFileReader();
		this.initConfigMap();
	}

	public String getConfigProperty(final String key) {
		return this.config.get(key);
	}

	private void initConfigMap() {
		// width=1800
		// height=950
		// boardWidth=3840
		// boardHeight=2160
		// planetCount=12
		try {
			this.config.put("width", this.configFileReader.getPropertieValue("width"));
			this.config.put("height", this.configFileReader.getPropertieValue("height"));
			this.config.put("boardWidth", this.configFileReader.getPropertieValue("boardWidth"));
			this.config.put("boardHeight", this.configFileReader.getPropertieValue("boardHeight"));
			this.config.put("planetCount", this.configFileReader.getPropertieValue("planetCount"));
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
