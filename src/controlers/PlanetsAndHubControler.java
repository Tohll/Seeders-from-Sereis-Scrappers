package controlers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import planets.AbsPlanet;

public class PlanetsAndHubControler implements Serializable {

	private static class SingletonHolder {
		private static final PlanetsAndHubControler INSTANCE = new PlanetsAndHubControler();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -8997586541657617376L;

	public static PlanetsAndHubControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final List<AbsPlanet> planets;

	private PlanetsAndHubControler() {
		this.planets = new ArrayList<>();
	}

	public List<AbsPlanet> getPlanets() {
		return this.planets;
	}
}
