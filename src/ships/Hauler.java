package ships;

import catapults.Catapult;

public class Hauler extends AbsShip {

	/**
	 *
	 */
	private static final long serialVersionUID = 9025375384783918279L;

	public Hauler(final Catapult homeLand) {
		super(homeLand, 10);
		this.type = AbsShip.HAULER;
		this.life = 100;
	}
}
