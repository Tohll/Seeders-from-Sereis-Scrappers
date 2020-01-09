package planets;

import java.awt.Point;

public class HydrogenPlanet extends AbsPlanet {

	/**
	 *
	 */
	private static final long serialVersionUID = 6616424190804123563L;

	public HydrogenPlanet(final int number, final String planetSize, final Point position) {
		super(number, "resources/img/2.png", planetSize, position, "Hydrogen");
		this.setToolTipText("<html><p>Number : " + number + "<br>Type : Hydrogen<br>Size : " + planetSize);
	}

}
