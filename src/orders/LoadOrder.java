package orders;

import ships.AbsShip;

public class LoadOrder extends AbsOrder {

	public LoadOrder(final AbsShip ship) {
		super("Loading...", ship);
	}

	@Override
	protected void execute() {
		try {
			Thread.sleep(4000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
			this.ship.interrupt();
		}
	}

}
