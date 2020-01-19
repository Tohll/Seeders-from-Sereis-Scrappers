package ships;

import catapults.Catapult;
import controlers.CatapultsControler;
import tools.Vector2;

public class Hauler extends AbsShip {

	/**
	 *
	 */
	private static final long serialVersionUID = 9025375384783918279L;

	public Hauler(final Catapult homeLand) {
		super(homeLand, 1);
		this.type = AbsShip.HAULER;
		this.life = 100;
	}

	@Override
	protected void act() {
		while (!this.isInterrupted()) {
			try {
				this.docked = false;
				this.setTarget(
						new Vector2(CatapultsControler._getInstance().getCatapults().get("MAT-001").getDockLocation().x,
								CatapultsControler._getInstance().getCatapults().get("MAT-001").getDockLocation().y));
				this.move();
				this.setTarget(new Vector2(this.homeLand.getDockLocation().x, this.homeLand.getDockLocation().y));
				this.move();
			} catch (final InterruptedException e) {
				e.printStackTrace();
				this.interrupt();
			}
		}
	}
}
