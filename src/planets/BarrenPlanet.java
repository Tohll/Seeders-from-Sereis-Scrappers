package planets;

import java.awt.Point;

public class BarrenPlanet extends AbsPlanet {

	/**
	 *
	 */
	private static final long serialVersionUID = -7498391496950068248L;

	public BarrenPlanet(final String planetSize, final Point position) {
		super("resources/img/4.png", planetSize, position);
	}

}
