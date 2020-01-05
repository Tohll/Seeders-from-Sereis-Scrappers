package controlers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import planets.AbsPlanet;
import planets.BarrenPlanet;
import planets.HydrogenPlanet;
import planets.LavaPlanet;
import planets.WaterPlanet;
import tools.ConfigFileReader;

public class PlanetsControler {

	private static class SingletonHolder {
		private static final PlanetsControler INSTANCE = new PlanetsControler();
	}

	public static PlanetsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private Dimension boardSize;
	private final ConfigFileReader configFileReader;
	private int planetCount;
	private final List<AbsPlanet> planets;
	private final Random random;

	private PlanetsControler() {
		this.random = new Random();
		this.planets = new ArrayList<>();
		this.configFileReader = new ConfigFileReader();
		try {
			this.boardSize = new Dimension(Integer.parseInt(this.configFileReader.getPropertieValue("boardWidth")),
					Integer.parseInt(this.configFileReader.getPropertieValue("boardHeight")));
			this.planetCount = Integer.parseInt(this.configFileReader.getPropertieValue("planetCount"));
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		this.initPlanets();
	}

	public List<AbsPlanet> getPlanets() {
		return this.planets;
	}

	private void initPlanets() {

		final int boardDeadBorder = 50;
		final int xOffSet = this.boardSize.width - (boardDeadBorder * 2);
		final int yOffSet = this.boardSize.height - (boardDeadBorder * 2);

		for (int i = 1; i <= this.planetCount; i++) {
			final Point position = new Point(this.random.nextInt(xOffSet) + boardDeadBorder,
					this.random.nextInt(yOffSet) + boardDeadBorder);
			final int randType = this.random.nextInt(4);
			final int randCaliber = this.random.nextInt(3);
			switch (randType) {
			// barren
			case 0:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new BarrenPlanet(AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new BarrenPlanet(AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new BarrenPlanet(AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException("Bad planet caliber");
				}
				break;
				// water
			case 1:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new WaterPlanet(AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new WaterPlanet(AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new WaterPlanet(AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException("Bad planet caliber");
				}
				break;
				// lava
			case 2:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new LavaPlanet(AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new LavaPlanet(AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new LavaPlanet(AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException("Bad planet caliber");
				}
				break;
				// hydrogen
			case 3:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new HydrogenPlanet(AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new HydrogenPlanet(AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new HydrogenPlanet(AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException("Bad planet caliber");
				}
				break;
			}
		}
		System.out.println("Planet count/initialized : " + this.planets.size());
	}

}
