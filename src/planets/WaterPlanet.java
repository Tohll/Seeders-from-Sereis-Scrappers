package planets;

import java.awt.Point;

public class WaterPlanet extends AbsPlanet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 421951389950034657L;

	public WaterPlanet(String filePath, String planetSize, Point position) {
		super("resources/img/3.png", planetSize, position);
	}

}
