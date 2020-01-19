package orders;

import controlers.CatapultsControler;
import ships.AbsShip;
import tools.Vector2;

public class MoveToCatapultOrder extends AbsOrder {

	private final String destination;

	public MoveToCatapultOrder(final String destination, final AbsShip ship) {
		super("Moving to " + destination + "...", ship);
		this.destination = destination;
	}

	@Override
	public void execute() {
		this.ship.setDocked(false);
		this.ship.setTarget(
				new Vector2(CatapultsControler._getInstance().getCatapults().get(this.destination).getDockLocation().x,
						CatapultsControler._getInstance().getCatapults().get(this.destination).getDockLocation().y));
		try {
			this.ship.move();
		} catch (final InterruptedException e) {
			e.printStackTrace();
			this.ship.interrupt();
		}
	}

}
