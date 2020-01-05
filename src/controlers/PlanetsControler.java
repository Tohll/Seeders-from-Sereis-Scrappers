package controlers;

import java.awt.Dimension;
import java.awt.Point;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import planets.AbsPlanet;
import planets.BarrenPlanet;
import planets.HydrogenPlanet;
import planets.LavaPlanet;
import planets.WaterPlanet;
import tools.ConfigFileReader;

public class PlanetsControler implements Serializable {

	private static class SingletonHolder {
		private static final PlanetsControler INSTANCE = new PlanetsControler();
	}

	/**
	 *
	 */
	private static final long serialVersionUID = -8997586541657617376L;

	public static PlanetsControler _getInstance() {
		return SingletonHolder.INSTANCE;
	}

	private final String badCaliberMessage;
	private Dimension boardSize;
	private final ConfigFileReader configFileReader;
	private int planetCount;
	private final List<AbsPlanet> planets;
	private final Random random;

	private PlanetsControler() {
		this.badCaliberMessage = "Bad planet caliber";
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

		final int boardDeadBorder = 250;
		final int xOffSet = this.boardSize.width - (boardDeadBorder * 2);
		final int yOffSet = this.boardSize.height - (boardDeadBorder * 2);

		for (int i = 1; i <= this.planetCount; i++) {
			final Point position = new Point(this.random.nextInt(xOffSet) + boardDeadBorder,
					this.random.nextInt(yOffSet) + boardDeadBorder);
			final int randType = this.random.nextInt(4);
			final int randCaliber = this.random.nextInt(3);
			final int number = this.random.nextInt(300) + 105;
			switch (randType) {
			// barren
			case 0:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new BarrenPlanet(number, AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new BarrenPlanet(number, AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new BarrenPlanet(number, AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException(this.badCaliberMessage);
				}
				break;
				// water
			case 1:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new WaterPlanet(number, AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new WaterPlanet(number, AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new WaterPlanet(number, AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException(this.badCaliberMessage);
				}
				break;
				// lava
			case 2:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new LavaPlanet(number, AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new LavaPlanet(number, AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new LavaPlanet(number, AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException(this.badCaliberMessage);
				}
				break;
				// hydrogen
			case 3:
				switch (randCaliber) {
				// small
				case 0:
					this.planets.add(new HydrogenPlanet(number, AbsPlanet.S, position));
					break;
					// medium
				case 1:
					this.planets.add(new HydrogenPlanet(number, AbsPlanet.M, position));
					break;
					// large
				case 2:
					this.planets.add(new HydrogenPlanet(number, AbsPlanet.L, position));
					break;
				default:
					throw new IllegalArgumentException(this.badCaliberMessage);
				}
				break;
			default:
				throw new IllegalArgumentException("Bad planet type");
			}
		}
		System.out.println("Planet count/initialized : " + this.planets.size());
	}

}
