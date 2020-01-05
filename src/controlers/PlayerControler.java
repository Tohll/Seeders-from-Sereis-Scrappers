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
		// implementation not needed
	}

	public AbsPlanet getSelectedPlanet() {
		return this.selectedPlanet;
	}

	public void setSelectedPlanet(final AbsPlanet selectedPlanet) {
		if (this.selectedPlanet != null) {
			this.selectedPlanet.isPlanetSelected(false);
		}
		this.selectedPlanet = selectedPlanet;
		this.selectedPlanet.isPlanetSelected(true);
	}
}
