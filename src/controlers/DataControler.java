package controlers;

import java.util.ArrayList;
import java.util.List;

import interfaces.ObsInterface;

public class DataControler {

	private static class SingletonHolder {
		private static final DataControler INSTANCE = new DataControler();
	}

	public static DataControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	PlayerControler playerControler;
	List<ObsInterface> playerObservers;

	private DataControler() {
		this.playerControler = PlayerControler._getInstance();
		this.playerObservers = new ArrayList<>();
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
