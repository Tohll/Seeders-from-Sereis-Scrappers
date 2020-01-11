package controlers;

import planets.AbsPlanet;

public class PlayerControler {

	private static class SingletonHolder {
		private static final PlayerControler INSTANCE = new PlayerControler();
	}

	public static PlayerControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private int creditAccount;
	private AbsPlanet selectedPlanet;

	private PlayerControler() {
		this.selectedPlanet = null;
		this.creditAccount = Integer.parseInt(DataControler._getInstance().getConfigProperty("playerAccount"));
	}

	public void addCreditsToPlayer(final int amount) {
		this.creditAccount = this.creditAccount + amount;
	}

	public long getPlayerAccount() {
		return this.creditAccount;
	}

	public AbsPlanet getSelectedPlanet() {
		return this.selectedPlanet;
	}

	public void removeCreditsFromPlayer(final int amount) {
		this.creditAccount = this.creditAccount - amount;
	}

	public void setSelectedPlanet(final AbsPlanet selectedPlanet) {
		this.selectedPlanet = selectedPlanet;
		DataControler._getInstance().updatePlayerObservers();
	}
}
