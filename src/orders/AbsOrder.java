package orders;

import interfaces.OrderInterface;
import ships.AbsShip;

public abstract class AbsOrder implements OrderInterface {
	protected String comment;
	protected AbsShip ship;

	public AbsOrder(final String comment, final AbsShip ship) {
		this.ship = ship;
		this.comment = comment;
	}

	protected abstract void execute();

	@Override
	public void executeOrder() {
		this.execute();
	}

	@Override
	public String getComment() {
		return this.comment;
	}
}
