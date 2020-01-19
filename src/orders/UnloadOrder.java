package orders;

import ships.AbsShip;

public class UnloadOrder extends AbsOrder {

	public UnloadOrder(final AbsShip ship) {
		super("Unloading...", ship);
	}

	@Override
	protected void execute() {
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			e.printStackTrace();
			this.ship.interrupt();
		}
	}

}
