package controlers;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import planets.AbsPlanet;
import planets.BarrenPlanet;

public class PlanetsControler {

	private static class SingletonHolder {
		private static final PlanetsControler INSTANCE = new PlanetsControler();
	}

	public static PlanetsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private List<AbsPlanet> planets;
	private Random random;

	private PlanetsControler() {
		this.random = new Random();
		this.planets = new ArrayList<>();
		this.initPlanets(20);
	}

	private void initPlanets(int planetCount) {

		for (int i = 1; i <= planetCount; i++) {
			//TODO generation aleatoire de position et globalement des planetes
			AbsPlanet planet;
			int randType = random.nextInt(4);
			switch (randType) {
			//barren
			case 0:
				String caliber;
				int randCaliber = random.nextInt(3);
				switch (randCaliber) {
				//small
				case 0:
//					this.planets.add(new BarrenPlanet(planetSize, position))
				}
			}
		}
	}

}
