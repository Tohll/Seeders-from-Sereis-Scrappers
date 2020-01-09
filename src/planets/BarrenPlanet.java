package planets;

import java.awt.Point;

public class BarrenPlanet extends AbsPlanet {

	/**
	 *
	 */
	private static final long serialVersionUID = -7498391496950068248L;

	public BarrenPlanet(final int number, final String planetSize, final Point position) {
		super(number, "resources/img/4.png", planetSize, position, "Barren");
		this.setToolTipText("<html><p>Number : " + number + "<br>Type : Barren<br>Size : " + planetSize);
	}

}
