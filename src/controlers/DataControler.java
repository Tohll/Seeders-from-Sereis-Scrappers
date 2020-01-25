package controlers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;

import dataModels.ShipsListModel;
import interfaces.ObsInterface;
import tools.ConfigFileReader;

public class DataControler {

	private static class SingletonHolder {
		private static final DataControler INSTANCE = new DataControler();
	}

	public static DataControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Properties catapults;
	private final List<ObsInterface> playerObservers;
	private Properties properties;
	private final AbstractTableModel shipsListModel;

	private DataControler() {
		this.playerObservers = new ArrayList<>();
		this.shipsListModel = new ShipsListModel();
		this.initProperties();
		this.initCatapults();
	}

	public Properties getCatapults() {
		return this.catapults;
	}

	public String getConfigProperty(final String key) {
		return this.properties.getProperty(key);
	}

	public AbstractTableModel getShipsListModel() {
		return this.shipsListModel;
	}

	private void initCatapults() {
		final ConfigFileReader configFileReader = new ConfigFileReader("config/catapults.properties.xml");
		try {
			this.catapults = configFileReader.getProperties();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private void initProperties() {
		final ConfigFileReader configFileReader = new ConfigFileReader("config/config.properties.xml");
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

	public void updateShipsListModel() {
		this.shipsListModel.fireTableDataChanged();
	}
}
