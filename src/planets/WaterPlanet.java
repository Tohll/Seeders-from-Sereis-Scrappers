package planets;

import java.awt.Point;

public class WaterPlanet extends AbsPlanet {

	/**
	 *
	 */
	private static final long serialVersionUID = 421951389950034657L;

	public WaterPlanet(final int number, final String planetSize, final Point position) {
		super(number, "resources/img/3.png", planetSize, position);
		this.setToolTipText("<html><p>Number : " + number + "<br>Type : Water<br>Size : " + planetSize);
	}

}
