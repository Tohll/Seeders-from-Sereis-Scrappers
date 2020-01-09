package planets;

import java.awt.Point;

public class LavaPlanet extends AbsPlanet {

	/**
	 *
	 */
	private static final long serialVersionUID = -7713222540125690043L;

	public LavaPlanet(final int number, final String planetSize, final Point position) {
		super(number, "resources/img/1.png", planetSize, position, "Lava");
		this.setToolTipText("<html><p>Number : " + number + "<br>Type : Lava<br>Size : " + planetSize);
	}

}
