package controlers;

import planets.AbsPlanet;

public class PlayerControler {

	private static class SingletonHolder {
		private static final PlayerControler INSTANCE = new PlayerControler();
	}

	public static PlayerControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private AbsPlanet selectedPlanet;

	private PlayerControler() {
		this.selectedPlanet = null;
	}

	public AbsPlanet getSelectedPlanet() {
		return this.selectedPlanet;
	}

	public void setSelectedPlanet(final AbsPlanet selectedPlanet) {
		this.selectedPlanet = selectedPlanet;
		DataControler._getInstance().updatePlayerObservers();
	}
}
